#laboratorio 5

#2a carregando dados
dados = read.table("C:\\Users\\Alessandra\\Documents\\Método de Bioinfo\\rat_KD.txt", header = TRUE)

#2b remover coluna
row.names(dados) = as.character(dados[,1])
dados = dados[,-1]

#3 usando ttest
names(dados)
controle = dados[,1:6]
keto = dados[,7:11]

todos = function(aux,amostra1,amostra2){
  aux1 = aux[names(amostra1)]
  aux2 = aux[names(amostra2)]
  aux1 = as.numeric(aux1)
  aux2 = as.numeric(aux2)
  t.saida = t.test(aux1,aux2, alternative = "two.sided",var.equal = TRUE)
  saida = as.numeric(t.saida$p.value)
  return(saida)
}
test = apply(dados,1,todos, amostra1 = controle, amostra2 = keto)

#4 plotando
hist(test, main = "Histograma de p-values", xlab = "test", ylab = "")

#5 cálculos
mean_controle = apply(controle, 1, mean, na.rm = TRUE)
mean_controle = log2(mean_controle)
mean_keto = apply(keto, 1, mean, na.rm = TRUE)
mean_keto = log2(mean_keto)
fold = mean_keto-mean_controle

#6 plotando
hist(fold)

#vulcão
trans = -1 * log10(test) #transformacao do p
plot(range(trans), range(fold),type = "n", xlab = "p-value transformado", ylab = "fold change")
points(trans, fold, col = "gray")
points(trans[(trans > 3 & fold > 0.7)], fold[(trans > 3 & fold > 0.7)],col = "red", pch = 16)
points(trans[(trans > 3 & fold < -0.7)], fold[(trans > 3 & fold < -0.7)],col = "green", pch = 16)
abline(v = 3)
abline(h = -0.5)
abline(h = 0.5)
