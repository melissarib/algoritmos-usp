public class Cliente {
	private String nome;
	private float valorConta;
	/**
	* Construtor padrão. Garante que todos os clientes saibam que mais uma
	* pessoa chegou na mesa.
	*/
	public Cliente() {
		valorConta = 0;
	}
	/**
	* Método que atualiza o nome do cliente.
	*/
	public void setNome(String nome){
		this.nome = nome;
	}
	/**
	* Método que retorna o nome do cliente.
	*/
	public String getNome(){
		return nome;
	}
	/**
	* Método que notifica ao cliente que a mesa pediu um novo prato ou bebida
	* de um dado valor. O cliente deve calcular a parte que lhe cabe em relação à
	* quantidade de pessoas na mesa e atualizar-se de acordo.
	*/
	public void novaDespesa(float valor){
		valorConta = valorConta + valor;
	}
	/**
	* Método que retorna o valor atual de sua conta.
	* O método deve incluir neste cálculo os 10% do garçom.
	*/
	public float getConta(){
		return(valorConta);
	}
	/**
	* Método para fechar a conta de um cliente. Deve garantir que todos os
	* outros clientes saibam que a partir de agora há uma pessoa menos na mesa.
	* O cliente que chama este método não deve mais fazer parte do aplicativo.
	* Este método deve ser usado quando o cliente sai da mesa.
	*/
	public float pagarConta(){
		float out = (float)(getConta() + getConta()*.1);
		return(out);
	}
}