import numpy as np
import cv2

"""
  RESUMO TEORICO EM TÓPICOS SOBRE FILTROS ESPACIAIS 
  - Diferença entre correlação e convolução espacial
  - O filtro espacial pode ser linear (ex: média) ou não-linear (ex: máximo)
  - Os filtros podem ser utilizados para suavização e aguçamento, por exemplo. 
  - Os filtros de suavização são utilizados para borramento e redução de ruído.
  - Importante lembrar que operações de conjuntos são operações não-lineares. Ex: União, intersecção, etc.
"""

# ---------------------------------------------------------
#               FILTROS LINEARES
# ---------------------------------------------------------

def meanFilter5x5():
  img = cv2.imread('imagens_tif/africa.tif')

  # Criando uma mascara 5x5 de 1/25 (1/25 = 1/(5*5))
  kernel = np.ones((5,5), dtype=np.float32)/25
  imgResult = cv2.filter2D(img, -1, kernel)

  cv2.imshow('image original', img)
  cv2.imshow('5x5 Mean Filter', imgResult)
  cv2.waitKey(0)
  cv2.destroyAllWindows()

def meanFilter3x3():
  img = cv2.imread('imagens_tif/africa.tif')

  # Criando uma mascara 3x3 de 1/9 (1/9 = 1/(3*3))
  kernel = np.ones((3,3), dtype=np.float32)/9
  imgResult = cv2.filter2D(img, -1, kernel)

  cv2.imshow('image original', img)
  cv2.imshow('3x3 Mean Filter', imgResult)
  cv2.waitKey(0)
  cv2.destroyAllWindows()

# n é o tamanho da mascara (kernel)
def meanFilterNxN(n):
  img = cv2.imread('imagens_tif/africa.tif')

  # Criando uma mascara NxN de 1/N^2 
  kernel = np.ones((n,n), dtype=np.float32)/(n*n)
  imgResult = cv2.filter2D(img, -1, kernel)

  # Mostra o resultado
  cv2.namedWindow('Result with n ' + str(n), cv2.WINDOW_NORMAL) # ajusta a imagem para o tamanho da janela
  cv2.imshow('Result with n ' + str(n), imgResult)

# A mascara (kernel) que tem maior peso no pixel central. 
# Os menores pesos são as posições mais distantes do centro.
def meanWeightFilter3x3():
  img = cv2.imread('imagens_tif/africa.tif')

  # kernel = np.ones((3,3), dtype=np.float32)
  kernel = np.array([ [1.0, 2.0, 1.0],
                      [2.0, 4.0, 2.0],
                      [1.0, 2.0, 1.0]], dtype=np.float32)
  kernel = kernel/(np.sum(kernel)) # dividindo pelos pesos

  # Fazendo a convolução da mascara na imagem (aplicando a mascara)
  imgResult = cv2.filter2D(img, -1, kernel)

  # Mostra o resultado
  cv2.imshow('Original', img)
  cv2.imshow('Result', imgResult)
  cv2.waitKey(0)
  cv2.destroyAllWindows()


# ---------------------------------------------------------
#               FILTROS NAO LINEARES
# ---------------------------------------------------------

# Filtro que possui mascara NxN e que obtem o maior valor da vizinhança
def maximumBoxFilter(n):
  img = cv2.imread('imagens_tif/africa.tif')

  # Criando a forma da mascara
  size = (n,n)
  shape = cv2.MORPH_RECT
  kernel = cv2.getStructuringElement(shape, size)

  # Aplicando o filtro de mínimo com uma mascara (kernel) NxN
  imgResult = cv2.dilate(img, kernel)

  # Mostra o resultado
  cv2.namedWindow('Result with n ' + str(n), cv2.WINDOW_NORMAL) # ajusta a imagem para o tamanho da janela
  cv2.imshow('Result with n ' + str(n), imgResult)

# Filtro que possui mascara NxN e que obtem o menor valor da vizinhança
def minimumBoxFilter(n):
  img = cv2.imread('imagens_tif/africa.tif')

  # Criando a forma da mascara
  size = (n, n)
  shape = cv2.MORPH_RECT
  kernel = cv2.getStructuringElement(shape, size)

  # Aplicando o filtro de mínimo com uma mascara (kernel) NxN
  imgResult = cv2.erode(img, kernel)

  # Mostra o resultado
  cv2.namedWindow('Result with n ' + str(n), cv2.WINDOW_NORMAL) # ajusta a imagem para o tamanho da janela
  cv2.imshow('Result with n ' + str(n), imgResult)

def medianFilter():
  img = cv2.imread('imagens_tif/africa.tif')

  imgResult = cv2.medianBlur(img, ksize=5) # ksize é o tamanho da mascara (kernel)

  # Mostra o resultado
  cv2.imshow('Original', img)
  cv2.imshow('Result', imgResult)
  cv2.waitKey(0)
  cv2.destroyAllWindows()

if __name__ == "__main__":
  # meanFilter5x5()
  # meanFilter3x3()

  # Testando o filtro com diferentes tamanhos de mascaras
  # meanFilterNxN(3)
  # meanFilterNxN(5)
  # meanFilterNxN(7)
  # cv2.waitKey(0)
  # cv2.destroyAllWindows()

  # meanWeightFilter3x3()
  # medianFilter()
  
  # Testando o filtro minimo com diferentes tamanhos de mascaras
  minimumBoxFilter(3)
  minimumBoxFilter(5)
  minimumBoxFilter(7)
  minimumBoxFilter(11)
  cv2.waitKey(0)
  cv2.destroyAllWindows()

  # Testando o filtro maximo com diferentes tamanhos de mascaras
  maximumBoxFilter(3)
  maximumBoxFilter(5)
  maximumBoxFilter(7)
  maximumBoxFilter(11)
  cv2.waitKey(0)
  cv2.destroyAllWindows()