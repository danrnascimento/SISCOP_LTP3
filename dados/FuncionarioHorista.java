package dados;

import java.io.Serializable;
import java.util.ArrayList;


public class FuncionarioHorista extends Funcionario implements Serializable{
	 	
	private Double valorHora;
	private ArrayList<Float> horasTrabalhada = new ArrayList<>();
	private ArrayList<Horas> diasTrabalhdados =new ArrayList<>();
	
	public FuncionarioHorista(String cpf, String nome, Double valorHora){
		super(cpf, nome);
		this.valorHora = valorHora;
	}
	
	
	public Double getValorHora(){
		return this.valorHora;
	}
	
	public void setValorHora(Double valorHora){
		this.valorHora = valorHora;
	}
	
	public void setHorasTrabalhada(Horas data, float horas) {
		horasTrabalhada.add(horas);
		diasTrabalhdados.add(data);
	}
	
	public ArrayList<Float> getHorasTrabalhadas(){
		return horasTrabalhada;
	}
	
	public ArrayList<Horas> getDiasTrabalhads(){
		return diasTrabalhdados;
	}
	
	@Override
	public int getTipo() {
		return Funcionario.HORISTA;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nSalario: " + this.valorHora;
	}

	
}
