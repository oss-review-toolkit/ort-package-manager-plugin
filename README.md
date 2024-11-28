# About

This is a template Gradle project to create a package manager plugin for the [analyzer tool] of the [OSS Review Toolkit].

# Usage

Create a new repository [from this template], clone it, and replace all occurrences of "MyPackageManager" (search for case-insensitive substrings) with the name of the package manager you are implementing.
Then implement the actual package manager, and when done, run

```shell
./gradlew installDist
```

Finally, copy `./build/install/MyPackageManager/*.jar` to the `plugin` direct of the ORT installation (`/opt/ort/plugin` in ORT's official Docker images).

[analyzer tool]: https://oss-review-toolkit.org/ort/docs/tools/analyzer
[OSS Review Toolkit]: https://github.com/oss-review-toolkit/ort
[from this template]: https://docs.github.com/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template
