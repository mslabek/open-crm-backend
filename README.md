# Open CRM - Backend

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

A Customer Relationship Management backend application made for learning purposes.

[Live demo](https://open-crm-demo.onrender.com/swagger-ui/index.html) (might take a while to wake up the app)

## 📖 Table of Contents
* [General Information](https://github.com/mslabek/open-crm-backend#-general-information)
* [Features](https://github.com/mslabek/open-crm-backend#-features)
* [Usage](https://github.com/mslabek/open-crm-backend#-usage)
* [Tech stack](https://github.com/mslabek/open-crm-backend#-tech-stack)
* [Learning goals](https://github.com/mslabek/open-crm-backend#-learning-goals)
* [Road map](https://github.com/mslabek/open-crm-backend#%EF%B8%8F-road-map)
* [Project status](https://github.com/mslabek/open-crm-backend#-project-status)
* [License](https://github.com/mslabek/open-crm-backend#%EF%B8%8F-license)


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


## 🎡 Usage

### Demo data and general remarks
1. The easiest and recommended way to use the application is by the online demo - however it can take a while to 
   wake up ⏰
2. The online demo database already has some data entered - without data in the users table, it would be impossible to 
use the demo because basically all endpoints require authentication and authorization. There are 4 users you can log 
   in as:


| Username          | Password | Roles                                  |
|-------------------|----------|----------------------------------------|
| Admin             | pass     | ROLE_ADMIN                             |
| SalesTestUser     | pass     | ROLE_SALES                             |
| InventoryTestUser | pass     | ROLE_INVENTORY                         |
| SuperUser         | pass     | ROLE_ADMIN, ROLE_SALES, ROLE_INVENTORY |

3. You can log in by sending a ```POST``` HTTP request to the [login endpoint](https://open-crm-demo.onrender.com/swagger-ui/index.html/login)
with username and password as the request body in json format. In the response you receive an authentication cookie 
(JSESSIONID) you have to attach to every subsequent request.
4. Different endpoints require different user roles so if you want to have access to everything without having to 
   log in multiple times, just log in as superuser.
5. If you don't know how something works, check the documentation in the Swagger interface.

### Online demo usage

The online demo application can be used by sending to it HTTP requests manually (for example using ```curl``` command 
or Postman) or by using [Swagger interface](https://open-crm-demo.onrender.com/swagger-ui/index.html).


### Local deployment
The application can also be deployed locally using Docker (however requires some work) in following steps:
1. Clone this repository on your local machine
2. Build the docker image from the Dockerfile using ```docker build```
3. Prepare a postgres database:
   - Deploy the database (for example in Docker)
   - Run the [schema.sql](https://github.com/mslabek/open-crm-backend/blob/master/src/main/resources/schema.sql) script
   - Run the [users-insert.sql](https://github.com/mslabek/open-crm-backend/blob/master/src/main/resources/users-insert.sql) 
     script
4. Run the crm image using ```docker run``` with following environment variables:
   - DB_URL - the url to the database
   - DB_USERNAME - the username of a user in the database
   - DB_PASSWORD - the password of a user in the database
5. Run the created container


## 🛠 Tech Stack
Most important technologies used:
- Spring Boot:
  - Spring MVC
  - Spring Security
  - Spring Data JPA
- Build tool: Gradle
- Documentation tool: Swagger
- Database: Postgres (demo db is a postgres instance deployed on Render.com)
- Lombok
- Validation: Hibernate Validator
- Testing:
  - Mockito
  - AssertJ
- Demo deployment:
  - Docker
  - [Render.com](https://render.com)

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