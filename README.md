# Cool Tools

A CLI to rent tools

## Dependencies
Build with OpenJDK 17.0.7 and Maven 3.9.2

## How to run

1. Package
    ```bash
    cd cooltools
    mvn clean package
    ```
1. Run jar
    ```bash
    java -jar .\target\cooltools-1.0-SNAPSHOT.jar
    ```
1. You should see:
    ```bash
    Welcome to Cool Tools!
    Please enter your rental info
    Tool Code:
    ```
1. Follow the prompts to enter the rental information
1. If your request is unable to be processed, you will have the ability to retry when prompted.

## Required Tests

The tests outlined in the requirements are located in cooltools\src\test\java\com\katiehagood\AppTest.java