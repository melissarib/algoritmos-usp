# Distribuição de Poisson

lambda = 8

prob = function (k,l) {
       out = l^k * exp(-l)/factorial(k)
       return(out)
}

# prob(20,lambda)

P = NULL
for(k in 0:30) {
       P = c(P, prob(k, lambda))
}

x11(); barplot(P);

P = NULL
for(k in 0:30) {
       P = c(P, dpois(k, lambda))
}

x11(); barplot(P);



# x~Poi(lambda)

