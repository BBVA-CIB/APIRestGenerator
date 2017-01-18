# API Rest Generator
## Overview
This project is targeting people who want to implement Rest APIs server and client code just designing a Swagger specification. You will not need to understand every framework specification (flavours) like Spring, JAX-RS, JQuery, etc., we will do for you, you will only implement your business code!

![mainView](https://github.com/BBVA-CIB/APIRestGenerator/blob/master/screenshots/mainView.png)

#### Why API Rest Generator?
**Time.** Nowadays, developers spend so much time understanding new frameworks when their milestones are usually getting shorter, being quite hard to look into third party libraries.

**Variety.** There are several ways to define a Rest communication between client and server and not all the architectures support specific frameworks.

**Team coordination.** When different teams (e.g. one as front-end and another as back-end) have to work together in a project, some misunderstanding problems could appear. To avoid those problems, it could be necessary "mocks" to work independently or any common specification to define the Rest endpoints.  

API Rest Generator is the solution for the previous problems:
* Imagine a Website where you import your API specification, choose a flavour and the outcome is a complete implementation for your endpoints.
* Imagine that the outcome also contains an example project with all your endpoints, to be useful not only for validating your code generation but also as "mocks" for other teams.
* Finally, imagine that the only thing to implement is your business code, i.e. no annotations, no remote calls, etc., just implement the listeners!

**This is API Rest Generator: simple and flexible!**

##### simple because ...
* You only need to import your API specification, use one of the examples or code a new one in the text area.
* Choose one of the current available frameworks and click on it.
* It will generate different artifacts and an example project based on the input specification.
* Use the example project to start your work or share with other teams!

##### and flexible because ...
* The project is divided in different modules to implement new flavours easily and avoid the coupling.
* You can also implement new parsers
* Feel free to implement a new ones!

#### What is the current parser?

Nowadays, we have **implemented** a **parser for Swagger SPEC** but we would like to add a **RAML parser in the near future** ;)

#### What are the current flavours?

For server:
* **Spring MVC**
* **JAX-RS (CXF)** - The generation is compatible with OSGi

For client:
* **Javascript** (JQuery) - Implemented and compiled by Typescript previously
* **JAX-RS (CXF)** - The generation is compatible with OSGi

## Installation
##### Prerequisites
- [x] Java 1.7_80+
- [x] Typescript in your classpath
- [x] Maven in your classpath and connection to Maven Repository.

After downloading the project, you have to compile the modules with Maven. In the parent project execute:
```
mvn clean install
```
Copy every generated JAR file in `JavaProject/generatorBuilder/modules` folder.
* **Compulsory JAR files**: Web Client, Core, Swagger parser and Server Spring Controller.
* **Optional JAR files**: Each generator module is optional. If the generator has not been copied, it will not appear as a menu item.

Copy the core dependencies in JavaProject/generatorBuilder/lib
* In the core project (com.bbva.kltt.KLTT-APIRestGenerator.core):
```
mvn -DoutputDirectory=path/to/JavaProject/generatorBuilder/lib dependency:copy-dependencies
```
* The dependencies of Server Spring Controller module (com.bbva.kltt-KLTT-APIRestGenerator.controller.server-spring) must be copied as well:
```
mvn -DoutputDirectory=path/to/JavaProject/generatorBuilder/lib dependency:copy-dependencies
```
**Unix**
Go to JavaProject path and execute "serverLauncher.sh" and then "clientLauncher.sh".

**Windows**
Go to JavaProject path and execute "serverLauncher.cmd" and then "clientLauncher.cmd".

Finally, open your browser and go to `http://localhost:8090` (or the address you have chosen in the properties file “application.yml” - Project: com.bbva.kltt.KLTT-APIRestGenerator.web-client).

Please, visit our [WIKI](https://github.com/BBVA-CIB/APIRestGenerator/wiki) and [CHANGELOG](https://github.com/BBVA-CIB/APIRestGenerator/blob/master/CHANGELOG.md) for more information.