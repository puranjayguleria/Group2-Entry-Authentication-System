import os
import cv2

def image_changer():
    #out = pil_im.resize((128,128))
    os.chdir("C:/Users/MUJ/Desktop/TechSys/image_new")
    
    for f in os.listdir():
        if f.endswith('.jpg'):
            i = cv2.imread(f)
            resized = cv2.resize(i, (96,96), interpolation = cv2.INTER_AREA)
            cv2.imwrite(f, resized)
            print("File extension changed and image saved")