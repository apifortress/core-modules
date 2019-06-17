# Core Modules
This project contains several modules that can be used by the core server.
Core server implements the following actions
- load a fortress hook
- load a test
- load the vault
- store metrics and events of a test
- store post events and post metrics
- load schedules
- download a test

Every module implements all of only a part of the previous actions
The core server can be configured to use the default http acions or to use a specific module for a specific action
For example the core server can use the file system module to load a test, 
the jdbc module to store metrics and events on a database (mysql, oracle, postgres..) and then send informations to elasti search.

In etc/config.yml the user can configure the module in the proper way.
In core_beans.xml the user can configure spring to use the specific module for a specific action 

Moreover this project contains a simple mule project (eclipse is needed)
The mule project can substitute the fortress dashboard as endpoint.

## Modules
### Elastic  
Implements store of events, metrics, post events, post metrics in elastic search.  
### Filesystem  
Implements load of tests,schedules and vault, store of events and metrics, download of tests on file system.  
### JDBC  
Implements load of tests,schedules and vault, store of events and metrics, download of tests on SQL database  ****



 