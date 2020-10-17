# subset dat by samples of interest
cl <- as.character(ann[,2])
dat <- dat[,cl]
# two classes of DLBCL determined from HCA in paper
gc <- cl[1:19]
act <- cl[20:39]
# calculate means of each class for each gene
gc.m <- apply(dat[,gc],1,mean,na.rm=T)
act.m <- apply(dat[,act],1,mean,na.rm=T)
# get fold changes (log scale)
fold <- gc.m-act.m
# function to calculate Student's two-sample t-test on all genes at once
# function returns the p-value for the test
# NAs are removed for each test
t.test.all.genes <- function(x,s1,s2) {
x1 <- x[s1]
x2 <- x[s2]
x1 <- as.numeric(x1)
x2 <- as.numeric(x2)
t.out <- t.test(x1,x2, alternative="two.sided",var.equal=T)
out <- as.numeric(t.out$p.value)
return(out)
}
# s1 and s2 are dimensions of the two samples
# run function on each gene in the data frame
t.test.run <- apply(dat,1,t.test.all.genes,s1=gc,s2=act)
# look at distribution of p-values
hist(t.test.run,col="blue")
# transpose p-values
p.trans <- -1 * log(t.test.run)
# volcano plot #1
plot(range(p.trans),range(fold),type="n",xlab="-1*log(p-value)",ylab="fold change",main="Volcano Plot")
points(p.trans,fold,col="black")
points(p.trans[(p.trans>3&fold>0.7)],fold[(p.trans>3&fold>0.7)],col="red",pch=16)
points(p.trans[(p.trans>3&fold< -0.7)],fold[(p.trans>3&fold< -0.7)],col="green",pch=16)
abline(v=3)
abline(h=-0.7)
abline(h=0.7)
# one factor ANVOVA model with yarn breaks (during weaving) data set
data(warpbreaks)
summary(fm1 <- aov(breaks ~ tension, data = warpbreaks))
# plot of Tukey"s honestly significant difference intervals
TukeyHSD(fm1, "tension", ordered = TRUE)
plot(TukeyHSD(fm1, "tension"))
# second volcano plot
library(Biobase); library(annotate); library(golubEsets);
data(geneData);
dat <- geneData
# floor data to 10
dat[dat<10] <- 10
fold <- apply(log(dat[,c(1:5)]),1,mean) - apply(log(dat[,c(14:18)]),1,mean)
t.test.run <- apply(dat,1,t.test.all.genes,s1=c(1:5),s2=c(14:18))
t.test.run[is.na(t.test.run)]<-1
# transpose p-values
p.trans <- -1 * log(t.test.run)
# volcano plot #2
plot(range(p.trans),range(fold),type='n',xlab='-1*log(p-value)',ylab='fold change',main='Volcano Plot')
points(p.trans,fold,col='black')
points(p.trans[(p.trans>3&fold>0.7)],fold[(p.trans>3&fold>0.7)],col='red',pch=16)
points(p.trans[(p.trans>3&fold< -0.7)],fold[(p.trans>3&fold< -0.7)],col='green',pch=16)
text(p.trans[p.trans>3],fold[p.trans>3],labels=dimnames(dat)[[1]][p.trans[p.trans>3]],cex=0.65)
abline(v=3)
abline(h=-0.7)
abline(h=0.7)
# PLS gene selection with Spellman et al. yeast data
library(yeastCC)
library(Biobase)
library(annotate)
library(gpls)
data(yeastCC)
dat <- exprs(yeastCC)
dat<-dat[,23:46] # only isolate the cdc15 experiment
dat.m <- apply(dat,1,mean)
dat <- dat[!is.na(dat.m),] # only retain genes with values
y<-c(rep(1,12),rep(0,12)) # specify gene pattern
# run pls to get gene weights for response
pls.out <- glpls1a(t(dat),y,br=FALSE, denom.eps=0.0001)$coefficients
pls.out <- pls.out[-1] # remove intercept
# look at distribution of gene weights
hist(pls.out,col='pink')
# look at most positive and most negative gene profiles
par(mfrow=c(2,1))
plot(c(1:24),dat[pls.out==max(pls.out),],type='b',col='red',xlab='samples',ylab='expression',main='Highe
st gene weight')
plot(c(1:24),dat[pls.out==min(pls.out),],type='b',col='red',xlab='samples',ylab='expression',main='Lowes
t gene weight')
