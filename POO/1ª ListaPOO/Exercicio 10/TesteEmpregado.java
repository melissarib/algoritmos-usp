//TesteEmpregado.java
//Testa classe Empregado

public class TesteEmpregado{

	//método principal inicia a execução do aplicativo Java
	public static void main(String args[])	{
		//declaração
		Empregado e1;
		Empregado e2;

		//inicialização	
		e1 = new Empregado("Maria", "Bená", -2000);
		e2 = new Empregado("Nome", "Sobrenome", 10000);
		
		//
		System.out.println("Empregado 1 : " + e1.getNome() );
		System.out.println("Empregado 1 : " + e1.getSobrenome() );
		System.out.println("Empregado 1 : " + e1.getSalarioMensal()*12 );

		System.out.println("Empregado 2 : " + e2.getNome() );
		System.out.println("Empregado 2 : " + e2.getSobrenome() );
		System.out.println("Empregado 2 : " + e2.getSalarioMensal()*12 );

		//aumento
		e1.setSalarioMensal( e1.getSalarioMensal()*1.1 );
		e2.setSalarioMensal( e2.getSalarioMensal()*1.1 );
		
		//
		System.out.println("Empregado 1 : " + e1.getNome() );
		System.out.println("Empregado 1 : " + e1.getSobrenome() );
		System.out.println("Empregado 1 : " + e1.getSalarioMensal() );

		System.out.println("Empregado 2 : " + e2.getNome() );
		System.out.println("Empregado 2 : " + e2.getSobrenome() );
		System.out.println("Empregado 2 : " + e2.getSalarioMensal() );

	}//fim do método principal

}//fim da classe Vazi

