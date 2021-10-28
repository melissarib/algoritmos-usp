//IntervaloFibonacci.java
//Exibe os termos da sequencia de Fibonacci entre dois numeros a e b.

import java.util.Scanner;
import java.lang.String;

public class IntervaloFibonacci{

	public static void main(String[] args){
		//declaração
		Scanner sc;
		int i, a, b; 
		int pri, seg, aux;

		//inicialização
		sc = new Scanner(System.in);
		System.out.println("Digite o inicio do intervalo");
		a = sc.nextInt();
		System.out.println("Digite o fim do intervalo");
		b = sc.nextInt();
		pri = 1;
		seg = 1;

		//Teste para o intervalo começar em a
		if(a > b){
			aux = b;
			b = a;
			a = aux;
		}

		//lógica
		aux = pri + seg;
		if(a == 1)
			System.out.print(pri + " " + seg + " ");

		i=2;
		do{
			pri = seg;
			seg = aux;
			if(aux > a)		
				System.out.print(aux+", ");
			aux = pri + seg;
			i++;
		}
		while(aux<b);	
	}
}

