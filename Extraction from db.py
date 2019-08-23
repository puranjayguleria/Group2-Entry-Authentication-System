# -*- coding: utf-8 -*-
"""
Created on Thu Aug 22 17:47:00 2019

@author: Mehak Shandilya
"""

#!/usr/bin/python

import MySQLdb
#var="Mehak"
# Open database connection
db = MySQLdb.connect("localhost","root","root","task" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

sql = '''SELECT * FROM dtask WHERE name = "'''+var+'''"'''
try:
   # Execute the SQL command
   cursor.execute(sql)
  myresult = cursor.fetchall()

for x in myresult:
  print(x)

except:
   print "Error: unable to fecth data"

# disconnect from server
db.close()