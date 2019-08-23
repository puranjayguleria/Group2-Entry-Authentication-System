# -*- coding: utf-8 -*-
"""
Created on Thu Aug 22 17:47:00 2019

@author: Mehak Shandilya
"""
from tasks_mail import *
#!/usr/bin/python
def data(var):
    import MySQLdb
    var="Puranjay"
    # Open database connection
    db = MySQLdb.connect("localhost","root","iamlegend.224340","task" )
    
    # prepare a cursor object using cursor() method
    cursor = db.cursor()
    
    
    sql = '''SELECT * FROM dtask WHERE name = "'''+var+'''"'''
    try:
       # Execute the SQL command
       cursor.execute(sql)
       myresult = cursor.fetchall()
       print(myresult)
       
    
    except:
       print ("Error: unable to fetch data")
    
    # disconnect from server
    
    db.close()
    send_mail()
    