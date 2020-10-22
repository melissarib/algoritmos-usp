#laboratorio 2

#2a lendo os dados
dados = read.table("C:\\Users\\Alessandra\\Documents\\Método de Bioinfo\\spellman.txt", header = TRUE)

#2b remover coluna
row.names(dados) = as.character(dados[,1])
dados = dados[,-1]

#3a checar a dimensão dos dados
dim(dados)
 
#3b isolar o cdc15
cdc15 = dados[,23:46]
dados.mean = rowMeans(dados, na.rm = TRUE)            
na.idx = is.na(dados["YAL002W",])
length(na.idx)
dados["YAL002W",na.idx] = dados.mean["YAL002W"]

#4 fazer o boxplot e histograma
boxplot(as.numeric(dados["YAL002",]), col = 'red', main = "Boxplot do gene #2/YAL002W")
hist(as.numeric(dados["YAL002",]), col = 'red', xlab = 'dados',ylab = 'frequencia', main = "Histograma do gene #2/YAL002w ")
        
#5 gerar o perfil
plot(x = 1:ncol(dados), y = as.numeric(dados["YAL002W",]), type = 'l', col = 'red', main = "Perfil do gene #2", xlab = "expressao", ylab = "amostra", lwd = '3')

