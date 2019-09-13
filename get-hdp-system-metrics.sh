#/bin/bash


mkdir -p /home/hdfs/hdp

sudo -u hdfs hadoop fs -mkdir /user/hdfs/data &> /dev/null

while :
do
	DATE=`date '+%Y-%m-%d %H:%M:%S'`
	echo "$DATE Starting to gethering hdp information"
        filename=$RANDOM
	curl -u admin:admin -H "X-Requested-By: ambari"-X GET http://master:8080/api/v1/clusters/hdp/components/ 2> /dev/null | grep "service_name" | uniq > /home/hdfs/hdp/$filename.log

	DATE=`date '+%Y-%m-%d %H:%M:%S'`
	echo "$DATE hdp-componets is done."
	
	sleep 3

	services=`cat /home/hdfs/hdp/$filename.log | cut -d: -f2 | sed 's/"//g'` 
	rm -r -f /home/hdfs/hdp/$filename.log
	
	for service in $services 
	do
		curl -u admin:admin -H "X-Requested-By: ambari" -X GET http://master:8080/api/v1/clusters/hdp/services/$service?fields=ServiceInfo/state 2> /dev/null > /home/hdfs/hdp/$service.log
	done

	tar zcvf /home/hdfs/hdp/hdp$filename.tar.gz /home/hdfs/hdp &> /dev/null
	sudo -u hdfs hadoop fs -put /home/hdfs/hdp/hdp$filename.tar.gz /user/hdfs/data
	if [ $? -eq 0 ];
	then
		echo "the file hdp$filename.tar.gz has been uploaded to /user/hdfs/data"
	else
		echo "the file hdp$filename.tar.gz was failed"
	fi
	rm -r -f /home/hdfs/hdp/*
	
done
