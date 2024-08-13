# Numerical Data Processor
![Static Badge](https://img.shields.io/badge/Build-passing-flat)
[![Static Badge](https://img.shields.io/badge/Coverage-100%25-flat)](https://jacobnatural.github.io/numerical-data-processor/jacoco/index.html)
[![Static Badge](https://img.shields.io/badge/docs-blue)](https://jacobnatural.github.io/numerical-data-processor/apidocs/index.html)

## Overview

Numerical Data Processor is a Java-based application designed to analyze, validate, 
and optimize numerical data extracted from a file. The application provides a comprehensive
suite of functionalities for processing and evaluating numerical datasets, ensuring data quality, 
and performing advanced data analysis tasks.
All components of the application are thoroughly tested using JUnit.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 22+**
- **Apache Maven 3.9.6+**

### Example File

- Sample input data in a text file (`numbers.txt`) should be formatted as follows:
```text
# The format is: <first_number>;<second_number>;<third_number>
5;20;55
19;26;45
30;35;45
55;80;40
```

### Cloning the repository:

```bash
git clone https://github.com/JacobNatural/numerical-data-processor.git
cd numerical-data-processor
```

### Running the application:
- To build the application and run it:
```bash
mvn clean package -DskipTests
cd target  
java --enable-preview -jar numerical-data-processor-1.0.jar
```
### Running Tests
- To execute the tests, use the following command:
```bash
mvn clean test
```

### Integrate into Your Project
- If you want to use this application as a dependency in another Maven project,
  first install it into your local Maven repository by running:
```bash
mvn clean install -DskipTests
```
- Then, you can add the following dependency to your pom.xml file:

```xml
<dependency>
    <groupId>com.app</groupId>
    <artifactId>numerical-data-processor</artifactId>
    <version>1.0</version>
</dependency>
```

## Contributing

We welcome contributions to improve the Numerical Data Processor. Here's how you can contribute:

1. Fork the repository on GitHub.
2. Make enhancements or fix issues in your forked copy.
3. Submit a pull request to merge your changes into the main repository.

Please ensure your code adheres to our coding standards and is thoroughly tested before submitting a pull request.