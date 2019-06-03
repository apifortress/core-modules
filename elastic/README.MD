# Elastic
Implements store of events, metrics, post events, post metrics in elastic search.

## Configuration
Elastic module requires a specific entry in etc\config.yml as the following

**elastic**:  
&nbsp;&nbsp;**action1**: (events, metrics,postevents, postmetrics)  
&nbsp;&nbsp;&nbsp;&nbsp;**host**: host of elastic search (ex localhost)  
&nbsp;&nbsp;&nbsp;&nbsp;**port1**: elastic port 1  
&nbsp;&nbsp;&nbsp;&nbsp;**port2**: elastic port 2  
&nbsp;&nbsp;&nbsp;&nbsp;**scheme**: elastic schema (ex http)  
&nbsp;&nbsp;&nbsp;&nbsp;**id**: custom object id (ex. _id)  
&nbsp;&nbsp;v**index**: custome object index (ex. apifortressevents)  

## Configuration example  

elastic:  
&nbsp;&nbsp;events:  
&nbsp;&nbsp;&nbsp;&nbsp;host: localhost  
&nbsp;&nbsp;&nbsp;&nbsp;port1: 9200  
&nbsp;&nbsp;&nbsp;&nbsp;port2: 9201  
&nbsp;&nbsp;&nbsp;&nbsp;scheme: http  
&nbsp;&nbsp;&nbsp;&nbsp;id: _id  
&nbsp;&nbsp;&nbsp;&nbsp;index: fortressevents  
&nbsp;&nbsp;eventspost:  
&nbsp;&nbsp;&nbsp;&nbsp;host: localhost  
&nbsp;&nbsp;&nbsp;&nbsp;port1: 9200  
&nbsp;&nbsp;&nbsp;&nbsp;port2: 9201  
&nbsp;&nbsp;&nbsp;&nbsp;scheme: http  
&nbsp;&nbsp;&nbsp;&nbsp;id: _id  
&nbsp;&nbsp;&nbsp;&nbsp;index: fortresseventspost  
&nbsp;&nbsp;metrics:  
&nbsp;&nbsp;&nbsp;&nbsp;host: localhost  
&nbsp;&nbsp;&nbsp;&nbsp;port1: 9200  
&nbsp;&nbsp;&nbsp;&nbsp;port2: 9201  
&nbsp;&nbsp;&nbsp;&nbsp;scheme: http  
&nbsp;&nbsp;&nbsp;&nbsp;id: _id  
&nbsp;&nbsp;&nbsp;&nbsp;index: fortressmetrics  
&nbsp;&nbsp;metricspost:  
&nbsp;&nbsp;&nbsp;&nbsp;host: localhost  
&nbsp;&nbsp;&nbsp;&nbsp;port1: 9200  
&nbsp;&nbsp;&nbsp;&nbsp;port2: 9201  
&nbsp;&nbsp;&nbsp;&nbsp;scheme: http  
&nbsp;&nbsp;&nbsp;&nbsp;id: _id  
&nbsp;&nbsp;&nbsp;&nbsp;index: fortressmetricspost  
    
  