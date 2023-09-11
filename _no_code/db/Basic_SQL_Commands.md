
## INTRO

- DDL - Data Defintion Language
- DML - Data Manipulation Language
- DQL - Data Query Language

![image](https://github.com/aswinbennyofficial/Student-Mark-Management/assets/110408942/59050db7-7ce7-449a-b041-15d7a402d190)


--- 

## DDL

#### 1. CREATE
```SQL

CREATE TABLE Student (Rollno int, Name varchar(25), Age int)

```

```SQL

CREATE TABLE CopyOfStudents AS SELECT rollno, age
FROM Students

```


#### 2. ALTER
```SQL

ALTER TABLE Student ADD age int

```

```SQL

ALTER TABLE Student DROP age

```

```SQL

ALTER TABLE Student RENAME age TO ages

```

```SQL

ALTER TABLE Customers MODIFY age varchar(20)

```


#### 3. DELETE
```SQL

DROP TABLE Students

-- Delete the whole table incl schema
```


#### 4. TRUNCATE 
```SQL

TRUNCATE TABLE Students

-- Delete without erasing schema
```


---

## DML

#### 1. INSERT
```SQL

INSERT INTO Students (Rollno,Age) VALUES (12,18)

```

```SQL

INSERT INTO Student2 select Rollno,age from Student1;

```


#### 2. UPDATE
```SQL

UPDATE Students SET Age=13 WHERE rollno>4

```

#### 3. DELETE
```SQL

DELETE FROM Students WHERE Rollno>3

```
```SQL

DELETE FROM Students WHERE Rollno IN (3,9,5)

```

```SQL

DELETE FROM Students
-- Delete all rows

```


---
## DQL

#### 1. SELECT
```SQL

SELECT * FROM Students

```

```SQL

SELECT age,name FROM Student

```

```SQL

SELECT age,name FROM Students WHERE rollno>4

```

```SQL

SELECT DISTINCT * FROM Students
-- outputs distinct elements only

```

```SQL

SELECT  Emp_id AS ID , Name FROM Company
--- Gives alias ID to emp_id of table

```


### Contraints
```sql

ALTER TABLE Student ADD CONSTRAINT chk_contraint CHECK(age>18)  ---can drop contraints also

ALTER TABLE Student DROP CONSTRAINT chk_contraint

```
