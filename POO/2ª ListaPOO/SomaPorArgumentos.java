//SomaPorArgumentos.java

import java.util.Scanner;

public class SomaPorArgumentos{

	public static void main(String[] args){

		int soma;

		soma = Integer.parseInt(args[0]) + Integer.parseInt(args[1]);

		System.out.println("numeros de argumentos " + args.length);		
		System.out.println("soma "+ soma);
	}
}



