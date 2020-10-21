#laboratorio 4

# instalando e carregando biblioteca
source("https://bioconductor.org/biocLite.R")
for(i in c("Biobase","annotate","affydata","marray"))
  if(!i %in% installed.packages()[,1]) biocLite(i,suppressUpdates = T)
library(Biobase)
library(annotate)
library(marray)
data(swirl)

# grafico sem normalizacao
maPlot(swirl[,3],lines.func=NULL,legend.func=NULL,main='Sem normalização')

#normalizacao
mnorm =  maNorm(swirl[,3],norm="median",echo=TRUE)

#grafico com normalizacao
maPlot(mnorm,lines.func = NULL,legend.func = NULL,main = "Grafico MvA normalizado")

#loess
maPlot(mnorm,lines.func=NULL,legend.func=NULL,main='Normalização de intensidade global de Loess')

#carregando bibliotecas
library(affy)
library(affydata)
data(Dilution)

#perfect matches
obj= pm(Dilution[,1:2])
mva.pairs(obj)

#10 normalizando intensidades
obj= normalize.loess(obj,subset=1:nrow(x),span=1/3)

#11 graficos
mva.pairs(obj[,1:2])

#12
RMA.data= expresso(Dilution,bgcorrect.method="mas",normalize.method="constant",pmcorrect.method="mas",summary.method="avgdiff")
