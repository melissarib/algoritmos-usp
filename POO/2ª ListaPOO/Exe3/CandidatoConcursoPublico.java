/*Implemente um aplicativo para controlar os candidatos de uma prova de concurso público.
 *O aplicativo deve armazenar os nomes dos candidatos inscritos no concurso. O número
 *máximo de candidatos armazenados deve ser informado como argumento de linha de
 *comando. O sistema deve ter as seguintes opções:
 *1 – Cadastrar candidato
 *2 – Listar candidatos
 *3 – Contar candidatos
 *4 – Sair
 *A opção 1 (cadastrar candidatos) só deve permitir que um candidato seja cadastrado por
 *vez. Para cadastrar mais de um candidato, essa opção precisa ser usada múltiplas vezes.
 */

//CandidatoConcursoPublico.java

import java.util.Scanner;

public class CandidatoConcursoPublico{

	public static void main(String[] args){
		//declaração
		int nInscrito;		
		int op;
		Scanner sc;
		String[] nomeCandidato;
		int teste;
		
		//inicialização
		sc = new Scanner(System.in);
		nomeCandidato = new String[ Integer.parseInt(args[0]) ];
		nInscrito = 0;

		//menu
		do{
			System.out.println("\n1 – Cadastrar candidato");
			System.out.println("2 – Listar candidatos");
			System.out.println("3 – Contar candidatos");
			System.out.println("4 – Sair");
			op = sc.nextInt();			
			switch(op){
				case 1:
					if(nInscrito < nomeCandidato.length){
						System.out.println("Digite o nome do Candidato");
						nomeCandidato[nInscrito] = sc.nextLine();
						nInscrito++;
					}
					else
						System.out.println("Lista cheia");
					break;

				case 2:
					for(int i=0;i<nInscrito;i++)
						System.out.println("Candidato "+ (i+1) +" - "+ nomeCandidato[i]);
					System.out.println("FIM FOR");
					break;

				case 3:
					System.out	.println("Total de candidatos inscritos: " + nInscrito);
					break;

				case 4:
					break;

				default:
					System.out.println("Opcao incorreta");
		
			}//fim switch
		}//fim do	
		while(op!=4);		
	}//fim main
}//fim class

