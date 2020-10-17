#1) Download and load the Golub ALL/AML training set data file into R. Set the row names to the first column values (Affymetrix fragment names) and remove the first column. Look at the dimensions and verify that you have 38 arrays and 7,129 genes.

golub <- read.table("golubTrain.txt", header = T)
dimnames(golub)[[1]] <- as.character(golub[,1])
golub <- golub[,-1]
dim(golub)
## [1] 7129 38

#2) Download and load the 3 class annotation file for this data set into R. 

anotacao_golub <- read.table("golubTrainClass2.txt", header = T)
head(anotacao_golub)
## B.cell
## 1 T-cell
## 2 T-cell
## 3 B-cell
## 4 B-cell
## 5 T-cell
## 6 B-cell

#3) Cast the data to a data frame.

golub <- as.data.frame(golub)
head(golub)
## X1 X2 X3 X4 X5 X6 X7 X8 X9 X10 X11
## AFFX-BioB-5_at -214 -139 -76 -135 -106 -138 -72 -413 5 -88 -165
## AFFX-BioB-M_at -153 -73 -49 -114 -125 -85 -144 -260 -127 -105 -155
## AFFX-BioB-3_at -58 -1 -307 265 -76 215 238 7 106 42 -71
## AFFX-BioC-5_at 88 283 309 12 168 71 55 -2 268 219 82
## AFFX-BioC-3_at -295 -264 -376 -419 -230 -272 -399 -541 -210 -178 -163
## AFFX-BioDn-5_at -558 -400 -650 -585 -284 -558 -551 -790 -535 -246 -430
## X12 X13 X14 X15 X16 X17 X18 X19 X20 X21 X22
## AFFX-BioB-5_at -67 -92 -113 -107 -117 -476 -81 -44 17 -144 -247
## AFFX-BioB-M_at -93 -119 -147 -72 -219 -213 -150 -51 -229 -199 -90
## AFFX-BioB-3_at 84 -31 -118 -126 -50 -18 -119 100 79 -157 -168
## AFFX-BioC-5_at 25 173 243 149 257 301 78 207 218 132 -24
## AFFX-BioC-3_at -179 -233 -127 -205 -218 -403 -152 -146 -262 -151 -308
## AFFX-BioDn-5_at -323 -227 -398 -284 -402 -394 -340 -221 -404 -347 -571
## X23 X24 X25 X26 X27 X34 X35 X36 X37 X38 X28
## AFFX-BioB-5_at -74 -120 -81 -112 -273 -20 7 -213 -25 -72 -4
## AFFX-BioB-M_at -321 -263 -150 -233 -327 -207 -100 -252 -20 -139 -116
## AFFX-BioB-3_at -11 -114 -85 -78 -76 -50 -57 136 124 -1 -125
## AFFX-BioC-5_at -36 255 316 54 81 101 132 318 325 392 241
## AFFX-BioC-3_at -317 -342 -418 -244 -439 -369 -377 -209 -396 -324 -191
## AFFX-BioDn-5_at -499 -396 -461 -275 -616 -529 -478 -557 -464 -510 -411
## X29 X30 X31 X32 X33
## AFFX-BioB-5_at 15 -318 -32 -124 -135
## AFFX-BioB-M_at -114 -192 -49 -79 -186
## AFFX-BioB-3_at 2 -95 49 -37 -70
## AFFX-BioC-5_at 193 312 230 330 337
## AFFX-BioC-3_at -51 -139 -367 -188 -407
## AFFX-BioDn-5_at -155 -344 -508 -423 -566

#4) Make the names of the data frame the annotation file labels (remember that depending on how you read in the annotation file, it may be a dataframe with 1 column.

