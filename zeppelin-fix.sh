#!/bin/bash

rm /opt/zeppelin/lib/jackson-annotations-2.5.0.jar
rm /opt/zeppelin/lib/jackson-core-2.5.3.jar
rm /opt/zeppelin/lib/jackson-databind-2.5.3.jar
cp /opt/spark-2.1.0-bin-hadoop2.7/jars/jackson-annotations-2.6.5.jar /opt/zeppelin/lib
cp /opt/spark-2.1.0-bin-hadoop2.7/jars/jackson-core-2.6.5.jar /opt/zeppelin/lib
cp /opt/spark-2.1.0-bin-hadoop2.7/jars/jackson-databind-2.6.5.jar /opt/zeppelin/lib


rm /opt/zeppelin-0.7.1-bin-all/lib/hadoop-annotations-2.6.0.jar
rm /opt/zeppelin-0.7.1-bin-all/lib/hadoop-auth-2.6.0.jar
rm /opt/zeppelin-0.7.1-bin-all/lib/hadoop-common-2.6.0.jar


cp /opt/spark-2.1.0-bin-hadoop2.7/jars/hadoop-annotations-2.7.3.jar /opt/zeppelin-0.7.1-bin-all/lib
cp /opt/spark-2.1.0-bin-hadoop2.7/jars/hadoop-auth-2.7.3.jar /opt/zeppelin-0.7.1-bin-all/lib
cp /opt/spark-2.1.0-bin-hadoop2.7/jars/hadoop-common-2.7.3.jar /opt/zeppelin-0.7.1-bin-all/lib


/opt/zeppelin/bin/zeppelin-daemon.sh restart
echo "zeppelin was fixed "
