if (!requireNamespace("BiocManager", quietly = TRUE))
  install.packages("BiocManager")
BiocManager::install("multtest")
library(multtest);
data(golub);
golub[1042,2]
golub[1024,]
View(golub.gnames)

#separando valores de expressao do gene M9.....
gol.fact <- factor(golub.cl,levels = 0:1, labels = c("ALL","AML"))
#visualizando
golub[1042, gol.fact == "ALL"]


#67
mall <- apply(golub[,gol.fact=="ALL"], 1, mean)
maml <- apply(golub[,gol.fact=="AML"], 1, mean)
o <- order(abs(mall-maml), decreasing=TRUE)
print(golub.gnames[o[1:5],2])
#calculando expressao genica media
mediaALL <- apply(golub[, gol.fact == "ALL"], 1, mean)
mediaAML <- apply(golub[, gol.fact == "AML"], 1, mean)
# ordenando valores da diferença de expressao genecia
difExpressao <- order(abs(mediaALL - mediaAML), decreasing = TRUE)
print(golub.gnames[difExpressao[1:5],2])


#68
#recolhendo oncogenes
oncogenes <- grep("oncogene", ignore.case = TRUE, golub.gnames[,2])
length(oncogenes)
oncogenesNames <- golub.gnames[oncogenes,2]
#media valores de expressao de oncogenes
mediaOnco <- apply(golub[oncogenes, gol.fact == "ALL"], 1, mean)
#ids das maiores medias de expressao
idExpreOnco <-order(mediaOnco, decreasing = TRUE)
oncoNames <- golub.gnames[oncogenes, 2]
#View(oncogenes)
#print 5 maiores valores de expressao


print(oncoNames[idExpreOnco[1:5]])
mediaOnco <- apply(golub[oncogenes, gol.fact == "AML"], 1, mean)
idExpreOnco <-order(mediaOnco, decreasing = TRUE)
print(oncoNames[idExpreOnco[1:5]])


#69

varAML <- golub[808, gol.fact == "AML"]
varALL <- golub[808, gol.fact == "ALL"]
var.test(varALL,varAML)



t.test(varALL, varAML, alternative = "two.sided")

#70
varAML <- golub[1788, gol.fact == "AML"]
varALL <- golub[1788, gol.fact == "ALL"]

vetores <- list(varALL, varAML)
boxplot(vetores)

t.test(varALL, varAML, alternative = "two.sided")



varAML <- golub[1391, gol.fact == "AML"]
varALL <- golub[1391, gol.fact == "ALL"]

vetores <- list(varALL, varAML)
boxplot(vetores)

t.test(varALL, varAML, alternative = "two.sided")



View(golub.gnames)

#PARTE 2:
genes <- golub[2208:2300]
nomeGenes <- golub.gnames[2208:2300,2]
p <- 0;

for(i in 1:92){
  ALL <- golub[i, gol.fact == "ALL"]  
  AML <- golub[i, gol.fact == "AML"]
  p[i] <- c(t.test(ALL, AML, alternative = "two.sided")$p.value)
}
ordGenes <- order(p[], decreasing = FALSE)
print(nomeGenes[ordGenes[1:20]])

t.test(ALL, AML, alternative = "two.sided")




#b
media <- apply(golub[2208:2300,], 1, mean)
ALL <- golub[2208:2300, gol.fact == "ALL"]  
AML <- golub[2208:2300, gol.fact == "AML"]
aux1 = 0
aux2 = 0
for(i in 1:92){
  for(j in 1:27){
    if(ALL[i,j]> media[i]){
      aux1 <- aux1 + 1
    }
  }
}

for(i in 1:92){
  for(j in 1:11){
    if(AML[i,j]>media[i]){
      aux2 <- aux2 + 1
    }
  }
}


prop.test(c(aux1,aux2),c(length(ALL), length(AML)), alternative = "greater")

