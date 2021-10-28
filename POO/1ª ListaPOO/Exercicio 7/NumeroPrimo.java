//NumeroPrimo.java
//Verifica se o numero é primo

import java.util.Scanner;

public class NumeroPrimo{

	public static void main(String[] args){
		//declaração
		Scanner sc;
		int i,n;

		//inicialização
		sc = new Scanner(System.in);
		System.out.println("entre com um numero: ");
		n = sc.nextInt();

		//
		if(n==2) System.out.println("\nPRIMO!");
		else{
			for(i=2;i<n;i++){
				if(n%i == 0){
					System.out.println("NAO E PRIMO");
					n=0;
					break;
				}
			}
			if(n!=0)
				System.out.println("\nPRIMO!");	
		}
	}
}

