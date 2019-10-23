#include <stdio.h> 
#include <math.h>

int conta(int vetor[], int n){ 
	if (n==0) return (vetor[n]); 
	else return (conta(vetor, n-1) + vetor[n]); 
} 

float media(int vetor[], int n){
	return (conta(vetor, n)/n); 
} 

int maior(int vetor[], int n, int max){ 
	if (vetor[n]>=max) 
		if (vetor[n]>=vetor[n-1]) return (vetor[n]); 
	else return (maior(vetor, n-1, max));
}

 main(){ 
	int i; 
	int n; 
	int max=0; 
	int vetor[10]; 	

	printf("tamanho vetor:"); 
	scanf("%d",&n); 

	for(i=0;i<n;i++){
		vetor[i]=i;
	}

	printf("O elemento maximo eh %d \n", maior(vetor, n, max));
	printf("A soma eh %d \n", conta(vetor, n));
	printf("A media eh %f \n", media(vetor, n));

	return 0;
}
