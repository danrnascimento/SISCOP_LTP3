package dados;

import java.util.GregorianCalendar;

public class Horas {

	private String horaCompleta;
	private String dataCompleta;
	private int hora = new GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY);
	private int minuto = new GregorianCalendar().get(GregorianCalendar.MINUTE);
	private int segundos = new GregorianCalendar().get(GregorianCalendar.SECOND);
	private int dia = new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH);
	private int mes = new GregorianCalendar().get(GregorianCalendar.MONTH) + 1;
	private int ano = new GregorianCalendar().get(GregorianCalendar.YEAR);
	
	public String getHoraCompleta() {
		return this.hora + ":" + this.minuto + ":" + this.segundos;
	}
	public String getDataCompleta(){
		return this.dia + "/" + this.mes + "/" + this.ano;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
}
