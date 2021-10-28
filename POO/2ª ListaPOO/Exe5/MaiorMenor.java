/*Implemente um método estático que use os métodos da classe Math para encontrar o maior e menor valor de um array de 5 elementos passado como parâmetro. Escreva um aplicativo para testar este método.
 */

//MaiorMenor.java
public class MaiorMenor{

	public static void encontrarMaiorMenor(int v[]){
		//declaraçã dos campos da classe
		int min, max, aux;

		//inicialização
		min = v[0];
		max = v[0];

		//lógica
		for(int i=0;i<v.lenght-1;i++){
			aux = Math.min(v[i],v[i+1]);
			if (aux<min)
				min = aux;
			aux = Math.max(v[i],v[i+1]);
			if (aux>max)
				max = aux;
		}//fim for

		System.out.println("Maior valor: " + max);
		System.out.println("Menor valor: " + min);	
	}//fim metodo estático
}//fim class

