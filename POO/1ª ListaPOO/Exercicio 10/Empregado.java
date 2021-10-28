//Empregado.java
//

import java.util.Scanner;

public class Empregado{
	//atributos
	private String nome;
	private String sobrenome;
	private double salarioMensal;

	//construtor
	public Empregado(String nome, String sobrenome, double salarioMensal){
		setNome(nome);
		setSobrenome(sobrenome);
		setSalarioMensal(salarioMensal);
	}

	//métodos set
	public void setNome (String nome){
		this.nome = nome;
	}
	public void setSobrenome (String sobrenome){
		this.sobrenome = sobrenome;
	}
	public void setSalarioMensal (double salarioMensal){
		//declaração
		Scanner sc;

		//inicialização
		sc = new Scanner(System.in);

 		//teste lógico
		while(salarioMensal <=0){
			System.out.println("Digite um valor maior que 0.");
			salarioMensal = sc.nextDouble();
		}
		this.salarioMensal = salarioMensal;
	}


	//métodos get
	public String getNome (){
		return(this.nome);
	}
	public String getSobrenome (){
		return(this.sobrenome);
	}
	public double getSalarioMensal (){
		return(this.salarioMensal);
	}
}

