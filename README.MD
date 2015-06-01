serial-event-listener-arduino
=========================

###### Prerequisites

[RXTX Java library](http://rxtx.qbang.org/wiki/index.php/Main_Page)

###### QuickStart
```
mvn clean package
java -Djava.library.path=/path/to/rxtx/library -jar target/com.example.arduino-1.0-SNAPSHOT-shaded.jar "portIdentifer"
```
example for linux
```
mvn clean package
java -Djava.library.path=/opt/libs/rxtx-2.2pre2-bins/x86_64-unknown-linux-gnu -jar target/com.example.arduino-1.0-SNAPSHOT-shaded.jar "/dev/ttyACM0"
```
