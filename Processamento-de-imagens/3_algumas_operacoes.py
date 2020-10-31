import numpy as np
import cv2

def test():
  img = cv2.imread('image_exemplo_2_5.png', 0)
  imgSum = (img + img + img + img + img)/5
  imgSum2 = imgSum + imgSum
  imgSum3 = imgSum2 + imgSum2
  # imgSum4 = imgSum3 + img
  # imgSum5 = imgSum4 + img

  cv2.imshow('image original', img)
  cv2.imshow('image sum', imgSum)
  cv2.imshow('image sum2', imgSum2)
  cv2.imshow('image sum3', imgSum3)
  # cv2.imshow('image sum4', imgSum4)
  # cv2.imshow('image sum5', imgSum5)
  cv2.waitKey(0)
  cv2.destroyAllWindows()

if __name__ == "__main__":
  # negative()
  test()