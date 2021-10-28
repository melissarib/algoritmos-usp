//IMC.java
//Calcula o índice de massa corporea de uma pessoa.

public class IMC{

	public static void main(String[] args){
		//declaração
		float massa,altura,imc;
		
		//inicialização
		System.out.println("Calculo do IMC\n\n");
		massa = (float)58.5;
		altura = (float)1.71;
		
		//lógica
		imc = massa / (altura*altura);
		System.out.println("Seu IMC " + imc);

		//categorias
		System.out.print("\nCategoria: ");
		if(imc<18.5)
			System.out.println("Subnutrido");
		else{
			if(imc<25)
				System.out.println("Peso saudavel");
			else{
				if(imc<30)
					System.out.println("Sobrepeso");
				else{
					if(imc<35)
						System.out.println("Obesidade Grau I");
					else{
						if(imc<40)
							System.out.println("Obesidade Grau II");
						else
							System.out.println("Obesidade Grau III");
					}
				}
			}
		}
	}
}

