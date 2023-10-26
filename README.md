
# Photographer - website
Simple site for booking appointments.

## Table of contents

* [General info](#general-info)
* [Technologies](#Technologies)
* [Setup](#setup)

### General info

Project was created as final project. It was meant to help artist in setting up sessions. 

### Technologies

* Java 17
* SpringBoot 2.7.16
* MySQL 5
* SpringSecurity
* JSP
* jstl

### Setup

First you need to have MySQL installed.
To run this application either open it in IDE for example InteliJ or compile project and run as any other java file.
While database is created you need to insert admin account into.
If value starts with "your_" you need to swap it with yours values.


```mysql
USE photo;
INSERT INTO users( active, first_name, password, phone_number, role, username) VALUES (true, 'your_name', 'your_password', 'your_phone_number', 'ROLE_ADMIN', 'your_username_must_be_email');
```

Note: I suggest password change on website because it will be saved as plane text instead of encrypted.

