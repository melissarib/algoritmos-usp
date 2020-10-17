# import eisen data
dat <- read.table("eisen.txt",header=T) 
dimnames(dat)[[1]] <- as.character(dat[,1])

dat <- dat[,-1]
dat <- as.data.frame(dat)


# import annotation file
ann <- read.table("eisenClasses.txt",header=T)


# subset dat by samples of interest
cl <- as.character(ann[,2])
dat <- dat[,cl]

# two classes of DLBCL

gc <- cl[1:19]
act <- cl[20:39]

# split up classes and look at both samples for gene #8000 
x <- as.numeric(dat[8000,gc])
y <- as.numeric(dat[8000,act])


# remove “NAs”
x <- x[!is.na(x)];	y <- y[!is.na(y)]

# plot both samples

xy.list <- list(x,y)
boxplot(xy.list,col='purple',main='Gene #8000')


# calculate two-sample Welch’s t-test (unequal variances) between normal and tumor for gene #8000

xy.ttest <- t.test(x, y, alternative ="two.sided",paired = FALSE, var.equal = FALSE,conf.level = 0.95)

# determine sd of each group and choose max

x.sd <- sd(x)
y.sd <- sd(y)

# calculate number of replicates to detect 3 fold change (1.1 on log scale) at 80% power

power.t.test(delta=log(3),sd=y.sd,power=.8)
