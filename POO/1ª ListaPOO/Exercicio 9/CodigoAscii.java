//CodigoAscii.java
//Exibe os codigos da tabela ASCII correspondente a cada caractere.

import java.util.Scanner;

public class CodigoAscii{

	public static void main(String[] args){
		//declaração
		Scanner sc;
		String frase;
		int i, tam, codigo;
		char v[];

		//inicialização
		sc = new Scanner(System.in);
		System.out.println("Digite a frase:");
		frase = sc.nextLine();
		tam = frase.length();
		v = frase.toCharArray();

		//Cálculo		
		for(i=0;i<tam;i++){
			codigo = (int)v[i];
			System.out.println(v[i] + " = " + codigo);
		}
	}
}
