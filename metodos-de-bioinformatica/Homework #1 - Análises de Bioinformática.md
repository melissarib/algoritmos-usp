1.) Baixe e carregue o arquivo de dados do conjunto de treinamento Golub ALL / AML em R. Defina os
nomes das linhas para os valores da primeira coluna (nomes dos fragmentos do Affymetrix) e remova a
primeira coluna. Observe as dimensões e verifique se você tem 38 matrizes e 7.129 genes.

```r
golub <- read.table ("golubTrain.txt", header = T)
dimn`ames (golub)[[1]] <- as.character (golub[,1])
golub <- golub[,-1]
dim (golub)
```

2.) Baixe e carregue o arquivo de anotação de 3 classes para este conjunto de dados em R. (1pt.)

```r
anotacao_golub <- read.table ("golubTrainClass2.txt", header = T)
head (anotacao_golub)
```

3.) Transmita os dados para um dataframe. (1pt.)
```r
golub <- as.data.frame (golub)
head (golub)
```
4.) Transforme os nomes do quadro de dados nos rótulos do arquivo de anotação (lembre-se de que
dependendo de como você lê no arquivo de anotação, pode ser um quadro de dados com 1 coluna.
(1pt.)
```r
names (golub) <- anotacao_golub[,1]
head (golub)5.) Subconjunto dos dados pelos primeiros 100 genes. (1pt.)
golub <- golub[1:100,]
dim (golub)
```
6.) Ao utilizar apenas os primeiros 100 genes, existe uma amostra discrepante aberrante. Identifique
esta amostra atípica usando os seguintes gráficos visuais: Correlation plot (heat map), hierarchical
clustering dendrogram e CV vs. mean plot.

7.) Gráfico de correlação (mapa de calor) (2 pontos.)
```r
correlacao_golub <- cor (golub)
image (correlacao_golub)
transposta_golub <- t (golub)
distancias_golub <- dist (transposta_golub)
plot ( hclust (distancias_golub))
mean_golub <- apply (golub,2,mean)
cv_golub <- ( apply (golub,2,sd))/mean_golub
plot (mean_golub,cv_golub)
```
8.) Agora baixe e carregue o conjunto de dados de levedura Spellman. Lembre-se de definir os nomes
das linhas para a primeira coluna, como você fez antes com o conjunto de dados de leucemia. (1pt.)
```r
spellman <- read.table ("spellman.txt",header=T)
dimnames (spellman)[[1]] <- as.character (spellman[,1])
spellman <- spellman[,-1]
head (spellman)

```
9.) Lance os dados para um quadro de dados e subconjunto para trabalhar apenas com as amostras de
experimento cdc28. (1pt.)
```r
spellman <- as.data.frame (spellman)
spellman <- spellman[,47:63]
head (spellman)
```
10.) Use a função e chame a função abaixo para preencher todos os valores “NA” com as médias de
linha computadas. (2 pontos)
```r
miss.fill <- function(x) {
if( sum ( is.na ( as.numeric (x))) ==17 ) {
x[ is.na (x)] <-0
}
if( sum ( is.na ( as.numeric (x))) > 0 & sum ( is.na ( as.numeric (x))) <17 )
{
x[ is.na (x)] <- mean ( as.numeric (x[! is.na (x)]))
}return (x)
}
dat.fill <- as.data.frame ( t ( apply (spellman,1,miss.fill)))
dat.fill <- round (dat.fill,2)
spellman<-dat.fill
```
11.) Calcule o método de agrupamento kmeans em todos os 6.178 genes, usando 10 centros de
agrupamento e 100 iterações. (5 pontos)
```r
cluster_spellman <- kmeans (spellman,centers=10, iter.max=100)
```
12.) Procure o gene#2 (YAL002W) e encontre o cluster ao qual ele pertence. Usando esses genes,
calcule a distância de cada gene ao gene# 2 (use a distância de manhattan na função dist ()). (5 pontos)
head (cluster_spellman$cluster)13.) Lance o objeto de distância para uma matriz e obtenha a coluna à qual o gene#2 corresponde.
Dica: se o gene no 2 estiver na coluna no 1 de sua matriz, obtenha as distâncias dos membros do cluster
correspondentes com: 
```r
gene.dist <- gene.dist [2: nrow (gene.dist), 1]
```
Se o gene # 2 estiver na coluna # 4 de sua matriz, obtenha as distâncias dos membros do cluster
correspondentes com:
```r
gene.dist <- gene.dist [c (1: 3,5: nrow (gene.dist)), 4] (5pts.)
distancia_YAL002W <- dist (spellman[cluster_grupo6,],method="manhattan")
distancia_YAL002W <- as.matrix (distancia_YAL002W)
head ( dimnames (distancia_YAL002W)[[2]])
distancia_YAL002W <- distancia_YAL002W[ c (1,3: nrow (distancia_YAL002W)),2]
```
14.) Obtenha os pesos de cada gene como uma porcentagem da soma dos valores de distância.
Assumindo que a primeira matriz (cdc28_0) está faltando um valor para o gene # 2, calcule a média
ponderada do vetor de peso do gene para este valor ausente. Imprima este valor médio ponderado. (5
pontos)

```r
peso <- as.numeric (distancia_YAL002W/ sum (distancia_YAL002W))
media_peso <- mean (spellman[cluster_spellman$cluster[-2],1]/peso)
print (media_peso)
```

Código completo:

```r
golub <- read.table ("golubTrain.txt", header = T)
dimnames (golub)[[1]] <- as.character (golub[,1])
golub <- golub[,-1]
dim (golub)
anotacao_golub <- read.table ("golubTrainClass2.txt", header = T)
head (anotacao_golub)
golub <- as.data.frame (golub)
head (golub)
names (golub) <- anotacao_golub[,1]
head (golub)
golub <- golub[1:100,]
dim (golub)
correlacao_golub <- cor (golub)
image (correlacao_golub)
transposta_golub <- t (golub)
distancias_golub <- dist (transposta_golub)
plot ( hclust (distancias_golub))
mean_golub <- apply (golub,2,mean)
cv_golub <- ( apply (golub,2,sd))/mean_golub
plot (mean_golub,cv_golub)
spellman <- read.table ("spellman.txt",header=T)
dimnames (spellman)[[1]] <- as.character (spellman[,1])
spellman <- spellman[,-1]
head (spellman)
spellman <- as.data.frame (spellman)
spellman <- spellman[,47:63]
head (spellman)
miss.fill <- function(x) {
if( sum ( is.na ( as.numeric (x))) ==17 ) {
x[ is.na (x)] <-0
}
if( sum ( is.na ( as.numeric (x))) > 0 & sum ( is.na ( as.numeric (x))) <17 ) {
x[ is.na (x)] <- mean ( as.numeric (x[! is.na (x)]))} return (x)
}
dat.fill <- as.data.frame ( t ( apply (spellman,1,miss.fill)))
dat.fill <- round (dat.fill,2)
spellman<-dat.fill
cluster_spellman <- kmeans (spellman,centers=10, iter.max=100)
head (cluster_spellman$cluster)
distancia_YAL002W <- dist (spellman[cluster_grupo6,],method="manhattan")
distancia_YAL002W <- as.matrix (distancia_YAL002W)
head ( dimnames (distancia_YAL002W)[[2]])
distancia_YAL002W <- distancia_YAL002W[ c (1,3: nrow (distancia_YAL002W)),2]
peso <- as.numeric (distancia_YAL002W/ sum (distancia_YAL002W))
media_peso <- mean (spellman[cluster_spellman$cluster[-2],1]/peso)
print (media_peso)
```
