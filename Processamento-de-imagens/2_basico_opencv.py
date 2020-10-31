import cv2

def use_read_images():
  print("")
  print("VISUALIZANDO COMO QUE FUNCIONA O CARREGAMENTO DE IMAGENS")
  imgCor = cv2.imread('image_woman1_by_christina_morillo.jpg', cv2.IMREAD_COLOR) # carrega a imagem colorida
  imgCinza = cv2.imread('image_woman2_by_christina_morillo.jpg', cv2.IMREAD_GRAYSCALE) # carrega a imagem cinza
  imgTransp = cv2.imread('image_logo_python.png', cv2.IMREAD_UNCHANGED) # Obtem o canal de transparencia da imagem

  print("    Tamanho da imagem colorida: ", imgCor.shape) # mostra o tamanho da imagem colorida
  print("    Tamanho da imagem cinza: ", imgCinza.shape) # mostra o tamanho da imagem cinza
  print("    Tamanho da imagem transparente: ", imgTransp.shape) # mostra o tamanho da imagem cinza

  # mostra a imagem colorida carregada
  cv2.namedWindow('first_image_loaded', cv2.WINDOW_NORMAL) # ajusta a imagem para o tamanho da janela
  cv2.imshow('first_image_loaded', imgCor) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer
  cv2.destroyAllWindows() # fecha as janelas abertas

  # mostra a imagem cinza carregada
  cv2.namedWindow('first_image_loaded2', cv2.WINDOW_NORMAL) # ajusta a imagem para o tamanho da janela
  cv2.imshow('first_image_loaded2', imgCinza) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer
  cv2.destroyAllWindows() # fecha as janelas abertas

  # mostra a imagem que possui partes transparentes que foi carregada
  cv2.namedWindow('first_image_loaded3') # ajusta a imagem para o tamanho da janela
  cv2.imshow('first_image_loaded3', imgTransp) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer
  cv2.destroyAllWindows() # fecha as janelas abertas

def use_resize_image():
  print("")
  print("VISUALIZANDO COMO QUE FUNCIONA O REDIMENSIONAMENTO DA IMAGEM")
  imgCinza = cv2.imread('image_woman2_by_christina_morillo.jpg', cv2.IMREAD_GRAYSCALE) # carrega a imagem cinza
  print("    Tamanho original da imagem cinza: ", imgCinza.shape) # mostra o tamanho da imagem cinza
  print("        Largura: " , imgCinza.shape[1])
  print("        Altura: " , imgCinza.shape[0])

  scale_percent = 15 # porcentagem do tamanho original
  width = int(imgCinza.shape[1] * scale_percent / 100)
  height = int(imgCinza.shape[0] * scale_percent / 100)
  dim = (width, height) # nova dimensao da imagem 
  print("    Nova dimensao da imagem: ", dim)
  resized = cv2.resize(imgCinza, dim, interpolation = cv2.INTER_AREA)
  cv2.imshow("Imagem com nova dimensao", resized) 
  cv2.waitKey(0)
  cv2.destroyAllWindows()

def use_save_image():
  print("")
  print("VISUALIZANDO COMO QUE FUNCIONA O SALVAMENTO DE UMA IMAGEM")
  imgCor = cv2.imread('image_logo_python.png', cv2.IMREAD_COLOR) # carrega a imagem colorida
  imgCinza = cv2.cvtColor(imgCor, cv2.COLOR_BGR2GRAY) # transforma a imagem colorida em cinza

  # mostra a imagem colorida que foi carregada
  cv2.namedWindow('python logo colorido') 
  cv2.imshow('python logo colorido', imgCor) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a imagem cinza que foi obtida
  cv2.namedWindow('python logo cinza') 
  cv2.imshow('python logo cinza', imgCinza) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  cv2.destroyAllWindows() # fecha as janelas abertas

  status = cv2.imwrite('image_logo_python_saved_by_openCV.png', imgCinza)
  print("    A imagem obteve o status: ", status)

