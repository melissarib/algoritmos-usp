# inicialmente, importamos a biblioteca que vamos usar
# turtle é a biblioteca gráfica padrão para o Python
import turtle


turtle.shape("turtle")
turtle.bgcolor("blue")
turtle.pensize(1)
turtle.pencolor("white")


# função para criar o floco de neve
def snowflake(tamLado, nivel):
    if nivel == 0:
        # faz com que a 'caneta' se movimente na tela, de acordo com o comprimento
        turtle.forward(tamLado)
        return
    # /= é um atalho de operação. mesma coisa que tamLado = (tamLado/3).
    tamLado = tamLado/3
    snowflake(tamLado, nivel - 1)
    # gira a caneta em um angulo de 60 graus pra esquerda
    turtle.left(60)
    snowflake(tamLado, nivel - 1)
    # gira a caneta em um angulo de 120 graus pra direita
    turtle.right(120)
    snowflake(tamLado, nivel - 1)
    turtle.left(60)
    snowflake(tamLado, nivel - 1)


# funcao principal
if __name__ == "__main__":

    #aconselhado utilizar o tamanho máximo de 390 para nao cortar na tela
    tamanho = int(input("Entre com o tamanho: "))
    #aconselhado usar até quatro níveis para evitar lentidão ao desenhar
    nivel = int(input("Entre com a quantidade de niveis: "))

    turtle.speed(0)  #  definindo a velocidade da tartaruguinha/caneta
    turtle.penup()  # para cima, para não desenhar enquanto estiver em movimento
    turtle.backward(tamanho / 2)
    turtle.pendown()

    for i in range(3):
        #Aqui, como a caneta está pra baixo, irá desenhar ao mover
        snowflake(tamanho, nivel)
        turtle.right(120)
    # Inicia o loop da tartaruguinha. É uma função própria do Thinker.
    turtle.mainloop()
