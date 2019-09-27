# Exercício - Distribuição Beta - Estatística para Computação Aplicada
# Neste exercício, utilizar a distribuição Beta para estimar p de "bom senso"

# Na teoria e estatística da probabilidade, a distribuição Beta é uma família 
# de distribuições de probabilidade contínuas definidas no intervalo [0, 1]. 
# É parametrizado por α e β > 0, que controlam a forma da distribuição. 

# Valores de x para comparação

x1 = 40
x2 = 4
x3 = 400

# Valores de N para comparação
N1 = 100
N2 = 10
N3 = 1000

# Definindo valores de α

alpha1 = x1 + 1
alpha2 = x2 + 1
alpha3 = x3 + 1

# Definindo valores de β
beta1 = N1 - x1 + 1
beta2 = N2 - x2 + 1
beta3 = N3 - x3 + 1

# Gera uma sequência de números
p = seq(0,1, by=0.01)   

# Calcula os resultados
resultado1 = dbeta(p, alpha1, beta1)
resultado2 = dbeta(p, alpha2, beta2)
resultado3 = dbeta(p, alpha3, beta3)

# Gráfico
plot(resultado3, type = "l", lwd=2, col = "#CC0066")
points(resultado2, type = "l", lwd=2, col = "#000000")
points(resultado1, type = "l", lwd=6, col = "#660099")



