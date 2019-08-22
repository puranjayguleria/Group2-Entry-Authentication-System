# -*- coding: utf-8 -*-
"""
Created on Thu Aug 22 12:28:47 2019

@author: MUJ
"""

import cv2
import sys
from time import sleep

key = cv2.waitKey(1)
webcam = cv2.VideoCapture(0)
sleep(2)
while True:
    
    check,frame = webcam.read()
    print(check)
    print(frame)
    cv2.imshow("Capturing", frame)
    key = cv2.waitKey(1)
    if key == ord('s'):
        cv2.imwrite(filename = 'saved_webcam_img.jpg', img = frame)
        webcam.release()
        print("Image clicked and saved...")
        break
    elif key == ord('q'):
        webcam.release()
        cv2.destroyAllWindows()
        break