names(golub) <- anotacao_golub[,1]
head(golub)
## T-cell T-cell B-cell B-cell T-cell B-cell B-cell T-cell
## AFFX-BioB-5_at -214 -139 -76 -135 -106 -138 -72 -413
## AFFX-BioB-M_at -153 -73 -49 -114 -125 -85 -144 -260
## AFFX-BioB-3_at -58 -1 -307 265 -76 215 238 7
## AFFX-BioC-5_at 88 283 309 12 168 71 55 -2
## AFFX-BioC-3_at -295 -264 -376 -419 -230 -272 -399 -541
## AFFX-BioDn-5_at -558 -400 -650 -585 -284 -558 -551 -790
## T-cell T-cell B-cell B-cell T-cell B-cell B-cell B-cell
## AFFX-BioB-5_at 5 -88 -165 -67 -92 -113 -107 -117
## AFFX-BioB-M_at -127 -105 -155 -93 -119 -147 -72 -219
## AFFX-BioB-3_at 106 42 -71 84 -31 -118 -126 -50
## AFFX-BioC-5_at 268 219 82 25 173 243 149 257
## AFFX-BioC-3_at -210 -178 -163 -179 -233 -127 -205 -218
## AFFX-BioDn-5_at -535 -246 -430 -323 -227 -398 -284 -402
## B-cell B-cell B-cell B-cell B-cell T-cell B-cell B-cell
## AFFX-BioB-5_at -476 -81 -44 17 -144 -247 -74 -120
## AFFX-BioB-M_at -213 -150 -51 -229 -199 -90 -321 -263
## AFFX-BioB-3_at -18 -119 100 79 -157 -168 -11 -114
## AFFX-BioC-5_at 301 78 207 218 132 -24 -36 255
## AFFX-BioC-3_at -403 -152 -146 -262 -151 -308 -317 -342
## AFFX-BioDn-5_at -394 -340 -221 -404 -347 -571 -499 -396
## B-cell B-cell AML AML AML AML AML AML AML AML AML
## AFFX-BioB-5_at -81 -112 -273 -20 7 -213 -25 -72 -4 15 -318
## AFFX-BioB-M_at -150 -233 -327 -207 -100 -252 -20 -139 -116 -114 -192
## AFFX-BioB-3_at -85 -78 -76 -50 -57 136 124 -1 -125 2 -95
## AFFX-BioC-5_at 316 54 81 101 132 318 325 392 241 193 312
## AFFX-BioC-3_at -418 -244 -439 -369 -377 -209 -396 -324 -191 -51 -139
## AFFX-BioDn-5_at -461 -275 -616 -529 -478 -557 -464 -510 -411 -155 -344
## AML AML NA
## AFFX-BioB-5_at -32 -124 -135
## AFFX-BioB-M_at -49 -79 -186
## AFFX-BioB-3_at 49 -37 -70
## AFFX-BioC-5_at 230 330 337
## AFFX-BioC-3_at -367 -188 -407
## AFFX-BioDn-5_at -508 -423 -566
dim(golub)
## [1] 7129 38

#5) Subset the data by the first 100 genes.

golub <- golub[1:100,]
dim(golub)
## [1] 100 38

#6) When utilizing only the first 100 genes, there exists one aberrant outlier sample. Identify this outlier sample using the following visual plots.

#7) Correlation plot (heat map)

correlacao_golub <- cor(golub)
image(correlacao_golub)
#Hierarchical clustering dendrogram
transposta_golub <- t(golub)
distancias_golub <- dist(transposta_golub)
plot(hclust(distancias_golub))
#CV vs. mean plot
mean_golub <- apply(golub,2,mean)
cv_golub <- (apply(golub,2,sd))/mean_golub
plot(mean_golub,cv_golub)

#8) Now download and load the Spellman yeast data set. Remember to set the row names to the first column as you did before with the leukemia dataset.

