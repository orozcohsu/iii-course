#!/bin/bash

cd /tmp/weblog/log;
FILES=$(find $dir -not -empty|sed -e '1,1d')

for f in $FILES
do
  mv $f -f /tmp/weblog/hadoop-log
done

cd /tmp/weblog/hadoop-log
FILES=$(find $dir -not -empty|sed -e '1,1d')

for i in $FILES
do
	mdate=$(echo $i | cut -c3-10)
	sdate=$(echo $i | cut -c11-12)
	sudo -u hive hadoop fs -mkdir -p /user/hive/prestage/weblog/$mdate/$sdate
        #For Ranger
        sudo -u hive hadoop fs -chmod -R 777  /user/hive/prestage/weblog
	sudo -u hive hadoop fs -copyFromLocal -f $i /user/hive/prestage/weblog/$mdate/$sdate
done

