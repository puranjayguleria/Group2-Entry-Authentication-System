# -*- coding: utf-8 -*-
"""
Created on Thu Aug 22 15:07:13 2019

@author: Mehak Shandilya
"""

from mysql.connector import MySQLConnection, Error
#!/usr/bin/python

import MySQLdb
emp_name = 'Puranjay'
emp_task = 'Recruitment'
emp_email="cpuranjay@gmail.com"

# Open database connection
db = MySQLdb.connect("localhost","root","iamlegend.224340","task" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

# Prepare SQL query to INSERT a record into the database.
sql = '''insert into task.dtask values("'''+ emp_name +'''","'''+ emp_task +'''",'''+'''"'''+ emp_email+'''"''' + ''')'''
print(sql)
try:
   # Execute the SQL command
   cursor.execute(sql)
   print("ABC")
   # Commit your changes in the database
   db.commit()
except:
   # Rollback in case there is any error
   db.rollback()

# disconnect from server
db.close()