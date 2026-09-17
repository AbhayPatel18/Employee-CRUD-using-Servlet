# Employee Management System

A web-based CRUD application built with Java Servlets, JDBC, MySQL, HTML, and CSS. It allows an administrator to securely log in and manage employee records through a clean browser interface.

---

## Features

- Secure admin login with session-based authentication
- Add new employees with name, salary, department, city, mobile number, email, and age
- View all employee records in a structured table
- Edit existing employee details with a pre-filled form
- Delete employees with a confirmation dialog
- Session protection on every protected page
- Persistent storage in MySQL using JDBC and PreparedStatement
- Clean and responsive UI built with HTML and CSS

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Frontend | HTML5, CSS3 |
| Backend | Java Servlets (Jakarta EE 6.0) |
| Data Access | JDBC with PreparedStatement |
| Database | MySQL 8.x |
| Server | Apache Tomcat 10.1 |
| IDE | Eclipse IDE |
| JDK | Java 17 or higher |

---

## Database Schema

**Database:** `employee_db`  
**Table:** `employee`

```sql
CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employee (
    emp_id        INT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(100) NOT NULL,
    salary        DECIMAL(10,2),
    department    VARCHAR(50),
    city          VARCHAR(50),
    mobile_number VARCHAR(15),
    email         VARCHAR(100),
    age           INT
);
