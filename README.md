# 2021-spring-cs160-team-Warlocks

HOW TO SETUP ENVIRONMENT

ECLIPSE/INTELLIJ
1. Copy contents of backend directory into folder called prettyboxes
2. Open Eclipse/IntelliJ and import prettyboxes as Java project.
3. Run pom.xml with Maven Install

MYSQL WORKBENCH
1. Go to Data Import/Restore
2. Click on Import from self-contained file and select prettybox.sql
3. Import to new schema "prettyboxes"
4. Go to Users and Privileges
5. Create new user called "springuser" with password "Password!"
6. Under springuser go to schema priveleges
7. Click on add entry and select prettyboxes as selected schema
8. Click on select all to add all permissions to springuser

Once all steps above are completed, go to Eclipse/IntelliJ and run Application.java and PrettyBoxes homepage should display on localhost:8080
