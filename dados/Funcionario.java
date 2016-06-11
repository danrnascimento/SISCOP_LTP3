package dados;

import java.io.Serializable;

public abstract class Funcionario implements Serializable{
	
	public static final int HORISTA = 1;
	public static final int MENSALISTA = 2;
	
	private String cpf;
	private String nome;
	
	
	public Funcionario(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public abstract int getTipo();
	
	@Override
	public String toString() {
		return "Nome: " + this.nome
				+ "\nCPF: " + this.cpf;
	}
	
}
