import smtplib

from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

msg = MIMEMultipart()
msg['From'] = 'rhythmbalooni1@gmail.com'
msg['To'] = 'cpuranjay@gmail.com'
msg['Subject'] = "Tasks for the day"

body = "Hi\nYour tasks for the day are as follows:\n\n1) 11 AM: Meeting with Senior Manager\n2) 3 PM: Presentation of project report"
msg.attach(MIMEText(body, 'plain'))
server = smtplib.SMTP('smtp.gmail.com', 587)
server.starttls()
server.login('rhythmbalooni1@gmail.com','netmax34')
text = msg.as_string()
server.sendmail(msg['From'], msg['To'], text)
server.quit()