import numpy as np
import cv2

# realça detalhes para visualização
def negative():
  img = cv2.imread('imagens_tif/mammogram.tif', 0)
  imgNegative = 255 - img  # where 255 is white

  cv2.imshow('image original', img)
  cv2.imshow('image negative', imgNegative)
  cv2.waitKey(0)
  cv2.destroyAllWindows()

#  A função logarítmica tem a importante característica
# de comprimir a faixa dinâmica das imagens com grandes
# variações de valores de pixels. 
def log():
  img = cv2.imread('imagens_tif\standard_test_images\DIP3E_Original_Images_CH03\Fig0305(a)(DFT_no_log).tif', 0)
  cv2.imshow('image original', img)

  imgLog = np.uint8(np.log(img))

  limiar = 0.1
  imgFinal = cv2.threshold(imgLog, limiar, 255, cv2.THRESH_BINARY)[1]
  cv2.imshow('limiar= 0.1', imgFinal)

  limiar = 0.5
  imgFinal = cv2.threshold(imgLog, limiar, 255, cv2.THRESH_BINARY)[1]
  cv2.imshow('limiar= 0.5', imgFinal)

  limiar = 1
  imgFinal = cv2.threshold(imgLog, limiar, 255, cv2.THRESH_BINARY)[1]
  cv2.imshow('limiar= 1', imgFinal)

  limiar = 2
  imgFinal = cv2.threshold(imgLog, limiar, 255, cv2.THRESH_BINARY)[1]
  cv2.imshow('limiar= 2', imgFinal)
  cv2.waitKey(0)
  cv2.destroyAllWindows()

def inverse_log():
  img = cv2.imread('imagens_tif\standard_test_images\DIP3E_Original_Images_CH03\Fig0305(a)(DFT_no_log).tif', 0)
  cv2.imshow('image original', img)

  imgLog = np.uint8(np.log(img))

  limiar = 0.1
  imgFinal = cv2.threshold(imgLog, limiar, 255, cv2.THRESH_BINARY_INV)[1]
  cv2.imshow('limiar= 0.1', imgFinal)

  limiar = 0.5
  imgFinal = cv2.threshold(imgLog, limiar, 255, cv2.THRESH_BINARY_INV)[1]
  cv2.imshow('limiar= 0.5', imgFinal)

  limiar = 1
  imgFinal = cv2.threshold(imgLog, limiar, 255, cv2.THRESH_BINARY_INV)[1]
  cv2.imshow('limiar= 1', imgFinal)

  limiar = 2
  imgFinal = cv2.threshold(imgLog, limiar, 255, cv2.THRESH_BINARY_INV)[1]
  cv2.imshow('limiar= 2', imgFinal)
  cv2.waitKey(0)
  cv2.destroyAllWindows()

  # falta fazer a transformacao de potencia (ou gama) 

if __name__ == "__main__":
  # negative()
  # log()
  inverse_log()