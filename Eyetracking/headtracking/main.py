#
#   Headpose Detection for Video
#   Written by Qhan
#   Last Update: 2019.1.9
#
import cv2
from src.Headtracking import headtracking

def main():
    cap = cv2.VideoCapture(0)
    hdt = headtracking()
    #클릭 측정   
    hdt.click_caliborate(cap)
    #측정
    hdt.sight_calibrate(cap)
    # When everything done, release the capture
    # 
    # 
    # 
    # 
    # 
    # training  시작
    train_data_list = [0,1,2,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,39,42,45]
    
    hdt.training(train_data_list)




    ##validation
    hdt.validation(cap)

    cap.release()
    cv2.destroyAllWindows()        

    
if __name__ == '__main__':
    main()
