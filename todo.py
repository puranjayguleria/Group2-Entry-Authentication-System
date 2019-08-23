from flask import render_template, url_for, request, redirect
from flask import Flask
import webbrowser
import sys
import os
from Mehak2 import *

app = Flask(__name__)

@app.route("/todo", methods=['POST', 'GET'])
def todo():
	if request.method== 'POST':
		name = request.form['name']
		f_task = request.form['task']
		f_email = request.form['emails']
		name = name.encode('ascii')
		f_task = f_task.encode('ascii')
		f_email = f_email.encode('ascii')
		# # print(ftask)
		# tododata[f_email]=f_task
		# print(tododata)
		# print(name, f_task, f_email)
		insertintodb((name, f_task, f_email))
		return redirect('todo')
	else:	
		return render_template('ToDo.html')

if __name__ == "__main__":
    app.run(debug=True,use_reloader=False)