def use_arithmetics_operations_grayscale():
  print("")
  print("VISUALIZANDO COMO QUE FUNCIONA A ADIÇÃO, SUBTRAÇÃO, MULTIPLICAÇÃO E DIVISÃO DE IMAGENS CINZAS")
  
  # Obtendo as imagens
  imgCinza = cv2.imread('image_woman3_lenna.png', cv2.IMREAD_GRAYSCALE) # carrega a imagem
  imgGradient = cv2.imread('image_grayscale_gradient.jpg', cv2.IMREAD_GRAYSCALE) # carrega a imagem
  print("    Tamanho original da imagem cinza: ", imgCinza.shape) # mostra o tamanho da imagem cinza
  print("    Tamanho original da imagem com efeito gradiente: ", imgGradient.shape) 

  # Antes de realizar as operações, as imagens precisam ter os mesmos tamanhos
  dim = (300, 300) # nova dimensao das imagens
  print("    Nova dimensao das imagens: ", dim)
  resizedImgCinza = cv2.resize(imgCinza, dim, interpolation = cv2.INTER_AREA)
  resizedImgGradient = cv2.resize(imgGradient, dim, interpolation = cv2.INTER_AREA)

  # Realizando as operações aritméticas
  imgSum = cv2.add(resizedImgCinza, resizedImgGradient)
  imgSumWeight = cv2.addWeighted(resizedImgCinza,0.7,resizedImgGradient,0.3,0)
  imgSub = cv2.subtract(resizedImgCinza, resizedImgGradient)
  imgMult = cv2.multiply(resizedImgCinza, resizedImgGradient)
  imgDiv = cv2.divide(resizedImgCinza, resizedImgGradient)

  # mostra as imagens originais que foram carregadas
  cv2.namedWindow('Imagem original') 
  cv2.imshow('Imagem original', imgCinza) # mostra a imagem
  cv2.namedWindow('Efeito gradiente') 
  cv2.imshow('Efeito gradiente', imgGradient) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra as imagens redimensionadas que foram obtidas
  cv2.namedWindow('Imagem original redimensionada') 
  cv2.imshow('Imagem original redimensionada', resizedImgCinza) # mostra a imagem
  cv2.namedWindow('Efeito gradiente redimensionado') 
  cv2.imshow('Efeito gradiente redimensionado', resizedImgGradient) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a soma das imagens
  cv2.namedWindow('Imagens Somadas') 
  cv2.imshow('Imagens Somadas', imgSum) # mostra a imagem
  cv2.namedWindow('Imagens Somadas com pesos') 
  cv2.imshow('Imagens Somadas com pesos', imgSumWeight) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a subtração das imagens
  cv2.namedWindow('Imagens Subtraidas') 
  cv2.imshow('Imagens Subtraidas', imgSub) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a multiplicação das imagens
  cv2.namedWindow('Imagens Multiplicadas') 
  cv2.imshow('Imagens Multiplicadas', imgMult) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a divisão das imagens
  cv2.namedWindow('Imagens Divididas') 
  cv2.imshow('Imagens Divididas', imgDiv) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer
  cv2.destroyAllWindows() # fecha as janelas abertas
  
