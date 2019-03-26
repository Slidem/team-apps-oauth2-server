# Team apps authentication server

## Team apps

Team apps category is a set of applications that all share the same authentication server.
The applications in the team apps category are simple applications that are to be used by a team in their office.
The applications can be various and are solely developed for fun amongst the team.

First app I've developed is a simple milk management app. It's focus is to manage who buys milk for coffe in the office :). You can check it out here:

- Milk service (Backend):
- Milk front end:

## The authentication server

The authentication server is a maven project build with java & spring boot2.

## Scope

To be used by all the team apps.

## Technologies used:
- Java 8
- Spring boot 2
- Spring data
- PostgreSQL
- Flyaway (for database migration)

## The login page

The static resources (the actual login page html) are provided by a maven plugin and added when 
the sources are generated.

The login page is a maven artifact packaged as a zip file:

```java
    <artifactItem>
        <groupId>ami.login</groupId>
        <artifactId>login</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <type>tar.gz</type>
        <outputDirectory>${project.build.outputDirectory}/login</outputDirectory>
    </artifactItem>
    
``` 

To see more about the the login page project go here:
https://github.com/Slidem/team-apps-login












