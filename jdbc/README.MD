# JDBC
Implements load of tests,schedules and vault, store of events and metrics, download of tests on SQL database

## Configuration
Jdbc module requires a specific entry in etc\config.yml as the following

**jdbc**:  
&nbsp;&nbsp;**action1** (downloader, events, metrics, tests, vault, hooks, schedules):  
&nbsp;&nbsp;&nbsp;&nbsp;**driver**: sql database driver (ex. **org.postgresql.Driver**)  
&nbsp;&nbsp;&nbsp;&nbsp;**url**: database url (ex **jdbc:postgresql://localhost/postgres**)  
&nbsp;&nbsp;&nbsp;&nbsp;**properties**:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**user**: valid database user  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**password**: user password  
&nbsp;&nbsp;&nbsp;&nbsp;**table**: action1 database table (ex. **tests**)  
&nbsp;&nbsp;**action2** (downloader, events, metrics, tests, vault, hooks, schedules):  
&nbsp;&nbsp;&nbsp;&nbsp;**driver**: sql database driver (ex. **org.postgresql.Driver**)  
&nbsp;&nbsp;&nbsp;&nbsp;**url**: database url (ex **jdbc:postgresql://localhost/postgres**)  
&nbsp;&nbsp;&nbsp;&nbsp;**properties**:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**user**: valid database user  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**password**: user password  
&nbsp;&nbsp;&nbsp;&nbsp;**table**: action2 database table (ex. **tests**)  

## Configuration Example  
jdbc:  
&nbsp;&nbsp;downloader:  
&nbsp;&nbsp;&nbsp;&nbsp;driver: org.postgresql.Driver  
&nbsp;&nbsp;&nbsp;&nbsp;url: jdbc:postgresql://localhost/postgres  
&nbsp;&nbsp;&nbsp;&nbsp;properties:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;user: postgres  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password: docker  
&nbsp;&nbsp;&nbsp;&nbsp;table: downloader  
&nbsp;&nbsp;events:  
&nbsp;&nbsp;&nbsp;&nbsp;driver: org.postgresql.Driver  
&nbsp;&nbsp;&nbsp;&nbsp;url: jdbc:postgresql://localhost/postgres  
&nbsp;&nbsp;&nbsp;&nbsp;properties:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;user: postgres  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password: docker  
&nbsp;&nbsp;&nbsp;&nbsp;table: events  
&nbsp;&nbsp;metrics:  
&nbsp;&nbsp;&nbsp;&nbsp;driver: org.postgresql.Driver  
&nbsp;&nbsp;&nbsp;&nbsp;url: jdbc:postgresql://localhost/postgres  
&nbsp;&nbsp;&nbsp;&nbsp;properties:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;user: postgres  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password: docker  
&nbsp;&nbsp;&nbsp;&nbsp;table: metrics  
&nbsp;&nbsp;tests:  
&nbsp;&nbsp;&nbsp;&nbsp;driver: org.postgresql.Driver  
&nbsp;&nbsp;&nbsp;&nbsp;url: jdbc:postgresql://localhost/postgres  
&nbsp;&nbsp;&nbsp;&nbsp;properties:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;user: postgres  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password: docker  
&nbsp;&nbsp;&nbsp;&nbsp;table: tests  
&nbsp;&nbsp;vault:  
&nbsp;&nbsp;&nbsp;&nbsp;driver: org.postgresql.Driver  
&nbsp;&nbsp;&nbsp;&nbsp;url: jdbc:postgresql://localhost/postgres  
&nbsp;&nbsp;&nbsp;&nbsp;properties:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;user: postgres  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password: docker  
&nbsp;&nbsp;&nbsp;&nbsp;table: vault  
&nbsp;&nbsp;hooks:  
&nbsp;&nbsp;&nbsp;&nbsp;driver: org.postgresql.Driver  
&nbsp;&nbsp;&nbsp;&nbsp;url: jdbc:postgresql://localhost/postgres  
&nbsp;&nbsp;&nbsp;&nbsp;properties:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;user: postgres  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password: docker  
&nbsp;&nbsp;&nbsp;&nbsp;table: hooks  
&nbsp;&nbsp;schedules:  
&nbsp;&nbsp;&nbsp;&nbsp;driver: org.postgresql.Driver  
&nbsp;&nbsp;&nbsp;&nbsp;url: jdbc:postgresql://localhost/postgres  
&nbsp;&nbsp;&nbsp;&nbsp;properties:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;user: postgres  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password: docker  
&nbsp;&nbsp;table: schedules  
  