/*Implemente um aplicativo que a leitura de duas matrizes A e B e posteriormente informe o resultado da soma das mesmas. A quantidade de linhas e colunas das matrizes deve ser passada pelo usuário como um argumento da linha de comando, e o valor de cada elemento deve ser lido do teclado.
 */

//SomaDeMatrizes.java

import java.util.Scanner;

public class SomaDeMatrizes{

	public static void main(String[] args){
		//declaração
		int i, j;	//contadoras
		int nLinhas, nColunas;		//tamanho das matrizes
		int a[][], b[][], soma[][];	//matrizes
		Scanner sc;

		//inicialização
		nLinhas = Integer.parseInt(args[0]);
		nColunas = Integer.parseInt(args[1]);
		a = new int[nLinhas][nColunas];
		b = new int[nLinhas][nColunas];
		soma = new int[nLinhas][nColunas];
		sc = new Scanner(System.in);
		
		//lógica

		//preencher matriz a
		for(i=0;i<nLinhas;i++){
			for(j=0;j<nColunas;j++){
				System.out.println("Insira o elemento a["+i+"]["+j+"]" );
				a[i][j] = sc.nextInt();
			}
		}
		//preencher matriz b
		for(i=0;i<nLinhas;i++){
			for(j=0;j<nColunas;j++){
				System.out.println("Insira o elemento b["+i+"]["+j+"]" );
				b[i][j] = sc.nextInt();
			}
		}
		//somar
		for(i=0;i<nLinhas;i++){
			for(j=0;j<nColunas;j++){
				soma[i][j] = a[i][j] + b[i][j];
			}
		}
		//escrever matriz soma
		System.out.println("");
		for(i=0;i<nLinhas;i++){
			for(j=0;j<nColunas;j++){
				System.out.print(soma[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}



