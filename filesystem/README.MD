# Elastic
Implements store of events, metrics, post events, post metrics in elastic search.

## Configuration
Elastic module requires a specific entry in etc\config.yml as the following

**filesystem**:  
&nbsp;&nbsp;**action1** (downloader, events, metrics, tests, vault, hooks, schedules):  
&nbsp;&nbsp;&nbsp;&nbsp;**basepath**: path of the action1 files  
&nbsp;&nbsp;**action2** (downloader, events, metrics, tests, vault, hooks, schedules):  
&nbsp;&nbsp;&nbsp;&nbsp;**basepath**: path of the action2 files    

## Configuration example  
filesystem:  
&nbsp;&nbsp;downloader:  
&nbsp;&nbsp;&nbsp;&nbsp;basepath: files/downloaders  
&nbsp;&nbsp;events:  
&nbsp;&nbsp;&nbsp;&nbsp;basepath: files/events  
&nbsp;&nbsp;metrics:  
&nbsp;&nbsp;&nbsp;&nbsp;basepath: files/metrics  
&nbsp;&nbsp;tests:  
&nbsp;&nbsp;&nbsp;&nbsp;basepath: files/tests  
&nbsp;&nbsp;vault:  
&nbsp;&nbsp;&nbsp;&nbsp;basepath: files/vault  
&nbsp;&nbsp;hooks:  
&nbsp;&nbsp;&nbsp;&nbsp;path: files/hooks.yml  
&nbsp;&nbsp;schedules:  
&nbsp;&nbsp;&nbsp;&nbsp;basepath: files/schedules  
&nbsp;&nbsp;&nbsp;&nbsp;filename: schedule.properties  