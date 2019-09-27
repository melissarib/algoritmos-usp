conta1 = 0
conta2 = 0
a = 50000

for (a in 1:a) {
  c1 = sample(c(0, 0, 0, 1, 1), 1, replace = T)
  c2 = sample(c(0, 0, 1), 1, replace = T)
  soma = c1 + c2
  
  if(soma == 2 | soma == 0){
    conta1 = conta1 + 1
  }
  
  if(soma >= 1){
    conta2 = conta2 + 1
  }
}  

prob1 = conta1 / a
prob2 = conta2 / a

result = prob1 + prob2