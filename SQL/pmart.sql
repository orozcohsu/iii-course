CREATE DATABASE IF NOT EXISTS PMART;

USE PMART;

CREATE TABLE IF NOT EXISTS PMART.M_WEBLOG               
(                                                                
DT TIMESTAMP COMMENT 'page view datetime'
,URL STRING COMMENT 'page view url'
,UA STRING COMMENT 'user agent'
,CAT1 STRING COMMENT 'category1'
,CAT2 STRING COMMENT 'category2'
,CAT3 STRING COMMENT 'category3'
,CAT4 STRING COMMENT 'category4'
,CAT5 STRING COMMENT 'category5'
,NAME STRING COMMENT 'user name'                     
)                                                               
COMMENT 'PMART page view log'  
PARTITIONED BY 
(
PARTITION_CDATE VARCHAR(10) COMMENT 'page view partition'
)                          
STORED AS ORC TBLPROPERTIES("ORC.COMPRESS"="SNAPPY");

--CDATE: 2017-07-10 (YYYY-MM-DD)
ALTER TABLE PMART.M_WEBLOG DROP IF EXISTS PARTITION (PARTITION_CDATE='${CDATE}');

SET hive.exec.dynamic.partition.mode=nonstrict;
SET hive.exec.dynamic.partition=true;

INSERT INTO PMART.M_WEBLOG PARTITION(PARTITION_CDATE)
SELECT
A.DT 
,A.URL
,A.UA
,B.CAT1
,B.CAT2
,B.CAT3
,B.CAT4
,B.CAT5
,C.NAME
,DATE_SUB(A.DT,0) AS PARTITION_DT
FROM PDATA.P_WEBLOG A
JOIN PDATA.CATEGORY B ON A.URL = B.URL
JOIN PDATA.PROFILE C ON A.UUID = C.UUID;
