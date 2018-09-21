# RoboCode Maven Starter

## To start a new RoboCode robot project

- clone this project
- change the package and class name from "teamname.RobotName" to whatever's appropriate
- rename the properites file from teamname/RobotName.properties to agree with the class name
- inside the properties file, set robot.classname to the fully qualified classname of your Robot
- for good measure, in the pom.xml change the groupId to your package name and the artifactId to you Robot class name.

For what it's worth, maybe not all of these changes are required, but it seems to work.

## To package the Robot

```
mvn package
```

