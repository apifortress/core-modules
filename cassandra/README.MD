# CASSANDRA
Implements store of events and metrics in cassandra database

## Configuration
Cassandra module requires a specific entry in etc\config.yml as the following

**cassandra**:  
&nbsp;&nbsp;**action1** (downloader, events, metrics, tests, vault, hooks, schedules):  
&nbsp;&nbsp;&nbsp;&nbsp;**host**: database host (ex. **localhost**)  
&nbsp;&nbsp;&nbsp;&nbsp;**port**: database port (ex **9042**)  
&nbsp;&nbsp;&nbsp;&nbsp;**user**: database user (ex **cassandra**)   
&nbsp;&nbsp;&nbsp;&nbsp;**password**: database password (ex **cassandra**)  
&nbsp;&nbsp;&nbsp;&nbsp;**keyspace**: database keyspace (ex **apiftest**)  
&nbsp;&nbsp;&nbsp;&nbsp;**table**: database table (ex **events**)    

## Configuration Example  
cassandra:  
&nbsp;&nbsp;&nbsp;events:  
&nbsp;&nbsp;&nbsp;&nbsp;host: localhost  
&nbsp;&nbsp;&nbsp;&nbsp;port: 9042  
&nbsp;&nbsp;&nbsp;&nbsp;user: cassandra 
&nbsp;&nbsp;&nbsp;&nbsp;password: cassandra  
&nbsp;&nbsp;&nbsp;&nbsp;keyspace: apiftest  
&nbsp;&nbsp;&nbsp;&nbsp;table: events  
&nbsp;&nbsp;metrics:  
&nbsp;&nbsp;&nbsp;&nbsp;host: localhost  
&nbsp;&nbsp;&nbsp;&nbsp;port: 9042  
&nbsp;&nbsp;&nbsp;&nbsp;user: cassandra  
&nbsp;&nbsp;&nbsp;&nbsp;password: cassandra  
&nbsp;&nbsp;&nbsp;&nbsp;keyspace: apiftest  
&nbsp;&nbsp;&nbsp;&nbsp;table: metrics
  