spellman <- read.table("spellman.txt",header=T)
dimnames(spellman)[[1]] <- as.character(spellman[,1])
spellman <- spellman[,-1]
head(spellman)
## cln3.1 cln3.2 clb2.2 clb2.1 alpha0 alpha7 alpha14 alpha21 alpha28
## YAL001C 0.15 NA -0.22 0.07 -0.15 -0.15 -0.21 0.17 -0.42
## YAL002W -0.07 -0.76 -0.12 -0.25 -0.11 0.10 0.01 0.06 0.04
## YAL003W -1.22 -0.27 -0.10 0.23 -0.14 -0.71 0.10 -0.32 -0.40
## YAL004W -0.09 1.20 0.16 -0.14 -0.02 -0.48 -0.11 0.12 -0.03
## YAL005C -0.60 1.01 0.24 0.65 -0.05 -0.53 -0.47 -0.06 0.11
## YAL007C 0.65 1.39 -0.29 -0.54 -0.60 -0.45 -0.13 0.35 -0.01
## alpha35 alpha42 alpha49 alpha56 alpha63 alpha70 alpha77 alpha84
## YAL001C -0.44 -0.15 0.24 -0.10 NA 0.18 0.42 -0.25
## YAL002W -0.26 0.04 0.19 -0.22 -0.20 0.12 0.21 -0.26
## YAL003W -0.58 0.11 0.21 0.09 0.57 -0.14 0.29 0.01
## YAL004W 0.19 0.13 0.76 0.07 0.04 -0.06 0.30 -0.47
## YAL005C -0.07 0.25 0.46 0.12 0.49 -0.42 0.28 -0.30
## YAL007C 0.49 0.18 0.43 -0.23 -0.30 -0.24 0.23 0.04
## alpha91 alpha98 alpha105 alpha112 alpha119 cdc15_10 cdc15_30
## YAL001C -0.01 -0.13 0.77 -0.21 0.43 -0.16 0.09
## YAL002W -0.30 0.22 0.58 -0.36 0.13 NA NA
## YAL003W 0.04 0.05 0.55 -0.08 0.33 -0.37 -0.22
## YAL004W -0.16 -0.11 0.23 -0.45 0.02 NA NA
## YAL005C -0.18 -0.30 0.68 -0.24 0.22 -0.43 -1.33
## YAL007C 0.09 0.18 0.16 -0.01 -0.21 NA NA
## cdc15_50 cdc15_70 cdc15_80 cdc15_90 cdc15_100 cdc15_110 cdc15_120
## YAL001C -0.23 0.03 -0.04 -0.12 -0.28 -0.44 -0.09
## YAL002W NA -0.58 0.23 -0.23 0.08 -0.62 0.55
## YAL003W -0.16 0.04 0.53 -0.25 0.08 -0.24 0.37
## YAL004W NA -1.50 -0.03 -1.20 -0.06 -1.78 0.14
## YAL005C -1.53 -1.53 -0.37 -1.65 -0.71 -1.53 -0.10
## YAL007C NA 0.14 0.58 0.25 0.03 -1.00 0.00
## cdc15_130 cdc15_140 cdc15_150 cdc15_160 cdc15_170 cdc15_180
## YAL001C 0.12 0.06 -0.04 0.31 0.59 0.34
## YAL002W -0.32 0.03 -0.56 0.47 -0.15 0.49
## YAL003W -0.22 0.16 -0.21 0.03 0.03 0.48
## YAL004W -1.13 -0.13 -1.27 -0.27 -0.94 0.14
## YAL005C -1.15 -0.33 -1.15 -0.19 -0.84 0.52
## YAL007C -0.41 0.10 0.14 0.40 0.20 0.24
## cdc15_190 cdc15_200 cdc15_210 cdc15_220 cdc15_230 cdc15_240
## YAL001C -0.28 -0.09 -0.44 0.31 0.03 0.57
## YAL002W NA 0.23 -0.49 0.33 0.18 0.65
## YAL003W NA 0.22 -0.06 0.08 0.56 0.48
## YAL004W NA 1.04 0.48 1.94 1.62 1.73
## YAL005C NA 1.18 0.88 1.80 2.24 2.34
## YAL007C NA 0.26 -0.39 0.43 -0.26 -0.33
## cdc15_250 cdc15_270 cdc15_290 cdc28_0 cdc28_10 cdc28_20 cdc28_30
## YAL001C 0.00 0.02 -0.26 -0.19 -0.77 -0.17 -0.19
## YAL002W -0.29 NA NA 0.83 -0.01 -0.77 -0.62
## YAL003W -0.47 -0.45 -0.41 -0.36 -0.22 0.22 -0.28
## YAL004W 1.22 NA NA 1.64 1.14 0.88 -0.07
## YAL005C 1.43 1.27 1.18 1.55 1.58 1.34 0.01
## YAL007C -0.37 NA NA -0.59 -0.16 0.66 -0.10
## cdc28_40 cdc28_50 cdc28_60 cdc28_70 cdc28_80 cdc28_90 cdc28_100
## YAL001C 0.13 -0.36 -0.55 -0.07 -0.01 0.03 0.27
## YAL002W 0.14 -0.58 -0.05 0.23 0.20 0.23 0.08
## YAL003W 0.41 -0.80 0.42 0.05 -0.47 1.06 -2.82
## YAL004W 0.03 -1.18 0.07 -0.34 -0.73 -0.18 -0.60
## YAL005C 0.53 -0.80 -0.16 -0.61 -0.90 -0.07 -0.96
## YAL007C 0.07 -0.33 0.41 -0.23 -0.51 0.58 0.07
## cdc28_110 cdc28_120 cdc28_130 cdc28_140 cdc28_150 cdc28_160 elu0
## YAL001C 0.49 0.85 0.66 -0.24 0.03 0.09 0.30
## YAL002W -0.03 0.39 -0.09 NA 0.19 -0.14 0.14
## YAL003W 0.38 -0.22 0.47 0.89 0.48 0.80 -1.54
## YAL004W -0.16 -0.12 -0.38 0.01 NA NA 0.36
## YAL005C -0.53 -0.66 NA -0.27 -0.41 0.35 -0.09
## YAL007C 0.32 0.01 0.17 NA -0.21 -0.14 -0.17
## elu30 elu60 elu90 elu120 elu150 elu180 elu210 elu240 elu270 elu300
## YAL001C -0.12 0.24 0.18 -0.24 0.11 -0.12 0.37 0.07 -0.09 -0.32
## YAL002W 0.17 0.03 0.35 0.03 -0.08 -0.13 -0.21 -0.06 -0.10 -0.19
## YAL003W -0.30 NA 0.39 0.18 0.28 0.15 0.40 0.24 0.22 -0.27
## YAL004W 0.10 0.11 0.14 -0.15 -0.73 -0.79 -0.20 0.34 0.07 -0.59
## YAL005C -0.02 NA 0.25 -0.45 -0.83 -0.26 -0.16 0.30 0.21 -0.43
## YAL007C -0.28 -0.16 0.20 0.36 0.17 0.39 0.10 0.00 -0.16 0.29
## elu330 elu360 elu390
## YAL001C 0.04 -0.48 0.04
## YAL002W 0.08 0.01 -0.02
## YAL003W -0.10 0.34 0.02
## YAL004W 0.35 0.66 0.32
## YAL005C 0.21 0.60 0.65
## YAL007C -0.06 -0.26 -0.41

