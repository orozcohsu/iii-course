# Web log random data generating

## history log data
## realtime log data

realtime log:
1. python realtime_data.py  -t "2017-01-01 05:00:00" -p "/var/lib/docker/volumes/hadoop/_data/weblog/log"
2. python realtime_data.py  -t "2017-01-01 05:00:00" 

history log:
1. python realtime_data_history.py  -t "2017-01-01 05:00:00" -p "/var/lib/docker/volumes/hadoop/_data/weblog/history-log" 

files:
1. user_agent.csv: user agent
2. uuid.csv: user id
3. host_url.csv: url and category
4. hivename.csv: uuid and user name
5. inteval.csv: time inteval

folders:
1. es-log: elasticsearch log (hot-dataflow)
2. hadoop-log: hadoop hdfs (cold-dataflow)
3. history-log: history log 
4. log: realtime log
5. SH: ETL shell
6. SQL: ETL SQL
