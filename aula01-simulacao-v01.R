# simulação de um experimento com 3 dados diferentes
# calcular a probabilidade estimada.
# nesse vetor que vai de 1 até 6, não pegar o mesmo valor de novo

B = 1E7 #quantidade de vezes que rodaremos o experimento
resposta = 0
casosdeinteresse = 0

for(i in 1:B) {
  dado6 =   sample(1:6, 1, replace=FALSE)
  dado10 =  sample(1:10, 1, replace=FALSE)
  dado20 =  sample(1:20, 1, replace=FALSE)
  resposta = dado6 + dado10 + dado20
  if(resposta == 33){ casosdeinteresse = casosdeinteresse + 1 }
}
	
probestimada = casosdeinteresse / B 
probestimada
