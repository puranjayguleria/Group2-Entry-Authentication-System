from PIL import Image,ImageFilter
import os

size_96 = (96,96) #changes the size of image
os.chdir("C:/Users/MUJ/Desktop")

for f in os.listdir():
    if f.endswith('.gif'):
        i = Image.open(f)
        fn, text = os.path.splitext(f)
        i.thumbnail(size_96)
        os.chdir("C:/Users/MUJ/Desktop")
        i.convert('RGB').save('{}.jpg'.format(fn))
        print("File extension changed and image saved")