def use_arithmetics_operations_color():
  print("")
  print("VISUALIZANDO COMO QUE FUNCIONA A ADIÇÃO, SUBTRAÇÃO, MULTIPLICAÇÃO E DIVISÃO DE IMAGENS COLORIDAS")
  
  # Obtendo as imagens
  imgCor = cv2.imread('image_woman3_lenna.png', cv2.IMREAD_COLOR) # carrega a imagem
  imgGradient = cv2.imread('image_color_gradient.png', cv2.IMREAD_COLOR) # carrega a imagem
  print("    Tamanho original da imagem cinza: ", imgCor.shape) # mostra o tamanho da imagem cinza
  print("    Tamanho original da imagem com efeito gradiente: ", imgGradient.shape) 

  # Antes de realizar as operações, as imagens precisam ter os mesmos tamanhos
  dim = (300, 300) # nova dimensao das imagens
  print("    Nova dimensao das imagens: ", dim)
  resizedImgCor = cv2.resize(imgCor, dim, interpolation = cv2.INTER_AREA)
  resizedImgGradient = cv2.resize(imgGradient, dim, interpolation = cv2.INTER_AREA)

  # Realizando as operações aritméticas
  imgSum = cv2.add(resizedImgCor, resizedImgGradient)
  imgSumWeight = cv2.addWeighted(resizedImgCor,0.7,resizedImgGradient,0.3,0)
  imgSub = cv2.subtract(resizedImgCor, resizedImgGradient)
  imgMult = cv2.multiply(resizedImgCor, resizedImgGradient)
  imgDiv = cv2.divide(resizedImgCor, resizedImgGradient)

  # mostra as imagens originais que foram carregadas
  cv2.namedWindow('Imagem original') 
  cv2.imshow('Imagem original', imgCor) # mostra a imagem
  cv2.namedWindow('Efeito gradiente') 
  cv2.imshow('Efeito gradiente', imgGradient) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra as imagens redimensionadas que foram obtidas
  cv2.namedWindow('Imagem original redimensionada') 
  cv2.imshow('Imagem original redimensionada', resizedImgCor) # mostra a imagem
  cv2.namedWindow('Efeito gradiente redimensionado') 
  cv2.imshow('Efeito gradiente redimensionado', resizedImgGradient) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a soma das imagens
  cv2.namedWindow('Imagens Somadas') 
  cv2.imshow('Imagens Somadas', imgSum) # mostra a imagem
  cv2.namedWindow('Imagens Somadas com pesos') 
  cv2.imshow('Imagens Somadas com pesos', imgSumWeight) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a subtração das imagens
  cv2.namedWindow('Imagens Subtraidas') 
  cv2.imshow('Imagens Subtraidas', imgSub) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a multiplicação das imagens
  cv2.namedWindow('Imagens Multiplicadas') 
  cv2.imshow('Imagens Multiplicadas', imgMult) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer

  # mostra a divisão das imagens
  cv2.namedWindow('Imagens Divididas') 
  cv2.imshow('Imagens Divididas', imgDiv) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer
  cv2.destroyAllWindows() # fecha as janelas abertas

def add_texto_to_image():
  print("")
  print("VISUALIZANDO COMO QUE FUNCIONA A ADIÇÃO DE TEXTO NAS IMAGENS")
  
  imgCor = cv2.imread('image_woman3_lenna.png', cv2.IMREAD_COLOR) # carrega a imagem
  font = cv2.FONT_HERSHEY_SIMPLEX  
  width = 15
  height = 40
  org = (width, height) 
  fontScale = 1
  color = (255, 0, 0)  # Blue color in BGR 
  thickness = 2 # Line thickness of 2 px 
  imageWithText = cv2.putText(imgCor, 'Texto1 teste.', org, font, fontScale, color, thickness, cv2.LINE_AA, False) 
  imageWithText = cv2.putText(imgCor, 'Texto2 teste.', org, font, fontScale, color, thickness, cv2.LINE_AA, True) # texto de ponta cabeça

  # mostra o resultado
  cv2.namedWindow('Imagem com texto') 
  cv2.imshow('Imagem com texto', imageWithText) # mostra a imagem
  cv2.waitKey(0) # aguarda o pressionamento de uma tecla qualquer
  cv2.destroyAllWindows() # fecha as janelas abertas

if __name__ == "__main__":
  # use_read_images()
  # use_resize_image()
  # use_save_image()
  # use_arithmetics_operations_grayscale()
  # use_arithmetics_operations_color()
  # add_texto_to_image()