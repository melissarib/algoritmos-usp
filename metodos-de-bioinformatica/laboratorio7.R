#Lab7 Cluster Analysis
#1.) Load the fibroEset library and data set (library(fibroEset)). Obtain the classifications for the samples.

source("http://bioconductor.org/biocLite.R")

## Bioconductor version 2.14 (BiocInstaller 1.14.2), ?biocLite for
## help
biocLite("fibroEset")
## Bioconductor version 2.14 (BiocInstaller 1.14.2), ?biocLite for
## help
## Warning: 'BiocInstaller' update failed, using version 1.14.2
## BioC_mirror: http://bioconductor.org
## Using Bioconductor version 2.14 (BiocInstaller 1.14.2), R version
## 3.1.1.
## Installing package(s) 'fibroEset'
##
## The downloaded source packages are in
## '/dados/tmp/RtmpmhiL9p/downloaded_packages'
## Warning: installed directory not writable, cannot update packages 'ASEB',
## 'AnnotationDbi', 'BH', 'BMA', 'BRAIN', 'BatchJobs', 'BiRewire',
## 'BiocInstaller', 'CNORode', 'ChAMP', 'ChIPpeakAnno', 'DBI',
## 'DECIPHER', 'DEoptimR', 'GGally', 'GMD', 'GenomicFeatures',
## 'HTSeqGenie', 'Hmisc', 'JADE', 'KEGGprofile', 'MLInterfaces',
## 'MMDiff', 'MeSHDbi', 'MinimumDistance', 'R.devices',
## 'R.filesets', 'R.utils', 'R2HTML', 'RPMM', 'RSQLite', 'RUnit',
## 'Rcpp', 'RcppArmadillo', 'Rook', 'SeqGSEA', 'VennDiagram',
## 'WriteXLS', 'aplpack', 'beanplot', 'betareg', 'biovizBase',
## 'caTools', 'charm', 'checkmate', 'coin', 'corpcor',
## 'customProDB', 'data.table', 'deSolve', 'devtools', 'doBy',
## 'e1071', 'energy', 'ensemblVEP', 'fBasics', 'flexmix',
## 'flipflop', 'flowMap', 'fpc', 'gWidgets', 'gdsfmt', 'geepack',
## 'ggbio', 'gplots', 'gsubfn', 'haplo.stats', 'highr',
## 'htmltools', 'httpuv', 'httr', 'hwriter', 'idr', 'illuminaio',
## 'imageHTS', 'inSilicoMerging', 'intervals', 'jsonlite',
## 'keggorthology', 'knitr', 'limma', 'log4r', 'maigesPack',
## 'maps', 'matrixStats', 'mboost', 'mclust', 'mime', 'minet',
## 'minqa', 'mosaics', 'nleqslv', 'nor1mix', 'oligo', 'pamr',
## 'pander', 'party', 'pcaPP', 'plethy', 'pracma', 'prodlim',
## 'proxy', 'pvclust', 'qtl', 'rChoiceDialogs', 'randomForestSRC',
## 'rbamtools', 'rcdk', 'rcdklibs', 'rebmix', 'refGenome', 'rols',
## 'roxygen2', 'segmented', 'sendmailR', 'shiny', 'spam',
## 'spliceR', 'sqldf', 'testthat', 'timeDate', 'timeSeries',
## 'xtable', 'BiocInstaller', 'MASS', 'nlme'
library(fibroEset)
## Loading required package: Biobase
## Loading required package: BiocGenerics
## Loading required package: parallel
##
## Attaching package: 'BiocGenerics'
##
## The following objects are masked from 'package:parallel':
##
## clusterApply, clusterApplyLB, clusterCall, clusterEvalQ,
## clusterExport, clusterMap, parApply, parCapply, parLapply,
## parLapplyLB, parRapply, parSapply, parSapplyLB
##
## The following object is masked from 'package:stats':
##
## xtabs
##
## The following objects are masked from 'package:base':
##
## Filter, Find, Map, Position, Reduce, anyDuplicated, append,
## as.data.frame, as.vector, cbind, colnames, do.call,
## duplicated, eval, evalq, get, intersect, is.unsorted, lapply,
## mapply, match, mget, order, paste, pmax, pmax.int, pmin,
## pmin.int, rank, rbind, rep.int, rownames, sapply, setdiff,
## sort, table, tapply, union, unique, unlist
##
## Welcome to Bioconductor
##
## Vignettes contain introductory material; view with
## 'browseVignettes()'. To cite Bioconductor, see
## 'citation("Biobase")', and for packages 'citation("pkgname")'.
data(fibroEset)
xx <- exprs(fibroEset)

#2.) Select a random set of 50 genes from the data frame, and subset the dataframe.

colnames(xx) <- as.character(fibroEset$species)
x <- xx[sample(1:nrow(xx), 50),]

#3.) Run and plot hierarchical clustering of the samples using manhattan distance metric and median linkage method. Make sure that the sample classification labels are along the x-axis. Title the plot.
h <- hclust(dist(x,method='manhattan'), method= 'median')
plot(h)

#4.) Now both run hierarchical clustering and plot the results in two dimensions (on samples and genes). Plot a heatmap with the genes on the -axis and samples on the x-axis. Once again, make sure that the sample and enes labels are present. Title the plot.

heatmap(x, distfun = function(x)
dist(x,method='manhattan'), hclustfun = function(x)
hclust(dist(x,method='manhattan'), method= 'median' ) )

#5.) Calculate PCA on the samples and retain the first two components vectors (eigenfunctions). Calculate k-means clustering on these first two components with k=3.

x.pca <- prcomp(t(x))
x.loads <- x.pca$x[,1:2]
plot(x.loads[,1],x.loads[,2],main="teste")
# kmeans clustering with random data
cl <- kmeans(x, centers=3, iter.max=20)
plot(x, col = cl$cluster,cex=1)
points(cl$centers, col = 1:3, pch = "*",cex=2.5)

#6.) Plot a two-dimensional scatter plot of the sample classification labels, mbedded with the first two eigenfunctions (from PCA). Color the labels with
#the color that corresponds to the predicted cluster membership. Make sure to abel the axes and title the plot. Color based on kmeans cluster. Put the
#different species and identify them and then color them based on kmeans luster to see which species didn’t cluster correctly.

sp = fibroEset$species
plot(x, col = cl$cluster,cex=1)
text(x, labels=sp, cex=.6)
