//MultiplosDeTres.java
//Calcula os múltiplos de 3 entre 1 e 100.

public class MultiplosDeTres{
	
	public static void main(String[] args){		
		System.out.println("Multiplos de 3");
		for(int i=1;i<=100;i++){
			if(0==i%3){
				System.out.print(" " + i);
			}
		}
		System.out.println(" ");
	}
}

