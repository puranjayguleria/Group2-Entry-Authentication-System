# -*- coding: utf-8 -*-
"""
Created on Thu Aug 22 12:28:47 2019

@author: Mehak Shandilya
"""
import pyttsx3
import cv2
import time
from image_change import *
from image_reco_model import *
#from image_reco_model import *
import pickle 
from keras.models import load_model

    
def get_image(camera):
    retval, im = camera.read()
    return im
    
    #for i in xrange(ramp_frames):
        #temp = get_image()
def take_picture(identity):
    camera_port = 0;
    fps = 30;
    ramp_frames = 30;
    
    camera = cv2.VideoCapture(camera_port)    
    print("Taking image...")
    
    time.sleep(4)
    camera_capture = get_image(camera)
    file ="C://Users//MUJ//Desktop//TechSys//backend//image_new//test_image.jpg"
    cv2.imwrite(file,camera_capture)
    
    #time.sleep(4)
      
        
    del(camera)
    
    image_changer()
    #FRmodel=create_model()
    
    

    file_pi3=open("C:\\Users\\MUJ\\Desktop\\TechSys\\backend\\database.obj",'rb')
    database=pickle.load(file_pi3)
   
   
   

# returns a compiled model
# identical to the previous one
   
   
    

    flag=verify("C:\\Users\\MUJ\\Desktop\\TechSys\\backend\\image_new\\test_image.jpg", "p", database)
    
    
    return flag

take_picture("pyh")







