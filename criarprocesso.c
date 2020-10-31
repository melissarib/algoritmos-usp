#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main(int argc, char **argv){
	pid_t pid;

	/* cria outro processo */
	pid = fork();
	if (pid < 0) { 		/* ocorreu erro na execução do Fork */
		fprintf(stderr, "Falha no Fork ");
		exit(-1);
	}
	else if (pid == 0) { 		/* processo filho */
        printf("Processo filho: %d\n", pid);
		execlp("/bin/ls","ls", "-al", NULL);
		exit(1);
	}
	else { /* processo pai */
	    printf("Processo pai: %d\n", pid);
		wait(NULL); 	/* pai espera o término do filho */
		printf("Filho terminou \n");
		exit(0);
	}
}