#9) Cast the data to a data frame and subset to only work with the cdc28 experiment samples.

spellman <- as.data.frame(spellman)
spellman <- spellman[,47:63]
head(spellman)
## cdc28_0 cdc28_10 cdc28_20 cdc28_30 cdc28_40 cdc28_50 cdc28_60
## YAL001C -0.19 -0.77 -0.17 -0.19 0.13 -0.36 -0.55
## YAL002W 0.83 -0.01 -0.77 -0.62 0.14 -0.58 -0.05
## YAL003W -0.36 -0.22 0.22 -0.28 0.41 -0.80 0.42
## YAL004W 1.64 1.14 0.88 -0.07 0.03 -1.18 0.07
## YAL005C 1.55 1.58 1.34 0.01 0.53 -0.80 -0.16
## YAL007C -0.59 -0.16 0.66 -0.10 0.07 -0.33 0.41
## cdc28_70 cdc28_80 cdc28_90 cdc28_100 cdc28_110 cdc28_120 cdc28_130
## YAL001C -0.07 -0.01 0.03 0.27 0.49 0.85 0.66
## YAL002W 0.23 0.20 0.23 0.08 -0.03 0.39 -0.09
## YAL003W 0.05 -0.47 1.06 -2.82 0.38 -0.22 0.47
## YAL004W -0.34 -0.73 -0.18 -0.60 -0.16 -0.12 -0.38
## YAL005C -0.61 -0.90 -0.07 -0.96 -0.53 -0.66 NA
## YAL007C -0.23 -0.51 0.58 0.07 0.32 0.01 0.17
## cdc28_140 cdc28_150 cdc28_160
## YAL001C -0.24 0.03 0.09
## YAL002W NA 0.19 -0.14
## YAL003W 0.89 0.48 0.80
## YAL004W 0.01 NA NA
## YAL005C -0.27 -0.41 0.35
## YAL007C NA -0.21 -0.14

