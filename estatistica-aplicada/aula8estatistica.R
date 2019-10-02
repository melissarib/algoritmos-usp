# Estatística para Computação Aplicada - aula 8
# Tópico: Distribuição Normal Multivariada
# Como visualizar a distribuição normal multivariada com elipses? 

# A distribuição normal multivariada tem uma ampla gama de aplicações em estatística. 
# Exemplos incluem análise de componentes principais, regressão multivariada, análise
# de variância multivariada, análise fatorial entre outras. 

# Implementar a distribuição multivarida em R envolve ao menos três tarefas computacionais.
# São elas: Cálculo do determinante de Σ; Multiplicação de matrizes; Inversa da matriz Σ.

#código rvencio 
dnormmulti = function(x, m, S){
  # x deve ser vetor coluna k-dimensional
  # m deve ser vetor coluna k-dimensional
  # S é matrix de covariancia k-por-k
  k = nrow(x)
  p1 = (sqrt(1/2/pi))^k 
  p2 = 1/sqrt(det(S))
  p3 = exp(-1/2 * t(x-m) %*% solve(S) %*% (x-m) )
  out = p1 * p2 * p3
  return(out)
}

m = matrix( c(1, -2) , 2, 1 )
S = matrix( c(1, 3/5, 3/5, 2) , 2 , 2 )

# espaço "prático" onde vai rolar. Sub-seleção do suporte da distrib
x = seq(  m[1,1] - 4*sqrt(S[1,1]) ,   m[1,1] + 4*sqrt(S[1,1]) , length = 100 )
y = seq(  m[2,1] - 4*sqrt(S[2,2]) ,   m[2,1] + 4*sqrt(S[2,2]) , length = 100 )

# gaussiana bi-dim de teste
f = matrix(0, length(x), length(y))
for(i in 1:length(x)){
  for(j in 1:length(y)){
    X = matrix( c(x[i], y[j]), 2, 1 )
    f[i,j] = dnormmulti(X , m, S) 
  }
}

# Exercício: aplicar uma máscara circular cujo raio R seja equivalente a 95% da área da gaussiana

# Função para determinar o raio
encontrarRaio = function(centro) {
  R = seq(0, 100, by = 0.01)
  # Criando a máscara a ser aplicada
  M = matrix(0, length(x), length(y))
  x0 = centro[1]; y0 = centro[2]
  
  for (r in 1:length(R)) {
    for (i in 1:length(x)) {
      for (j in 1:length(y)) {
        if ((x[i] - x0) ^ 2 + (y[j] - y0) ^ 2 < R[r]) 
          M[i, j] = 1
      }
    }
    
    if(integral >= 0.95) 
      return(R[r])
  }
} 

raio = encontraRaio(m) # raio de 9,69

# máscara aplicada a gaussiana
G = f*M  
integral = sum(G) * (x[2]-x[1]) * (y[2]-y[1])
image(x, y, G, main=round(integral,2))
contour(x, y, f, add = TRUE)
