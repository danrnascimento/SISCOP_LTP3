package dados;

import java.util.ArrayList;


public class FuncionarioHorista extends Funcionario{
	 	
	private Double valorHora;
	private ArrayList<Horas> horasTrabalhada = new ArrayList<>();
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
	
	public void setHorasTrabalhada(Horas data, Horas hora) {
		horasTrabalhada.add(hora);
		diasTrabalhdados.add(data);
	}
	
	public ArrayList<Horas> getHorasTrabalhadas(){
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
