# -*- coding: utf-8 -*-
"""
Created on Wed Aug 21 22:44:36 2019

@author: MUJ
"""

from keras.models import Sequential
from keras.layers import Conv2D, ZeroPadding2D, Activation, Input, concatenate
from keras.models import Model
from keras.layers.normalization import BatchNormalization
from keras.layers.pooling import MaxPooling2D, AveragePooling2D
from keras.layers.merge import Concatenate
from keras.layers.core import Lambda, Flatten, Dense
from keras.initializers import glorot_uniform
from keras.engine.topology import Layer
from keras import backend as K
K.set_image_data_format('channels_first')
import cv2
import os
import numpy as np
from numpy import genfromtxt
import pandas as pd
import tensorflow as tf
from fr_utils import *
from inception_blocks_v2 import *

def triplet_loss(y_true, y_pred, alpha = 0.2):
    
    
    anchor, positive, negative = y_pred[0], y_pred[1], y_pred[2]
    
    pos_dist = tf.reduce_sum(tf.square(tf.subtract(anchor,positive)),axis=-1)
    neg_dist =  tf.reduce_sum(tf.square(tf.subtract(anchor,negative)),axis=-1)
    basic_loss = tf.add(tf.subtract(pos_dist,neg_dist),alpha)
    loss = tf.reduce_sum(tf.maximum(basic_loss,0.0))
    
    return loss

def create_model():
    FRmodel = faceRecoModel(input_shape=(3, 96, 96))
    print("Total Params:", FRmodel.count_params())
    
    FRmodel.compile(optimizer = 'adam', loss = triplet_loss, metrics = ['accuracy'])
    load_weights_from_FaceNet(FRmodel)
    
    """ 
    database = {}
    database["anirudh"] = img_to_encoding("image_new//anirudh.jpg", FRmodel)
    print("loaded 1")
    database["p"] = img_to_encoding("image_new//p.jpg", FRmodel)
    database["h"]=img_to_encoding("image_new//h.jpg",FRmodel)
    database["mehak"]=img_to_encoding("image_new//poo.jpg",FRmodel) 
    """
    return FRmodel



def verify(image_path, identity, database, model):
   
 
    
    encoding = img_to_encoding(image_path,model)
    
    dist =  np.linalg.norm(encoding - database[identity])
    
    if dist<0.8:
        print("It's " + str(identity) + ", welcome home!")
        door_open = True
    else:
        print("It's not " + str(identity) + ", please go away")
        door_open = False
        
        
    return dist, door_open


"""import pickle 

file_pi3=open("database.obj",'rb')
database=pickle.load(file_pi3)
"""
"""FRmodel.save("model.xml")
FRmodel_reloaded = pickle.load(file_pi2)
"""
#dist,flag=verify("image_new//test_image.jpg", "h", database, FRmodel)












