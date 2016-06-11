package cadastro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import dados.Funcionario;
import dados.FuncionarioHorista;
import dados.FuncionarioMensalista;
import dados.Horas;
import erro.*;

public class Cadastro {
	
	public static HashMap<String, Funcionario> listaFuncionarios = new HashMap<>();
	
	/**
	 * Incluir Funcionario na Lista
	 * @param obj
	 */
	public static void incluirFuncionario(Funcionario obj){
		listaFuncionarios.put(obj.getCpf(), obj);
	}
	
	/**
	 * Metodo para registrar um dia de trabalho para Horista
	 * @param obj
	 * @param data
	 * @param hora
	 */
	public static void registrarHoraTrabalhada(FuncionarioHorista obj, Horas data, float horas){
		obj.setHorasTrabalhada(data, horas);
	}
	
	/**
	 * Metodo para Resgistrar uma falta para Mensalista
	 * @param obj
	 * @param data
	 * @param motivo
	 */
	public static void registrarFalta(FuncionarioMensalista obj, Horas data, String motivo){
		obj.setFaltas(data, motivo);
	}
	
	/**
	 * Metodo para Obter Salario Mensal
	 * @author Daniel Nascimento
	 * @param cpf
	 * @param mes
	 * @param ano
	 * @return
	 */
	public static Double ObterSalarioMensal(String cpf, int mes, int ano){
		
		Funcionario funcionario = listaFuncionarios.get(cpf);
		int tipo = funcionario.getTipo();
		
		
		if(tipo == 1){
			Double horas = 0.0;
			
			ArrayList<Horas> listaData = ((FuncionarioHorista)funcionario).getDiasTrabalhads();
			ArrayList<Float> listaHoras = ((FuncionarioHorista)funcionario).getHorasTrabalhadas();
			for(Horas data : listaData){
				if (data.getMes() == mes && data.getAno() == ano){
					for(Float hora : listaHoras){
						horas += hora;
					}
				}
			}
			return ((FuncionarioHorista)funcionario).getValorHora() * horas;
			
		}else{
			
			int faltas = 0;
			ArrayList<Horas> listaDatas = ((FuncionarioMensalista)funcionario).getListaFaltas();
			for (Horas aux : listaDatas){
				if(aux.getMes() == mes && aux.getAno() == ano){
					faltas++;
				}
			}
			
			Double valorDia = (((FuncionarioMensalista)funcionario).getSalarioFixo())/ 30;
			return (((FuncionarioMensalista)funcionario).getSalarioFixo()) - (valorDia * faltas);
			
		}
		
	}
	
	/**
	 * Retornar Lista com todos os funcionarios em ordem alfabetica
	 * @return ArrayLista<Funcionario>
	 * @throws PagtoException
	 */
	public static ArrayList<Funcionario> listarTodosFuncionarios() throws PagtoException{
		
		ArrayList<Funcionario> listaAux = new ArrayList<Funcionario>();
		
		for(Funcionario aux : listaFuncionarios.values()){
			listaAux.add(aux);
		}
		
		if(listaAux.isEmpty()){
			throw new PagtoException("Não encontrdo");
		}else{
			Collections.sort(listaAux, new Comparator<Funcionario>() {
				
				@Override
				public int compare(Funcionario auxiliar1, Funcionario auxiliar2) {
					return auxiliar1.getNome().compareTo(auxiliar2.getNome());
				}
				
			});
			
			return listaAux;
		}
		
	}
	
	/**
	 * Retornar Lista de Horas Trabalhadas com Horas em ordem de Data
	 * @param cpf
	 * @param mes
	 * @param ano
	 * @return
	 * @throws PagtoException
	 */
	public static ArrayList<String> listarHorasTrabalhadas(String cpf, int mes, int ano) throws PagtoException{
		
		ArrayList<String> listaAux = new ArrayList<String>();
		
		try {
			if (listaFuncionarios.get(cpf).getTipo() == 1){
				Funcionario funcionario = listaFuncionarios.get(cpf);
				
				ArrayList<Horas> listaDatas = ((FuncionarioHorista)funcionario).getDiasTrabalhads();
				ArrayList<Float> listaHoras = ((FuncionarioHorista)funcionario).getHorasTrabalhadas();
				
				for(int i = 0; i < listaDatas.size(); i++){
					if (listaDatas.get(i).getMes() == mes && listaDatas.get(i).getAno() == ano){
						listaAux.add("Data: " + listaDatas.get(i).getDataCompleta() + "\nHoras Completas: " + listaHoras.get(i) + "Horas");
					}
				}
				
				Collections.sort(listaAux);
				
				return listaAux;
				
			}else{
				throw new PagtoException("Funcionario nao e Horista");
			}
			
		} catch (PagtoException e) {
			throw new PagtoException("Nao encontrado");
		}
		
		
	}
	
	/**
	 * Retornar lista de Faltas em ordem de data
	 * @param cpf
	 * @param mes
	 * @param ano
	 * @return
	 * @throws PagtoException
	 */
	public static ArrayList<String> listarFaltas(String cpf, int mes, int ano) throws PagtoException{

		ArrayList<String> listaAux = new ArrayList<String>();
		
		try {
			
			if (listaFuncionarios.get(cpf).getTipo() == 2){
				Funcionario funcionario = listaFuncionarios.get(cpf);
				
				ArrayList<Horas> listaDatas = ((FuncionarioMensalista)funcionario).getListaFaltas();
				ArrayList<String> listaMotivos = ((FuncionarioMensalista)funcionario).getListaMotivo();
				
				for(int i = 0; i < listaDatas.size(); i++){
					if(listaDatas.get(i).getMes() == mes && listaDatas.get(i).getAno() == ano){
						listaAux.add("Data: " + listaDatas.get(i).getDataCompleta() + "\nMotivos: " + listaMotivos.get(i));
					}
				}
				
				Collections.sort(listaAux);
				
				return listaAux;
				
			}else{
				throw new PagtoException("Este Funcionario nao e Mensalista");
			}
			
		} catch (PagtoException e) {
			throw new PagtoException("Não encontrado");
		}
	}
	
	
}
