# Local Links
--- ---

- [Local Links](http://locallinks2-env.eba-spczqxvp.us-east-2.elasticbeanstalk.com/)

- [Video Demo Presentation](https://youtu.be/vwDCLfiN03U)
## Problem Statement
The problem that this application will solve is the need to know weather forecasts for local golf courses within a
given radius.  This app should pull up all golf courses within a certain square mileage and also display the weather
forecasts for the next 6 hours. For people who want to travel to new golf courses, it would be helpful to get a list
of all your options as well as know if it will rain in that location in the next 6 hours.  This will save people time,
gas, and wasted time.  

## Test Database MYSQL dump

I have provided the DDL to create and insert data into both Favorites and User tables in the test database

- [Favorites Table DDL](test_LocalLinks_favorites.sql)
- [User Table DDL](test_LocalLinks_user.sql)

## Design
- [Figma Mockup](https://www.figma.com/file/I1TPpRz8sZ3vOxRZ0Ss6iw/Local-Links?node-id=0%3A1)
- [Database Design](designDocuments/databaseDesign.md)

## Documentation and Reflection
- [User Stories](designDocuments/userStories.md)
- [Project Timeline](designDocuments/projectTimeline.md)
- [Time Log](designDocuments/timeLog.md)

## Technologies Used

* Security/Authentication
  * AWS Cognito
* Database
  * MySql 8
* ORM Framework
  * Hibernate 5
* Dependency Management
  * Maven
* Web Services Consumed
  * [Google Places](https://developers.google.com/maps/documentation/places/web-service/search)
  * [Google geocoding](https://developers.google.com/maps/documentation/geocoding)
  * [Weather (weatherapi.com)](https://www.weatherapi.com/)
* CSS
  * Bootstrap 5
* Logging
  * Log4J
* Hosting
  * AWS
* Unit Testing
  * JUnit
* IDE
  * IntelliJ IDEA