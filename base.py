from flask import Flask
from flask import render_template
import webbrowser
import sys
import os

# os.system("google-chrome google.com")

app = Flask(__name__)



@app.route("/1")

def page1():
	return render_template('1.html')



@app.route("/2")

def page2(name):
	return render_template('2.html', name=name)


@app.route("/3")

def page3():
	return render_template('3.html')



@app.route("/1f")

def page1fail():
	return render_template('1f.html')



arguments=sys.argv[1:]
# print(arguments)
if(arguments[2]==1):
	# webbrowser.open_new("http://127.0.0.1:5000/2")
	page2(arguments[1])
else:
	webbrowser.open_new("http://127.0.0.1:5000/1f")	

if __name__ == "__main__":
	app.run(debug=True)