#distribuição de probabilidade qualquer (discreta) 
       
       esp_amostral1 = 1:5
       prob1 = c(1,1,7,5,9)
       
       esp_amostral2 = 1:10
       prob2 = c(1,8,7,6,7,8,6,4,2,6)
       
       esp_amostral3 = 1:12 
       prob3 = c(2,5,4,9,3,2,7,1,5,8,6,7)    
       
       
       layout(matrix(c(1,2,3),1,3))
       barplot(prob1/sum(prob1))
       barplot(prob2/sum(prob2))
       barplot(prob3/sum(prob3))
 
       S = function(esp_amostral1, esp_amostral2, esp_amostral3,
                    prob1, prob2, prob3, B=1000){
                    
           X1 = sample(esp_amostral1, B, prob=prob1, replace=TRUE)
           X2 = sample(esp_amostral2, B, prob=prob2, replace=TRUE)
           X3 = sample(esp_amostral3, B, prob=prob3, replace=TRUE)
           
           # Y = X1 * X2 * X3
           # Y = X1 + X2 + X3
           Y = ((X1^2)+(X2^2)+(X3^3)) 
           distribuicao = table(Y)/sum(table(Y))
           return(distribuicao) 
       }
       
       X = S(esp_amostral1, esp_amostral2, esp_amostral3, prob1, prob2, prob3, 1E5)
       barplot(X)      
       
