       
              
      O = 1:5  # espaço amostral: coisas que podem acontecer
      n = length(O)        # quantas coisas podem acontecer?      
          
     
      S = function(O, p1, B=100){
              X = sample(O, B, prob=p, replace=TRUE)
              distrib = table(X)/sum(table(X))
              return(distrib)
      }
      
      p = c(10,10,20,40,80)
      sum(p)
      S(O,p)
              
      result = S(O,p)
      barplot(result)
      
 
