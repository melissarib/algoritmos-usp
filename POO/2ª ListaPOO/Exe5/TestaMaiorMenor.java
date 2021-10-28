/**
  *Testa o métod esstático da classe MaiorMenor.class
  */

public class TestaMaiorMenor{

	public static void main(String[] args){
		//declaração
		int vet[];
		int minMax[];
	
		//inicialização
		vet = {9,18,55,3,24};
	
		//lógica
		System.out.println("menor valor: "+menorVal);
		System.out.println("maior valor: "+maiorVal);
	}

	public static  int[] encontrarMaiorMenor(int v[]){
		//declaraçã
		int min;
		int max;
		int aux;

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
		return( {min,max} );		
	}//fim metodo estático

}

