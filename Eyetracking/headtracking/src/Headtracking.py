import dlib
from imutils import face_utils
import cv2
import time
import math
import pandas as pd
from sklearn.linear_model import LinearRegression
from src.compensate import Compensate
import pyautogui
pyautogui.FAILSAFE = False

class headtracking:
    def __init__(self, path = "./models/shape_predictor_68_face_landmarks.dat",wide=1920,height=1080):
        self.detector = dlib.get_frontal_face_detector()
        self.predictor = dlib.shape_predictor(path)
        self.wide = wide
        self.height = height
        self.target = [(0,30),(wide//2,30),(wide-30,30),(0,height//2),(wide//2,height//2),(wide-30,height//2),(0,height-35),(wide//2,height-35),(wide-30,height-35)] ##1920,1080
        self.train_data_list = [0,1,2,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,39,42,45]
        self.modelx = None
        self.modely = None
        self.mouse_size = None
        self.point = None
    def click_caliborate(self,cap,path = "./img/background.png"):
        start = time.time()
        while(cap.isOpened()):
            background = cv2.imread(path,cv2.IMREAD_ANYCOLOR)
            ret, frame = cap.read()
            frame = cv2.flip(frame, 1)
            gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
            rects = self.detector(gray, 0)
            for (_, rect) in enumerate(rects):
                shape=self.predictor(gray,rect)
                shape=face_utils.shape_to_np(shape)
            now = int(time.time() - start)
            if now>3:
                if cv2.waitKey(1):
                    mouse_size = math.sqrt((shape[51][0]-shape[57][0])**2+(shape[51][1]-shape[57][1])**2)
                    break
            cv2.putText(background,"say ah for 3sec : " + str(now) , (self.wide//2,self.height//2), cv2.FONT_HERSHEY_DUPLEX,1, (147, 58, 31), 1)
            cv2.imshow('Measure',background)

            if cv2.waitKey(1)==13:
                mouse_size = math.sqrt((shape[51][0]-shape[57][0])**2+(shape[51][1]-shape[57][1])**2)
                break
        self.mouse_size = mouse_size
    


    def sight_calibrate(self,cap,path = "./img/background.png"):
        point = []
        for i in range(9):
            start = time.time()
            while(cap.isOpened()):
                background = cv2.imread(path,cv2.IMREAD_ANYCOLOR)
                # Capture frame-by-frame
                ret, frame = cap.read()
                frame = cv2.flip(frame, 1)
                gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
                rects = self.detector(gray, 0)
                for (_, rect) in enumerate(rects):
                    shape=self.predictor(gray,rect)
                    shape=face_utils.shape_to_np(shape)
                #cv2.circle(background,target[i],50,(255,0,0),100)
                now = int(time.time() - start)
                if now>3:
                    if cv2.waitKey(1):
                        point.append(shape)
                        break
                cv2.putText(background,str(now) ,self.target[i], cv2.FONT_HERSHEY_DUPLEX,1, (147, 58, 31), 1)
                cv2.imshow('Measure',background)
                if cv2.waitKey(1)==13:
                    point.append(shape)
                    break
        self.point = point



    def training(self,train_data_list = []):
        if len(train_data_list) == 0:
            train_data_list = self.train_data_list
        else:
            self.train_data_list = train_data_list
        dfx = pd.DataFrame(columns=list(range(len(train_data_list))))
        dfxval = pd.DataFrame(columns=["1"])
        dfy = pd.DataFrame(columns=list(range(len(train_data_list))))
        dfyval = pd.DataFrame(columns=["1"])

        for i in range(9):
            da = self.point[i]
            xarr = []
            yarr = []
            for p in train_data_list:
                x,y = da[p]
                xarr.append(x)
                yarr.append(y)
            dfx.loc[i] = xarr
            dfy.loc[i] = yarr
            dfxval.loc[i] = self.target[i][0]
            dfyval.loc[i] = self.target[i][1]
        modelx = LinearRegression()
        modely = LinearRegression()
        self.modelx = modelx.fit(dfx,dfxval)
        self.modely = modely.fit(dfy,dfyval)



    def validation(self,cap):
        prex = self.wide//2
        prey = self.height//2
        while(cap.isOpened()):
            ret, frame = cap.read()
            frame = cv2.flip(frame, 1)
            gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
            rects = self.detector(gray, 0)
            for (_, rect) in enumerate(rects):
                shape=self.predictor(gray,rect)
                shape=face_utils.shape_to_np(shape)
            now_mouse =math.sqrt((shape[51][0]-shape[57][0])**2+(shape[51][1]-shape[57][1])**2)
            # now_eye = math.sqrt((shape[43][0]-shape[47][0])**2+(shape[43][1]-shape[47][1])**2)
            
            # if now_eye>right_eye_size:
            xarr = []
            yarr = []
            for i in self.train_data_list:
                x_,y_ = shape[i]
                xarr.append(x_)
                yarr.append(y_)      
            x = int(self.modelx.predict([xarr]))
            y = int(self.modely.predict([yarr]))
            com = Compensate(x,y)
            x,y,prex,prey = com.compensate(prex,prey,30,0.001)
            if now_mouse > self.mouse_size:
                pyautogui.click()
            pyautogui.moveTo(x,y)
            cv2.imshow('Measure',frame)
            prex = x
            prey = y
            if cv2.waitKey(1)==13:
                break