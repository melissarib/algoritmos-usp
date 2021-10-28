/*Implemente um método estático chamado isMultiple que determina para um par de números inteiros se o segundo número é múltiplo do primeiro. O método deve aceitar dois argumentos inteiros e retornar true se o segundo for um múltiplo do primeiro e false caso contrário. Incorpore este método a um aplicativo que insere uma série de pares interios (um par por vez) e determina se o segundo valor em cada par é um múltiplo do primeiro.
 */

//ParesMultiplos.java

import java.util.Scanner;

public class ParesMultiplos{

	public static void main(String[] args){
		//declaração
		int n1, n2;  //numeros que irão ser comparados
		boolean resultado;
		Scanner sc;

		//inicialização
		sc = new Scanner(System.in);

		//logica
		for(int i=0;i<8;i++){
			System.out.print("\nEntrando com o par. ");
			System.out.print("\n\tDigite o primiro numero: ");
			n1 = sc.nextInt();
			System.out.print("\n\tDigite o segundo numero: ");
			n2 = sc.nextInt();
			resultado = isMultiple(n1,n2);
			if(resultado)
				System.out.print("\nSao multiplos!");
			else
				System.out.print("\nNao sao multiplos!");
		}//fim for
	}//fim main

	//método
	public static boolean isMultiple(int a, int b){//em outro arquivo   classe.metodoEstatico
		if(b%a == 0)
			return true;
		return false;
	}

}//fim class
