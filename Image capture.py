# -*- coding: utf-8 -*-
"""
Created on Thu Aug 22 12:28:47 2019

@author: Mehak Shandilya
"""

import cv2
import time

camera_port = 0;
fps = 30;
ramp_frames = 30;

camera = cv2.VideoCapture(camera_port)

def get_image():
    retval, im = camera.read()
    return im

#for i in xrange(ramp_frames):
    #temp = get_image()
print("Taking image...")
time.sleep(4)
camera_capture = get_image()
file ="test_image.jpg"
cv2.imwrite(file,camera_capture)

time.sleep(4)
    
    
del(camera)




