# Student Mark Management System

The Student Mark Management System is a simple Java project that allows you to manage student marks using Maven and JDBC. This project provides functionality to create student classes, store their marks, edit marks, and display the mark sheet and more.

## Features
- Show saved marksheets
- Create a new marksheet.
- Delete marksheet
- Store marks for students.
- Edit existing marks.
- Delete a student and his marks
- Display the mark sheet.

## Prerequisites
- Java Development Kit (JDK) 8 or higher installed.
- Maven build tool installed.
- MySQL database installed and running.

## POM dependecy to be added
- add the following dependency in pom.xml
```xml
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
</dependency>
```
## MySQL Password
- Either add your password in the string **password** in connectDb() function.
- or add the following line in .bashrc file in home folder of your linux distro.
```bash 
export MYSQL_PASSKEY="<mysql password here>"
```

## Contributing
Contributions are welcome! If you find any issues or would like to enhance the functionality, feel free to create a pull request.

Clone the repo by
```bash
git clone https://github.com/aswinbennyofficial/Student-Mark-Management.git

```
