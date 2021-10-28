/*Escreva um aplicativo que que leia uma determinada quantidade de números, entre 10 e 100. A cada número lido, o aplicativo deve exibir uma mensagem caso o número entrado seja um número repetido. Ao final, o aplicativo deve exibir os números que foram lidos somente uma vez.
 */

import java.util.Scanner;

//EliminaRepet‏ido.java

public class EliminaRepet‏ido{

	public static void main (String[] args){
		int i,j;
		int numero[];
		Scanner sc;

		numero = new int[10];
		sc = new Scanner(System.in);
		System.out.println("digite um numero entre 10 e 100");
		do{		
		numero[0] = sc.nextInt();
		if(numero[0]<10 || numero[0] >100)
			System.out.println("Tem que ser entre 10 e 100");
		}while(numero[0]<10 || numero[0] >100);

		for(i=1;i<10;i++){
			System.out.println("digite outro numero entre 10 e 100");
			do{
				numero[i] = sc.nextInt();
				if(numero[i]<10 || numero[i] >100)
					System.out.println("Tem que ser entre 10 e 100");
			}while(numero[i]<10 || numero[i] >100);
				for(j=0;j<i;j++){//buscar números digitados
					if(numero[i]==numero[j]){
						System.out.println("O ultimo numero digitado e repetido!");
						numero[i]=0;
						break;//parar a busca
					}//fim if
				}//fim for
		}//fim for
		System.out.println("\nNumeros lidos sem repetir:");
		for(i=0;i<10;i++){
			if(numero[i]!=0)
				System.out.print(numero[i] + " ");
		}
	}//fim main
}//fim class
