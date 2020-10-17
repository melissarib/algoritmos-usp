library(Biobase);	library(annotate);	library(golubEsets);	library(multtest);
data(geneData);	data(golub);
dat1 <- geneData
dat2 <- golub[1:100,]
ann.dat2 <- golub.cl	# class labels

t.test.all.genes <- function(x,s1,s2) {
	x1 <- x[s1]
	x2 <- x[s2]
	x1 <- as.numeric(x1)
	x2 <- as.numeric(x2)
	t.out <- t.test(x1,x2, alternative=“two.sided”,var.equal=T)
	out <- as.numeric(t.out$p.value)
	return(out)
}
# s1 and s2 are dimensions of the two samples
# run function on each gene in the data frame
rawp <- apply(dat2,1,t.test.all.genes,s1=ann.dat2==0,s2=ann.dat2==1)

# apply multiple test correction using some permutation and step-down/up methods
library(multtest} 

# another option for a t-test and non-parameteric tests, using minP adjustment method
# p-value results are sorted in ascending order (be aware)
resP<-mt.minP(dat2,ann.dat2,test=“t”,side=“abs”)$rawp
	
# apply multiple test correction using non-permuted methods
library(base)
p <- c(0.01,0.04,0.77,0.34)
p.cor <- p.adjust(p,method=“holm”)


# get first 100 genes of golub data with class labels
data(golub)
smallgd<-golub[1:100,] 
classlabel<-golub.cl

# calculate multiple adjusted p-values with various methods
procs<-c("Bonferroni","Holm","Hochberg","SidakSS","SidakSD","BH","BY")
res2<-mt.rawp2adjp(rawp,procs)

# nice function to calculate the number of rejected hypotheses using Westfall and Young maxT adjustment
res<-mt.maxT(smallgd,classlabel)
mt.reject(cbind(res$rawp,res$adjp),seq(0,1,0.1))$r

# see mt.plot() for plots from the lecture

# SAM
dat <- golub
sam.ann <- classlabel+1 #the class labels must be 1 and 2 (not 0 and 1) 
data=list(x=dat,y=sam.ann, geneid=as.character(1:nrow(x)),genenames=paste("g",as.character(1:nrow(x)),sep="") , logged2=F)
samr.obj<-samr(data,  resp.type="Two class unpaired", nperms=100)

# look at distributions of observed and expected test statistics
par(mfcol=c(1,2))
hist(samr.obj$tt,col='red',main='SAM-observed test statistics')
hist(samr.obj$evo,col='red',main='SAM-expected test statistics')

# plot the observed vs. expected genes using a delta of +/-2
delta=2
samr.plot(samr.obj,delta)
title(main='Observed vs. Expected test statistics')
