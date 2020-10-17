# Load Golub et al. data 
library(multtest) 
data(golub);
dat <- golub
dat <- as.data.frame(dat)
ann.dat2 <- golub.cl	# class labels ALL=0; AML=1 
ann <- factor(c(rep("ALL",27),rep("AML",11)))

# HCA heat map of top 100 genes  # adjust colors for heat map
dat <- dat[1:100,]	# only use top 100 genes for computational speed 
hm.rg <- c("#FF0000","#CC0000","#990000","#660000","#330000","#000000","#000000","#0A3300","#146600","#1F9900","#29CC00","#33FF00")
names(dat) <- ann 
heatmap(as.matrix(dat),col=hm.rg)

# kmeans clustering with random data  library(mva)
x <- rbind(matrix(rnorm(100, sd = 0.3),ncol=2),matrix(rnorm(100, mean = 1, sd = 0.9),ncol=2),matrix( rnorm(100, mean = 4, sd = 0.75),ncol=2),matrix(rnorm(100, mean = -2, sd = 0.5),  ncol = 2))
cl <- kmeans(x, centers=4, iter.max=20) 
plot(x, col = cl$cluster,cex=1)
points(cl$centers, col = 1:4, pch = "*",cex=2.5)

# som clustering on Golub et al. samples 
library(class)
dat <- t(dat)	# transponse data 
dimnames(dat)[[1]] <- ann
gr <- somgrid(xdim=3,ydim=1,topo = "rectangular") 
dat.som <- SOM(dat,gr)

# plot classifier
bins <- as.numeric(knn1(dat.som$code, dat,c(1:3))) 
plot(dat.som$grid, type = "n")
symbols(dat.som$grid$pts[, 1],dat.som$grid$pts[, 2],circles = rep(0.85, 3), inches = FALSE, add = TRUE)
text(dat.som$grid$pts[bins, ] + rnorm(76, 0, 0.1),as.character(ann),cex=0.6,col='red')
