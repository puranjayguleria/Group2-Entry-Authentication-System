from flask import Flask
from flask import render_template, url_for, request, redirect
import webbrowser
import sys
import os
import MySQLdb

from Mehak3 import *
from Mehak1 import *
from keras.models import load_model

# os.system("google-chrome google.com")

arguments=sys.argv[1:]

app = Flask(__name__)






@app.route("/1")

def page1():
	return render_template('1.html')


@app.route("/2")

# def page2():
# 	return render_template('2.html')
def page2(x=arguments[0]):
	return render_template('2.html', name=x)


@app.route("/3")

def page3():
	return render_template('3.html')


@app.route("/4")

def page4():
	return render_template('4.html')

@app.route("/5")

def page5():
	return render_template('5.html')

@app.route("/6")

def page6():
	return render_template('6.html')

@app.route("/7")

def page7():
	return render_template('7.html')

@app.route("/todo")
def todo():
	return render_template('ToDo.html')




if arguments[1]=='1':
    webbrowser.open_new("http://127.0.0.1:5000/2")
    Name=arguments[0]
    flag=take_picture("puranjay")
    
    if flag:
        print("Into flag")
        webbrowser.open_new("http://127.0.0.1:5000/5")
        data(Name);
    
    else:
        print("Into else")
        
        webbrowser.open_new("http://127.0.0.1:5000/6")
   	
else:
    webbrowser.open_new("http://127.0.0.1:5000/7")

# @app.route("/todo", methods=['POST', 'GET'])
# def todo():
# 	print(request.method)
# 	if request.method== 'POST':
# 		ftask = request.form['task']
# 		ftask = ftask.encode('ascii')
# 		# print(ftask)
# 		tasks.append(ftask)
# 		print(tasks)
# 		return redirect('todo')
# 	else:	
# 		return render_template('ToDo.html')

# arguments=sys.argv[1:]
# print(arguments)

if __name__ == "__main__":
    app.run(debug=True,use_reloader=False)
    