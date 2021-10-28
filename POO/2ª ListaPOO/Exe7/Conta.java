public class Conta {
	private final int capac = 20;
	private static int numClients;
	Cliente c[];
	
	public Conta(){
		c = new Cliente[capac];
		numClients = 0;
	}
	
	public void addClient(String name){
		c[getNumClients()] = new Cliente();
		c[getNumClients()].setNome(name);
		numClients++;
	}
	
	public void removeClient(String name){
		for(int i=0; i<numClients; i++){
			if(c[i].getNome().equals(name)){
				for(int j=i+1; j<numClients && j<capac; j++){
					c[i] = c[j];
				}
			}
		}
		numClients--;
	}
	
	public void addExpense(float value){
		for(int i=0; i<numClients; i++){
			c[i].novaDespesa((float)(value/numClients));
		}
	}
	
	public int getNumClients(){
		return numClients;
	}
	
	public void showConta(){
		for(int i=0; i<getNumClients(); i++){
			System.out.println("Nome: "+c[i].getNome()+
			"\nCom 10garcom: "+c[i].pagarConta()+"\nSem 10garcom: "+c[i].getConta()+"\n");
		}
	}
}