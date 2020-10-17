######## Laboratório 4 - Métodos para Bioinformática  ########

# Carregar o dataset swirl da biblioteca marray. 
# Para isso, instalar o yeastCC. 
source("https://bioconductor.org/biocLite.R")
for(i in c("Biobase","annotate","affydata","marray"))
  if(!i %in% installed.packages()[,1]) biocLite(i,suppressUpdates = T)
library(Biobase)
library(annotate)
library(marray)
data(swirl)

# Fazer um gráfico MvA da matriz 3 sem nenhuma linha estratificada (use maPlot ()).
maPlot(swirl[,3],
       lines.func=NULL,
       legend.func=NULL,
       main='Sem normalização')

# Fazer normalização da matriz a partir mediana global.
mnorm <- maNorm(swirl[,3], norm="median", echo=TRUE)

# Fazer um gráfico MvA das matrizes normalizadas sem as linhas estratificadas ou legenda.
maPlot(mnorm,
       lines.func=NULL,
       legend.func=NULL,
       main='Normalização mediana global')

# Pergunta: O que é diferente entre os dados normalizados e os dados não normalizados?
# Resposta: O gráfico normalizado mudou. O gráfico normalizado não é simétrico em relação a M = 0.

mnorm <- maNorm(swirl[,3], norm="loess", echo=TRUE)

## Normalization method: loess.
## Normalizing array 1.

maPlot(mnorm,
       lines.func=NULL,
       legend.func=NULL,
       main='Normalização de intensidade global de Loess')

# Pergunta: Qual das duas normalizações parece ser melhor para esta matriz?
# Resposta: A normalização da intensidade global de loess é melhor 
# porque a curvatura do gráfico de Lowess é quase eliminada e a linearidade é forte.

library(affy)
library(affydata)
data(Dilution)

# Instrução: Obtenha apenas as pontas de prova de correspondência perfeita (use pm ()) e plote-as em um gráfico MvA para os primeiros 2 arrays (use mva.pairs).
x <- pm(Dilution)
mva.pairs(x[,1:2])

# # Instrução: Normalize essas intensidades de PM com a função normalize.loess. Defina o intervalo para 1/3.
x <- normalize.loess(x,subset=1:nrow(x),span=1/3)

# # Instrução: Plote essas intensidades em um gráfico MvA.
mva.pairs(x[,1:2])

# # Instrução: dados de RMA do trato da diluição com os seguintes parâmetros:
eset <- expresso(Dilution,bgcorrect.method="mas", normalize.method="constant", pmcorrect.method="mas",summary.method="avgdiff")

# Quadro de dados dos valores RMA
library(SummarizedExperiment)
se <- makeSummarizedExperimentFromExpressionSet(eset)
head(assay(se))
