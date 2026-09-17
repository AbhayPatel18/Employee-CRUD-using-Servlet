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

## How to Run

### Prerequisites

Make sure the following are installed on your system:

- JDK 17 or higher
- Apache Tomcat 10.1
- MySQL 8.x
- Eclipse IDE (Enterprise Edition)
- MySQL Connector/J 9.6.0

---

### Step 1 — Clone the Repository

Open a terminal and run:

```bash
git clone https://github.com/AbhayPatel18/Employee-CRUD-using-Servlet.git
```

---

### Step 2 — Set Up the Database

Open **MySQL Workbench** and execute the following SQL:

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
```

---

### Step 3 — Import Project into Eclipse

1. Open Eclipse
2. Go to **File → Import**
3. Select **General → Existing Projects into Workspace**
4. Click **Next**
5. Browse and select the cloned project folder
6. Click **Finish**

---

### Step 4 — Configure Database Credentials

Open `src/main/java/com/employee/dao/EmployeeDao.java` and update the following lines with your MySQL credentials:

```java
String url = "jdbc:mysql://localhost:3306/employee_db";
String user = "root";
String password = "your_mysql_password";
```

---

### Step 5 — Add the MySQL Connector JAR

Make sure the file `mysql-connector-j-9.6.0.jar` is present inside:

```
src/main/webapp/WEB-INF/lib/
```

If it is missing, download it from the [MySQL Connector/J page](https://dev.mysql.com/downloads/connector/j/) and place it in that folder. Eclipse will automatically add it to the project's Web App Libraries.

---

### Step 6 — Deploy to Tomcat

1. In Eclipse, open the **Servers** view
2. Right-click the Tomcat server → **Add and Remove**
3. Move `EmployeeCRUD2` to the **Configured** side → **Finish**
4. Right-click the Tomcat server → **Clean**
5. Right-click the Tomcat server → **Publish**
6. Right-click the Tomcat server → **Start**

---

### Step 7 — Open in Browser

Once Tomcat starts successfully, open:

```
http://localhost:8080/EmployeeCRUD2/login.html
```

---

### Step 8 — Login

Use the default administrator credentials:

| Field | Value |
|-------|-------|
| Username | `admin` |
| Password | `admin@123` |

---

### Step 9 — Use the Application

- Click **Add Employee** to create a new record
- Click **View All** to see the employee list
- Click **Edit** on any row to update details
- Click **Delete** to remove a record (a confirmation dialog will appear)
- Click **Logout** to end the session
