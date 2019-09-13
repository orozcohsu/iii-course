#!/bin/sh

#CDATE: YYYY-MM-DD

#FREQ: By day

CDATE=$(date +%Y-%m-%d)

sudo -u hive beeline -u "jdbc:hive2://sandbox.hortonworks.com:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2" --hivevar CDATE=$CDATE -f '/hadoop/weblog/SQL/pmart.sql';
