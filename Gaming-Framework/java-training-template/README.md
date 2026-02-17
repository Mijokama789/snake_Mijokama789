# java-training-template

## Preparation

1. Download and install Git for Windows
    1. Select Notepad++ as editor
    2. Use `main` branch
    3. Use external OpenSSH
    4. Native Windows Secure Channel library (**Important*!*)
    5. Checkout Windows-style, commit Linux-style
    6. Use MinTTY
    7. Fast-forward-merge
    8. Git credential manager
    9. Enable file system caching
    10. No experimental support for consoles
2. Create a fork of this repository
3. Clone your repository
4. Also clone https://gitea.ausbildung.werum.net/koerber-pharma-software/java-training-framework
5. Open the directory containing both repositories in Intellij Idea
6. Refresh all Maven projects in java-training-framework and do a `clean install`
7. Remove the repository settings in the pom.xml of your repository
8. Refresh the Maven project of your repository and also do a `clean install`
9. Start the `Showcase` class

## Usage

Steps to using this template

1. Update the LICENSE
2. Update the README
3. Update the pom.xml
4. Write code!

## How to compile:
Add the vm options to Maven.
```txt
-Dmaven.wagon.http.ssl.insecure=true
-Dmaven.wagon.http.ssl.allowall=true
-Dmaven.wagon.http.ssl.ignore.validity.dates=true
-Dmaven.resolver.transport=wagon
```