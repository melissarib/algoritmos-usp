       # Questão 1: Vestibular ITA 2008:
       # Considere uma população de igual número de homens e mulheres, em que sejam
       # daltônicos 5% dos homens e 0,25% das mulheres. Indique a probabilidade de 
       # que seja mulher uma pessoa daltônica selecionada ao acaso nessa população.

       NF = 100000000
       NM = NF # enunciado falou que era o mesmo número

       dM = 0.05 	# 5% de homens daltonicos
       dF = 0.0025 	# 0.25% de mulheres daltonicas

       Fem = data.frame(ID = 1:NF, status="Normal") # tabela começa com todos normais
       Fem[,2] = as.vector(Fem[,2])

       Mas = data.frame(ID = 1:NM, status="Normal")
       Mas[,2] = as.vector(Mas[,2])

       # floor é usada para retornar o maior valor inteiro 
       # que é menor ou igual a um número individual.

       NdaltM = floor(dM * NM) # número de homens afetados
       NdaltF = floor(dF * NF) # número de mulheres afetadas

       i = sample(1:nrow(Mas), NdaltM)
       Mas[i, "status"] = "dalt" # quem foi sorteado para ser daltonico

       i = sample(1:nrow(Fem), NdaltF)
       Fem[i, "status"] = "dalt" # quem foi sorteada para ser daltonica

       Pop= rbind(data.frame(Mas, sexo="M"), data.frame(Fem, sexo="F")) # juntando as populacoes

 # opção 1      
       conta = 0
       S = 100000 # numero de simulações
       for(s in 1:S){              
         i = sample(1:nrow(Pop), 1)
         if((Pop[i, "sexo"] == "F") && (Pop[i, "status"] == "dalt")){
            conta = conta + 1 # soma 1 caso seja menina e daltonica
         }
       }       
       prob1 = conta/S
           
 # opção 2
 # Qual a probabilidade de encontrar uma mulher daltonica na população daltonica 
       
       conta = 0
       S = 100000 # numero de simulações
       i = Pop[i,"status"] == "dalt"
       soDalt = Pop[i,]
       for(s in 1:S){              
         i = sample(1:nrow(soDalt), 1)
         if((soDalt[i, "sexo"] == "F") && (soDalt[i, "status"] == "dalt")){
            conta = conta + 1
         }
       }       
       prob2 = conta/S
       
       
      # Questão 2013: Vestibular ITA 2016
      # Numa certa brincadeira, um menino dispõe de uma caixa contendo quatro bolas,
      # cada qual marcada com apenas uma destas letras: N, S, L e O. Ao retirar 
      # aleatoriamente uma bola, ele vê a letra correspondente e devolve a bola à caixa. 
      # Se essa letra for N, ele dá um passo na direção Norte; 
      # se S, em direção Sul, se L, na direção Leste e se O, na direção Oeste.
      # Qual a probabilidade de ele voltar para a posição inicial no sexto passo? 
      
      S = 100000
      conta = 0
      for(s in 1:S){
             X = Y = 0 # no começo estamos na origem
             for(J in 1:6){
                 sentido = sample(c("x", "y"), 1)
                 if(sentido == "x"){
                   x = sample(c(-1,1),1)
                   X = X + x
                 }
                      
                 if(sentido == "y"){
                   y = sample(c(-1,1),1)
                   y = Y+y
                 }
              }
          
          if(X == 0 & Y == 0){conta = conta + 1}
       }
 
 prob = conta/S
 
 prob
 abs(prob - 0.09765)
 
       
      # Questão 12: Vestibular ITA 2013
      # Considere os seguintes resultados relativamente ao lançamento de uma moeda:
      # I. Ocorrência de duas caras em dois lançamentos.
      # II. Ocorrência de três caras e uma coroa em quatro lançamentos.
      # III. Ocorrência de cinco caras e três coroas em oito lançamentos.
      
      S = 10000
      
      # item I
      # duas caras em dois lançamentos. Possui reposição
      # moeda = sample(c("Ca", "Co"), 2, replace=TRUE)
      conta = 0
      for(s in 1:S){
        moeda = sample(0:1, 2, replace=TRUE)
        if(sum(m)==2) {conta = conta + 1}
      }
      probI = conta/S
      
          
      # item II
      # três caras e uma coroa em quatro lançamentos.
      # Possui reposição
      conta = 0
      for(s in 1:S){
        moeda = sample(0:1, 4, replace=TRUE)
        if(sum(m)== 3) {conta = conta + 1}
      }
      probII = conta/S
      
      # item III
      # três caras e uma coroa em quatro lançamentos.
      # Possui reposição
      conta = 0
      for(s in 1:S){
        moeda = sample(0:1, 8, replace=TRUE)
        if(sum(m)== 5) {conta = conta + 1}
      }
      probIII = conta/S
      
      
       
       
       
       
       
       
