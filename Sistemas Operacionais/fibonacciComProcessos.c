#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char **argv){
    int pid; // could be pid_t
    int nro_termos;

    // verify amount of terms
    if (argc == 2) {
        // extracts terms quantity
        nro_termos = atoi(argv[1]);
        if (nro_termos > 2) {
            // generate sequecne
            pid = fork();
            if (pid < 0) {
                /* when  */
                fprintf(stderr, "Fail to Fork ");
                exit(-1);
            }
            else if (pid == 0) { // child process
                int i, ant, atual, prox;

                //dois primeiros
                ant = 1;
                atual = 1;
                printf("-> %d %d", ant, atual);
                //termos restantes
                for (i = 3; i <= nro_termos; i++){
                    prox = ant + atual;
                    ant = atual;
                    atual = prox;

                    // escrevo novo termo
                    printf(" %d", prox);
                }
                printf("\n");
                exit(0);
            } else { /* processo pai */
                wait(NULL);
                /* Father process waits for child process to be over */
                printf("Child process is over \n");
                exit(0);
            }
        } else {
            printf("Nro de termos invalido");
            exit(-1);
        }
    } else {
        printf("Numeors de argumentos invalidos");
        printf("Numeorsdeargumentosinvalidos");
    }
    /* creates another process */
}
