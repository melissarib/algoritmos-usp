       
       B = 1E4              # notação para 1*10^u = 1Eu
       
       O = c("A","B","C")   # espaço amostral: coisas que podem acontecer
       n = length(O)        # quantas coisas podem acontecer?      
     # p = c(1/2, 1/4, 1/4) 
       p = c(10, 35, 403)   # não normalizado
       
       Simu = function(O, p, B=100){
              X = NULL    
              for(i in 1:B){
                     o = sample(O, 1, prob=p) # amostre uma coisa que pode acontecer
                     X = c(X,o)
              }
              distrib = table(X)/sum(table(X))
              return(distrib)
       }
              
     #         table(X)
     #         table(X)/sum(table(X))
  
 # realizando teste
 
       p1 = c(10, 35, 403)
       Simu(O, p1, 1E5)
       
       p2 = c(10,35,405)/sum(c(10,35,403))
       p2 
       Simu(O, p2, 1E5) # normalizado pela soma de todos
       
       
       
      Simu2 = function(O, p1, B=100){
              X = sample(O, B, prob=p, replace=TRUE)
              distrib = table(X)/sum(table(X))
              return(distrib)
      }
              
