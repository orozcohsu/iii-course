#!/bin/sh

#MDATE: YYYYMMDD
#SDATE: HR

#FREQ: By hour

MDATE=$(date +%Y%m%d)
SDATE=$(date +%H)

sudo -u hive beeline -u "jdbc:hive2://sandbox.hortonworks.com:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2" --hivevar MDATE=$MDATE --hivevar SDATE=$SDATE -f '/hadoop/weblog/SQL/stage.sql';
sudo -u hive beeline -u "jdbc:hive2://sandbox.hortonworks.com:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2" --hivevar MDATE=$MDATE --hivevar SDATE=$SDATE -f '/hadoop/weblog/SQL/pdata.sql';



