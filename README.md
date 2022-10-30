# Open CRM - Backend

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

A Customer Relationship Management backend application made for learning purposes.

## 📖 Table of Contents
* [General Information](#📝-general-information)
* [Features](#✨-features)
* [Tech stack](#🛠-tech-stack)
* [Learning goals](#💡-learning-goals)
* [Road map](#🏗️-road-map)
* [Project status](#🌱-project-status)
* [License](#⚖️-license)


## 📝 General Information

The application facilitates managing relationships with customers by persisting the customer data. It also allows managing inventory and orders.

The purpose of developing this application is to gather and showcase knowledge about CRUD systems that are more complex than your typical learning resource.

## ✨ Features
- CRUD operations for a relational domain model
- Creating and managing orders by referencing Clients and Inventory
- REST API architecture and visual interface (Swagger)
- Form validation
- Cookie authentication
- User roles and method-level authorization


## 🛠 Tech Stack
Most important technologies used:
- Spring Boot:
    - Spring MVC
    - Spring Security
    - Spring Data JPA
- Build tool: Gradle
- Documentation tool: Swagger
- Database: Postgres
- Lombok
- Validation: Hibernate Validator
- Testing:
    - Mockito
    - AssertJ

## 💡 Learning Goals
- Basic principles of designing backend CRUD applications with REST api
- Basic principles of modeling a domain
- Configuring basic security (authentication and authorization)
- Writing documentation
- Writing tests
- Deploying docker applications with a Postgres database


## 🏗️ Road Map
Here are some most important features / improvements that need to be implemented:
- [ ] Basic operations on orders
- [ ] Invoice generation
- [ ] User registration
- [ ] Increase test coverage


## 🌱 Project Status
Project is in development.


## ⚖️ License
This project is open source and available under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).