#10) Use both the function and call to the function below to fill all “NA” values with the computed row means.

miss.fill <- function(x) {
if(sum(is.na(as.numeric(x))) ==17 ) {
x[is.na(x)] <-0
} i
f(sum(is.na(as.numeric(x))) > 0 & sum(is.na(as.numeric(x))) <17 ) {
x[is.na(x)] <- mean(as.numeric(x[!is.na(x)]))
}
return(x)
} d
at.fill <- as.data.frame(t(apply(spellman,1,miss.fill)))
dat.fill <- round(dat.fill,2)
spellman<-dat.fill

#11) Calculate the kmeans clustering method on all 6,178 genes, using 10 cluster centers and 100 iterations.
cluster_spellman <- kmeans(spellman,centers=10, iter.max=100)

#12) Look for gene #2 (YAL002W) and find the cluster that it belongs to. Using these genes, calculate the distance from each gene to gene #2 (use manhattan distance in dist() function).

head(cluster_spellman$cluster)
## YAL001C YAL002W YAL003W YAL004W YAL005C YAL007C
## 5 5 1 3 3 10
grupo_YAL002W <- cluster_spellman$cluster[[2]]
grupo6 <- cluster_spellman$cluster==grupo_YAL002W
cluster_grupo6 <- dimnames(spellman)[[1]][grupo6]

#13) Cast the distance object to a matrix and get the column that gene #2 corresponds to. Hint: If gene #2 is in column #1 of your matrix, get the corresponding cluster member distances with: gene.dist <- gene.dist[2:nrow(gene.dist),1] If gene #2 is in column #4 of your matrix, get the corresponding cluster member distances with: gene.dist <-gene.dist[c(1:3,5:nrow(gene.dist)),4]

distancia_YAL002W <- dist(spellman[cluster_grupo6,],method="manhattan")
distancia_YAL002W <- as.matrix(distancia_YAL002W)
head(dimnames(distancia_YAL002W)[[2]])
## [1] "YAL001C" "YAL002W" "YAL009W" "YAL011W" "YAL013W" "YAL027W"
distancia_YAL002W <- distancia_YAL002W[c(1,3:nrow(distancia_YAL002W)),2]

#14) Get the weights of each gene as a percentage of the sum of distance values. Assuming that the first array (cdc28_0) is missing a value for gene #2, calculate the weighted mean from the gene weight vector for this missing value. Print out this weighted mean value.

peso <- as.numeric(distancia_YAL002W/sum(distancia_YAL002W))
media_peso <- mean(spellman[cluster_spellman$cluster[-2],1]/peso)
## Warning: longer object length is not a multiple of shorter object length
print(media_peso)
## [1] 518.6
