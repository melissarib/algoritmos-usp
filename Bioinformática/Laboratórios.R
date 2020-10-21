#laboratorio1

#2a lendo para o R
dado = read.table("C:\\Users\\Alessandra\\Documents\\método de bioinfo\\alon.txt",header = T)

#2b nomes das colunas
row.names(dado) = as.character(dado[,1])
head(rownames(dado))
dado = dado[,-1]

#3 verificar se coluna foi excluida
dim(dado)

#4 mostrar os nomes das amostras
colnames(dado)

#5 plotar grafico de um tumor vs uma amostra normal
plot(x=dado$tumor10, y=dado$norm10, xlab = "Tumor", ylab = "Normal", main = "Tumor sample vs. Normal sample - 2000 genes")

#6 plotar grafico normal vs normal com 20 genes
aux1 = dado[(1:20),]
plot(x = aux1$norm1, y = aux1$norm2, xlab = "Normal 1", ylab = "Normal 2", main = "Normal 1 vs. Normal 2 - 20 genes")

#7 conectar os pontos
plot(x = aux1$norm1, y = aux1$norm2, xlab = "Normal 1", ylab = "Normal 2", main = "Normal 1 vs. Normal 2 - 20 genes")
lines(x = aux1$norm1, y = aux1$norm2, col = 8)


#8 grafico genes 5 a 15
plot(1:ncol(dado),(dado[5,]/dado[15,]), ylab = 'genes',xlab = "amostra", main = "perfil dos genes 5 a 15")
text(1:ncol(dado),dado[5,]/dado[15,],label = names(dado), cex = 1)


