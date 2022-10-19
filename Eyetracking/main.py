#
#   Headpose Detection for Video
#   Written by Qhan
#   Last Update: 2019.1.9
#

import argparse
import cv2
import dlib
from imutils import face_utils
from sklearn.linear_model import LinearRegression
import pandas as pd
import pyautogui
pyautogui.FAILSAFE = False



def lim(x,y,prex,prey,down,up):
    noise_x = x-prex
    noise_y = y-prey
    if x<0:
        x = 0
    if x> 1920:
        x = 1920
    if y<0:
        y =0
    if y>1080:
        y=1080
    if down<abs(noise_x)<up:
        if noise_x>0:
            x = prex+down
        else:
            x = prex-down
    if down<abs(noise_y)<up:
        if noise_y>0:
            y = prey+down
        else:
            y = prey-down
    return x,y,prex,prey

def main():
    background = cv2.imread("background.png",cv2.IMREAD_ANYCOLOR)
    detector = dlib.get_frontal_face_detector()
    predictor = dlib.shape_predictor("./models/shape_predictor_68_face_landmarks.dat")
    cap = cv2.VideoCapture(0)

    w = 1920
    h = 1080
    target = [(0,0),(w//2,0),(w,0),(0,h//2),(w//2,h//2),(w,h//2),(0,h),(w//2,h),(w,h)] ##1920,1080
    point = []


    # 측정
    for i in range(9):
        while(cap.isOpened()):
            # Capture frame-by-frame
            ret, frame = cap.read()
            
            frame = cv2.flip(frame, 1)
            gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
            rects = detector(gray, 0)
            for (_, rect) in enumerate(rects):
                shape=predictor(gray,rect)
                shape=face_utils.shape_to_np(shape)
            #cv2.resizeWindow("Measuer", 1920, 1080)
            cv2.circle(background,target[i],50,(255,0,0),50)
            cv2.imshow('Measure',background)
            if cv2.waitKey(1)==13:
                point.append(shape)
                break
    # When everything done, release the capture
    # 
    # 
    # 
    # 
    # 
    # training  시작
    cap.release()
    cv2.destroyAllWindows()
    dfx = pd.DataFrame(columns=list(range(68)))
    dfxval = pd.DataFrame(columns=["1"])
    dfy = pd.DataFrame(columns=list(range(68)))
    dfyval = pd.DataFrame(columns=["1"])
    for i in range(9):
        da = point[i]
        xarr = []
        yarr = []
        for p in range(68):
            x,y = da[p]
            xarr.append(x)
            yarr.append(y)
        dfx.loc[i] = xarr
        dfy.loc[i] = yarr
        dfxval.loc[i] = target[i][0]
        dfyval.loc[i] = target[i][1]
    

    modelx = LinearRegression()
    modely = LinearRegression()
    modelx.fit(dfx,dfxval)
    modely.fit(dfy,dfyval)





    ##validation 
    prex = w//2
    prey = h//2
    cap = cv2.VideoCapture(0)
    while(cap.isOpened()):
        background = cv2.imread("background.png",cv2.IMREAD_ANYCOLOR)
        ret, frame = cap.read()
        frame = cv2.flip(frame, 1)
        gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
        rects = detector(gray, 0)
        for (_, rect) in enumerate(rects):
            shape=predictor(gray,rect)
            shape=face_utils.shape_to_np(shape)
        xarr = [0 for _ in range(68)]
        yarr = [0 for _ in range(68)]
        for i in range(68):
            x_,y_ = shape[i]
            xarr[i] = x_
            yarr[i] = y_      
        x = int(modelx.predict([xarr]))
        y = int(modely.predict([yarr]))
        x,y,prex,prey = lim(x,y,prex,prey,5,50)
        pyautogui.moveTo(x,y)
        cv2.imshow('Measure',frame)
        prex = x
        prey = y
        if cv2.waitKey(1)==13:
            break

        

    
if __name__ == '__main__':
    main()
