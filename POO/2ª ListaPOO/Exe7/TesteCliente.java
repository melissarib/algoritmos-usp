import java.util.Scanner;

public class TesteCliente {

	public static void main(String args[]){
		//declaracao
		int op;
		Scanner sc;
		Conta conta;
		
		//inicializacao
		sc = new Scanner(System.in);
		conta = new Conta();
		
		System.out.println("\n1. Adicionar cliente.\n2. Retirar cliente.\n3.Fazer pedido.\n4.Sair.\n\n");
		op = Integer.parseInt(sc.nextLine());
		while(op != 4){
			switch(op){
				case 1: //adicionar um cliente
					System.out.print("\nEntre com o nome do cliente a ser adicionado: ");
					conta.addClient(sc.nextLine());
					break;
				case 2: //retirar um cliente
					System.out.print("\nRetirando cliente da mesa, digite nome: ");
					conta.removeClient(sc.nextLine());
					break;
				case 3: //fazer um pedido
					System.out.print("\nDigite o valor da despesa a ser adicionada: ");
					conta.addExpense(Integer.parseInt(sc.nextLine()));
					break;
				default:
					System.out.println("Opcao de entrada invalida.");
			}
			
			conta.showConta();
			
			System.out.println("\n1. Adicionar cliente.\n2. Retirar cliente.\n3.Fazer pedido.\n4.Sair.\n\n");
			op = Integer.parseInt(sc.nextLine());
		}
	}
}