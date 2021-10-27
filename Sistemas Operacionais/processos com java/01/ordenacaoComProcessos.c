#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>


/* escopo de funcoes */
void quickSort(int valor[], int esquerda, int direita);


int main(int argc, char **argv){
  /* declaracao das variaveis */
  pid_t pid_filho; /*variavel que armazena processos*/
	int numero1[argc-1];
	int numero2[argc-1];
  clock_t c1, c2, c3, c4; /* variáveis que contam ciclos do processador */
  long double tmpPai, tmpFilho;


	/* verificar quantidade de parametros */
	if (argc < 2){	/*erro*/

		printf("Nro incorreto de parmetros\n");
		printf("uso: Fibo <limite_sequencia>\n");
		exit(-1);
	}
	else {/* recebeu parametros corretamente */
    int i;
    for (i = 0; i < argc-1; i++){
		  numero1[i] = atoi(argv[i+1]);
		  numero2[i] = atoi(argv[i+1]);
    }
	}

	/* criao o processo filho */
	pid_filho = fork();	/*invoca filho */

	if (pid_filho < 0){/* erro na criacao processo filho */
		printf("Erro fork: abortado\n");
		exit(-1);
	}
	else{

    if (pid_filho == 0){/*processo filho*/

      /* quick sort */
      c1 = clock();
      quickSort(numero1, 0, argc-2);
      c2 = clock();

      tmpFilho = (float)(c2 - c1)*1000/CLOCKS_PER_SEC; // tempo de execução em milissegundos
      printf("\nTempo gasto na ordenacao quick sort: %Lf\n", tmpFilho);
      exit(0);
    }
    else {/*processo pai*/
      wait(NULL);

      /* ordenacao simples */
      c3 = clock();
      int i,j;
      for (i = argc-2; i > 0; i--){
        for ( j = 0; j < i; j++){
          if( numero2[j] > numero2[j+1] ){
            int aux = numero2[j];
            numero2[j] = numero2[j+1];
            numero2[j+1] = aux;
          }
        }
      }
      c4 = clock();

      tmpPai = (float)(c4 - c3)*1000/CLOCKS_PER_SEC; // tempo de execução em milissegundos
      printf("\nTempo gasto na ordenacao simples: %Lf\n\n", tmpPai);

      exit(0);
    }
  }

}



void quickSort(int valor[], int esquerda, int direita){
  int i, j, x, y;

  i = esquerda;
  j = direita;
  x = valor[(esquerda + direita) / 2];
  while(i <= j){
    while(valor[i] < x && i < direita){
      i++;
    }
    while(valor[j] > x && j > esquerda){
      j--;
    }
    if(i <= j){
      y = valor[i];
      valor[i] = valor[j];
      valor[j] = y;
      i++;
      j--;
    }
  }

  if(j > esquerda){
    quickSort(valor, esquerda, j);
  }
  if(i < direita){
    quickSort(valor, i, direita);
  }
}
