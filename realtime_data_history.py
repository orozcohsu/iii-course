#!/usr/bin/python

# Realtime log generating
# Date: 2017-07-07
# Author: Orozco Hsu
# Organization: DataService   

# Sample: python realtime_data.py  -t "2017-01-01 05:00:00" -p "hdfs://192.168.100.1/data"
#         python realtime_data.py  -t "2017-01-01 05:00:00" -p "/var/lib/docker/volumes/hadoop/_data/weblog"

import sys, os, getopt
import csv
import random
import logging
import datetime, time

from time import gmtime, strftime


def usage():
	print('Usage: %s [OPTIONS] \n\nDataService: Simple program that generates behavior log for a realtime simulation\n\nOptions:\n'  \
	      '  -t TIMESTAMP YYYY-MM-DD HH:MM:SS\n  -o HDFS TEXT The HDFS URI\n  -h Show this message' % sys.argv[0]);  
			
# read url log
def url():
	url = []
	with open('host_url.csv') as csvfile:
		spamreader = csv.reader(csvfile, delimiter=',')
		for row in spamreader:
			url.append(row[5])
	return url

# read user agent
def ua():
	ua = []
	with open('user_agent.csv') as csvfile:
		spamreader = csv.reader(csvfile, delimiter='!')
		for row in spamreader:
			ua.append(row[0])	
	return ua

# read uuid
def uuid():
	uuid = []
	with open('uuid.csv') as csvfile:
		spamreader = csv.reader(csvfile, delimiter=',')
		for row in spamreader:
			uuid.append(row[0])		
	return uuid

# read time interval
def interval():
	interval = []
	with open('inteval.csv') as csvfile:
		spamreader = csv.reader(csvfile, delimiter=' ')
		for row in spamreader:
			interval.extend([row[1]])	
	return interval

# output file
class output:
	
	def __init__(self, ua, url, uuid, sDate, tPath, fPath, tt):	
		self.ua = ua
		self.url = url
		self.uuid = uuid
		self.sDate = sDate
		self.tPath = tPath
		self.fPath = fPath
		self.tt = tt
		
	def result(self):
		
		#Note: using function internal timestamp		
		ts = time.mktime(datetime.datetime.strptime(self.sDate, "%Y-%m-%d %H:%M:%S").timetuple())
		
		while True:		
			if(self.tt==0):
				with open(self.tPath, "a") as logfile:	
			
					k = random.randint(2,20)
					time.sleep(k)

					ts += k
					self.sDate = strftime("%Y-%m-%d %H:%M:%S", gmtime(ts))

					# how many logs in k second
					for f in range(random.randint(10,100)):	
						log = strftime(self.sDate + "|" + random.choice(self.url) + "|" + \
						random.choice(self.ua) + "|" + random.choice(self.uuid))
						logging.info(log);
						logfile.write(log + "\n")	
				
					self.tPath = strftime("%Y%m%d%H%M%S.csv", gmtime(ts))


			if(self.tt==2):
                                with open(self.tPath, "a") as logfile:

                                        k = random.randint(1,2)
                                        time.sleep(k)

                                        ts += k
                                        self.sDate = strftime("%Y-%m-%d %H:%M:%S", gmtime(ts))

                                        # how many logs in k second
                                        for f in range(random.randint(5,10)):
                                                log = strftime(self.sDate + "|" + random.choice(self.url) + "|" + \
                                                random.choice(self.ua) + "|" + random.choice(self.uuid))
                                                logging.info(log);
                                                logfile.write(log + "\n")

                                        self.tPath = self.fPath + "/" + strftime("%Y%m%d%H%M%S.csv", gmtime(ts))


				
def main(argv):
	
	logging.basicConfig(filename='weblog.log',level=logging.INFO,
        format='%(asctime)s.%(msecs)03d %(levelname)s %(module)s - %(funcName)s: %(message)s', datefmt="%Y-%m-%d %H:%M:%S")
	logging.info('Initial job...')

	# start time
	startTime = None
		
	# hdfs filename
	tmpFile = None
	
	# full path
	tPath = None
	
	# arg path
	fPath = None
	
	# target type
	tt = None
	
	try:  	
		opts, args = getopt.getopt(sys.argv[1:], "t:p:hv",["datetime=","path=","help"])
		
		for opt, arg in opts:
			if opt in ('-h', '--help'):  
				usage();  
				sys.exit(1);  
				
			elif opt in ('-t', '--datetime'):  
				startTime = arg
				ts = time.mktime(datetime.datetime.strptime(startTime, "%Y-%m-%d %H:%M:%S").timetuple())
				tmpFile = strftime("%Y%m%d%H%M%S.csv", gmtime(ts))
				
			elif opt in ('-p', '--path'):
				fPath = arg
				
		try:
			if(startTime==None):				 
				#asia time
				startTime = strftime("%Y-%m-%d %H:%M:%S", gmtime(time.time()))
				ts = time.mktime(datetime.datetime.strptime(startTime, "%Y-%m-%d %H:%M:%S").timetuple())
				tmpFile = strftime("%Y%m%d%H%M%S.csv", gmtime(ts))
				
			if(fPath==None):
				tPath = os.getcwd() + "/" + tmpFile
				tt = 0
			elif(fPath.startswith('hdfs:')):
				tPath = fPath + "/" + tmpFile
				tt = 1
			else:
				tPath = fPath + "/" + tmpFile
				tt = 2
			
			#print "tPath:", tPath
			
			logging.info('Starting...')
			out = output(ua(), url(), uuid(), startTime, tPath, fPath, tt)
			out.result()
			logging.info('Finished.')
				
		except EOFError:
			logging.error("Oops!  That was no valid output path.");  
			print("Oops!  That was no valid output path.")
			sys.exit(1);
			
	except getopt.GetoptError:  
		logging.error("Oops!  That was no valid input.");
		print("Oops!  That was no valid input.  Try again...");   

        sys.exit(1);
		

if __name__ == "__main__":
   main(sys.argv[1:])
