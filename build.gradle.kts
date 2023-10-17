/*
 * Copyright (C) 2023 The ORT Project Authors (see <https://github.com/oss-review-toolkit/ort/blob/main/NOTICE>)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation

plugins {
    alias(libs.plugins.kotlin)
}

repositories {
    mavenCentral()
}

testing {
    suites {
        withType<JvmTestSuite>().configureEach {
            useJUnitJupiter()

            dependencies {
                implementation(libs.kotestAssertionsCore)
                implementation(libs.kotestRunnerJunit5)

                runtimeOnly(libs.log4jApiToSlf4j)
                runtimeOnly(libs.logbackClassic)
            }
        }

        register<JvmTestSuite>("funTest") {
            sources {
                kotlin {
                    testType = TestSuiteType.FUNCTIONAL_TEST
                }
            }

            dependencies {
                implementation(project())
            }
        }
    }
}

// Associate the "funTest" compilation with the "main" compilation to be able to access "internal" objects from
// functional tests.
kotlin.target.compilations.apply {
    getByName("funTest").associateWith(getByName(KotlinCompilation.MAIN_COMPILATION_NAME))
}

dependencies {
    api(libs.ortAnalyzer)
    api(libs.ortModel)

    implementation(libs.log4jApiKotlin)

    "funTestImplementation"(libs.ortTestUtils)
    "funTestImplementation"(variantOf(libs.ortAnalyzer) { classifier("test-fixtures") })
}
