# -*- coding: utf-8 -*-
"""
Created on Thu Aug 22 15:07:13 2019

@author: MEHAK
"""

from mysql.connector import MySQLConnection, Error
from python_mysql_dbconfig import read_db_config
 
def insert_task(title, empid):
    query = "INSERT INTO tasks(task,empid) " \
            "VALUES(%s,%s)"
    args = (title, isbn)
 
    try:
        db_config = read_db_config()
        conn = MySQLConnection(**db_config)
 
        cursor = conn.cursor()
        cursor.execute(query, args)
 
        if cursor.lastrowid:
            print('last insert id', cursor.lastrowid)
        else:
            print('last insert id not found')
 
        conn.commit()
    except Error as error:
        print(error)
 
    finally:
        cursor.close()
        conn.close()
 
def main():
   insert_task('Meeting at 12','169108086')
 
if __name__ == '__main__':
    main()