#laboratorio 3

#2 carregando o Eisen DLBCL e removendo coluna
eisen = read.table("C:\\Users\\Alessandra\\Documents\\Método de Bioinfo\\eisen.txt", header = T, na.strings = "NA", blank.lines.skip = F)
row.names(eisen) = as.character(eisen[,1])
eisen = eisen[,-1]


#3 carregando a Eisenclass
eclasses = read.table("C:\\Users\\Alessandra\\Documents\\Método de Bioinfo\\eisenClasses.txt", header = T)

#4 separar as classes
amostra = as.character(eclasses[,2])
juntos = eisen[,amostra]
classe1 = juntos[,1:19]
classe2 = juntos[,20:39]
View(classe1)

#5 escolher um gene, remover NAs, fazer boxplot e hist
aux1 = as.numeric(classe1[1000,])
aux2 = as.numeric(classe2[1000,])
aux1 = na.omit(aux1)
aux2 = na.omit(aux2)
boxplot(aux1,aux2, col = c('red','blue'), main = "Gene 1000")
par(mfrow=c(2,1))
hist(aux1, col = 'red', ylab = "Frequencia", xlab = "dados", main = "Classe 1")
hist(aux2, col = 'blue', ylab = "Frequencia", xlab = "dados", main = "Classe 2")

#6 calcular sd
sd(aux1)
sd(aux2)
power.t.test(,sd = aux1.sd , sig.level = 0.01, power = 0.8)
