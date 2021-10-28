//Tabuada.java
//Calcula os 100 primeiros termos da tabuada de um número n qualquer.

public class Tabuada{

	public static void main(String[] args){
		//declaração
		int i,n;

		//inicialização
		n = 13;

		//cálculo
		for(i=0;i<=100;i++){
			System.out.println(i + " vezes " + n + " = " + i*n);
		}
	}
}

