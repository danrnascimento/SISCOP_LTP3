package dados;

import java.io.Serializable;
import java.util.ArrayList;


public class FuncionarioMensalista extends Funcionario implements Serializable{
	
	private Double salarioFixo;
	private ArrayList<Horas> faltas = new ArrayList<>();
	private ArrayList<String> motivo = new ArrayList<>();
	
	public FuncionarioMensalista(String cpf, String nome, Double salarioFixo){
		super(cpf, nome);
		this.salarioFixo = salarioFixo;
	}
	
	public Double getSalarioFixo(){
		return this.salarioFixo;
	}
	
	public void setSalarioFixo(Double salarioFixo){
		this.salarioFixo = salarioFixo;
	}
	
	public void setFaltas(Horas data, String motivo) {
		this.faltas.add(data);
		this.motivo.add(motivo);
	}
	
	public ArrayList<Horas> getListaFaltas(){
		return faltas;
	}
	
	public ArrayList<String> getListaMotivo(){
		return motivo;
	}

	@Override
	public int getTipo() {
		return Funcionario.MENSALISTA;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nSalario: " + this.salarioFixo;
	}
	
	
}
