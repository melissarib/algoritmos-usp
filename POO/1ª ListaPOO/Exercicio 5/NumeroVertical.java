//NumeroVertical.java
//Reescreve um numero inteiro com um numeral em cada linha

public class NumeroVertical{
	
	public static void main(String[] args){
		//declaração
		int i,valor,tam,aux=0;
		String numero;

		//inicialização
		numero = "1415926535";
		tam = numero.length();
		valor = Integer.parseInt(numero);

		//lógica
		for(i=tam-1;i>-1;i--){
			aux = ( valor-(valor % (int)(Math.pow(10,i))) ) / (int)(Math.pow(10,i));
			System.out.println(aux);
			valor = valor % (int)Math.pow(10,i);
		}
		System.out.println(" ");
	}
}

