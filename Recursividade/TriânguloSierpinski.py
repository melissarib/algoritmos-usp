import turtle
tortuga=turtle.Turtle()

def sierp(x,y):
        if y==0:
                tortuga.shape("turtle")
                tortuga.color("green")
                tortuga.begin_fill()
                tortuga.forward(x)
                tortuga.left(120)
                tortuga.forward(x)
                tortuga.left(120)
                tortuga.forward(x)
                tortuga.left(120)
                tortuga.end_fill()
        else:
                sierp(x/2,y-1)
                tortuga.forward(x/2)
                sierp(x/2,y-1)
                tortuga.left(120)
                tortuga.forward(x/2)
                tortuga.right(120)
                sierp(x/2,y-1)
                tortuga.right(120)
                tortuga.forward(x/2)
                tortuga.left(120)

window = turtle.Screen()
tortuga.speed(3)
sierp(200,3)
window.exitonclick()
