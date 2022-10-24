import math

class Compensate():
    def __init__(self,x,y,wide = 1920,height = 1080):
        self.wide = wide
        self.height = height
        if x<0:
            self.x = 0
        elif x> self.wide:
            self.x = self.wide
        else:
            self.x = x
        if y<0:
            self.y = 0
        elif y> self.wide:
            self.y = self.wide
        else:
            self.y = y

    @staticmethod
    def activate(x,move,sensitivity):
        return move * (1-math.exp(-x * sensitivity))

    
    def compensate(self,prex,prey,move = 30,sensitivity = 0.001):
        noise_x = self.x-prex
        noise_y = self.y-prey
        if noise_x>0:
            self.x = prex + self.activate(abs(noise_x),move,sensitivity)
        elif noise_x<0:
            self.x = prex - self.activate(abs(noise_x),move,sensitivity)
        if noise_y>0:
            self.y = prey + self.activate(abs(noise_x),move,sensitivity)
        elif noise_y<0:
            self.y = prey - self.activate(abs(noise_x),move,sensitivity)
        return self.x,self.y,prex,prey
