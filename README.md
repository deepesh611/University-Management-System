# 🎓 University Management System

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Shell Script](https://img.shields.io/badge/shell_script-%23121011.svg?style=for-the-badge&logo=gnu-bash&logoColor=white)
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

## Description
This Java application provides a university management system for managing student records. It includes functionalities such as user authentication, adding/editing student records, and viewing all records.

## 🚀 Features
- **🔐 Login Page**: Users must authenticate themselves through a login page before accessing the system.
- **📝 Student Database Management**: Users can perform CRUD operations on student records once authenticated.

## 💻 Technologies Used
- Java
- Java Swing (for GUI)
- MySQL (for database)
- JDBC (for database connectivity)

## 🛠️ Setup
1. **🗃️ Database Setup**: Set up a MySQL database to store student and user records. To do this, update your DB details in the `SetupDB` file  ( LOCATION: ./src/SetupDB.py ) and run it.
2. **☕ Java Development Kit (JDK)**: Make sure you have Java Development Kit installed on your system.
3. **🔌 Database Connection**: Update the database connection details (URL, username, password) in the Java code to connect to your MySQL database.
4. **▶️ Running the Application**: Compile and run the Java code to start the application.

## 📝 Usage
- Upon launching the application, users will be prompted to log in with their credentials.
- The initial credentials are, `USERNAME`: admin, `PASSWORD`: 12345. These credentials can be changed in MySQL.
- After successful login, users can add/edit student records or view all records.




