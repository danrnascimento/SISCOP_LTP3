package usuario;

import java.io.*;
import java.util.*;

import utilitarios.Console;
import utilitarios.LtpUtil;
import dados.*;
import erro.PagtoException;
import cadastro.*;
public class Usuario {
	
	static Scanner leia = new Scanner(System.in);
	
	public static void main(String argd[]) throws PagtoException{
		
		if (new File("Lista.obj").exists()) {
			lerArquivo();
		}
		
		menu();
		gravarArquivo();
	}
		
	/**
	 * Funcao para exibir menu 
	 * @throws PagtoException
	 */
	public static void menu() throws PagtoException{
		int opcao = 0;
		do{
			System.out.println("\n========| \tEscolha uma das opções abaixo:\t  |========\n"
					+ "\n\tFUNCIONARIO \n"
					+ "\n\t 1 - INSERIR UM FUNCIONARIO"
					+ "\n\n\tHORISTA \n"
					+ "\n\t2 - REGISTRAR HORA TRABALHADA"
					+ "\n\n\tMENSALISTA \n"
					+ "\n\t 3 - REGISTRAR FALTA"
					+ "\n\n\tTODOS \n"
					+ "\n\t 4 - LISTAR TODOS"
					+ "\n\n\t 0 - SAIR \n");

			System.out.println("Entre com a opção desejada: ");
			try {
				opcao = Integer.parseInt(leia.nextLine());
			} catch (Exception e) {
				opcao = 99;
			}
			

			switch(opcao){
			case 0: 
				System.out.println("Obrigado!");
				break;
			case 1:		
				incluirFuncionario();
				break;
			case 2:
				registrarTrabalhoHorista();
				break;
			case 3:
				registrarFaltaMensalista();
				break;
			case 4:
				listarTodosFuncionarios();
				break;
			default:
				System.out.println("Opção Inválida");
				break;
			}

		}while(opcao != 0);

}
	
	/**
	 * Funcao para Cadastrar um novo Funcionario
	 * @throws PagtoException
	 */
	public static void incluirFuncionario() throws PagtoException{
		
		String cpf;
		String nome;
		boolean error;
		Funcionario funcionario = null;
		
		do{
			error = false;
			System.out.println("Entre com o cpf do Funcionario: ");
			cpf = Console.readLine();
			
			if(LtpUtil.validarCPF(cpf) == false || cpf.isEmpty() || Cadastro.listaFuncionarios.containsKey(cpf)){
				System.out.println("CPF Inválido ou ja cadastrado!");
				error = true;
			}
			
		}while(error == true);
		
		do{
			error = false;
			System.out.println("Entre com o nome do Funcionario: ");
			nome = Console.readLine();
			
			if(nome.isEmpty()){
				System.out.println("Nome Inválido");
				error = true;
			}
			
		}while(error == true);
		
		do{
			error = false;
			int opcao = 0;
			
			do{
				try {
					System.out.println("Escolha um tipo de Participante: " + " [1]Horista [2]Mensalista");
					opcao = Integer.parseInt(leia.nextLine());
					
					if(opcao != 1 && opcao != 2){
						System.out.println("Opcao Inválida!");
						error = true;
					}
				} catch (Exception e) {
					System.out.println("Opcao Inválida");
					error = true;
				}
			}while(error == true);
			
			switch (opcao) {
			case 1:
				Double valorHora = null;
				error = false;
				do{
					try {
						System.out.println("Digite o valor da Hora: ");
						valorHora = Double.parseDouble(leia.nextLine());
						
						if(valorHora == null){
							System.out.println("Valor Inválido!");
							error = true;
						}
					} catch (Exception e) {
						System.out.println("Valor Invalido");
						error = true;
					}
				}while(error == true);
				
				funcionario = new FuncionarioHorista(cpf, nome, valorHora);
				Cadastro.incluirFuncionario(funcionario);
				
				break;
			
			case 2:
				Double salarioFixo = null;
				error = false;
				do{
					try {
						System.out.println("Digite o Salario: ");
						salarioFixo = Double.parseDouble(leia.nextLine());
						
						if(salarioFixo == null){
							System.out.println("Valor Inválido!");
							error = true;
						}
					} catch (Exception e) {
						System.out.println("Valor Invalido");
						error = true;
					}
				}while(error == true);
				
				funcionario = new FuncionarioMensalista(cpf, nome, salarioFixo);
				Cadastro.incluirFuncionario(funcionario);
				break;

			default:
				System.out.println("Opcao Inválida!");
				error = true;
				break;
			}
			
		}while(error == true);
	}
	
	/**
	 * Registrar Horas Trabalhadas para Horistas
	 * @throws PagtoException
	 */
	public static void registrarTrabalhoHorista() throws PagtoException{
		
		String cpf;
		FuncionarioHorista funcionario = null;
		boolean error;
		
		do{
			error = false;
			try {
				System.out.println("Digite o cpf do Funcionario: ");
				cpf = Console.readLine();
				
				if(cpf.isEmpty() || LtpUtil.validarCPF(cpf) == false){
					System.out.println("Conteudo Inválido ou vazio");
					error = true;
				}
				
				if(Cadastro.listaFuncionarios.get(cpf).getTipo() == 1){
					funcionario = (FuncionarioHorista) Cadastro.listaFuncionarios.get(cpf);
				}else{
					System.out.println("Funcionario não é Horista");
				}
			} catch (Exception e) {
				System.out.println("Funcionario não existe ou CPF inválido");
				error = true;
			}
			
		}while(error == true);
		
		do{
			error = false;
			
			int dia,mes,ano;
			float horas;
			
			try {
				System.out.println("Digite o dia do Registro");
				dia = Integer.parseInt(leia.nextLine());
				System.out.println("Digite o mes do Registro");
				mes = Integer.parseInt(leia.nextLine());
				System.out.println("Digite o ano do Registro");
				ano = Integer.parseInt(leia.nextLine());
				
				System.out.println("Digite quantas Horas o Funcionario Trabalhou");
				horas = Float.parseFloat(leia.nextLine());
				
				Horas data = new Horas(dia, mes, ano);
				
				Cadastro.registrarHoraTrabalhada(funcionario, data, horas);
				
			} catch (Exception e) {
				System.out.println();
				
			}
			
			
			
		}while(error == true);
		
		
	}
	
	/**
	 * Registrar Falta para Mensalista
	 * @throws PagtoException
	 */
	public static void registrarFaltaMensalista() throws PagtoException{
		
		String cpf;
		FuncionarioMensalista funcionario = null;
		boolean error;
		
		do{
			error = false;
			try {
				System.out.println("Digite o cpf do Funcionario: ");
				cpf = Console.readLine();
				
				if(cpf.isEmpty() || LtpUtil.validarCPF(cpf) == false){
					System.out.println("Conteudo Inválido ou vazio");
					error = true;
				}
				
				if(Cadastro.listaFuncionarios.get(cpf).getTipo() == 2){
					funcionario = (FuncionarioMensalista) Cadastro.listaFuncionarios.get(cpf);
				}else{
					System.out.println("Funcionario nao e Mensalista");
					error = true;
				}
			} catch (Exception e) {
				System.out.println("Funcionario não existe ou CPF inválido");
				error = true;
			}
			
		}while(error == true);
		
		do{
			error = false;
			
			int dia,mes,ano;
			String motivo;
			
			try {
				System.out.println("Digite o dia do Registro");
				dia = Integer.parseInt(leia.nextLine());
				System.out.println("Digite o mes do Registro");
				mes = Integer.parseInt(leia.nextLine());
				System.out.println("Digite o ano do Registro");
				ano = Integer.parseInt(leia.nextLine());
				
				System.out.println("Digite o Motivo da Falta");
				motivo = Console.readLine();
				
				Horas data = new Horas(dia, mes, ano);
				
				Cadastro.registrarFalta(funcionario, data, motivo);
				
			} catch (Exception e) {
				System.out.println();
				
			}
			
			
			
		}while(error == true);
		
		
	}
	
	/**
	 * Listar todos os Funcionarios
	 * @throws PagtoException
	 */
	public static void listarTodosFuncionarios() throws PagtoException{
		
		for(Funcionario aux : Cadastro.listaFuncionarios.values()){
			System.out.println("\n-----------------");
			System.out.println(aux.toString());
			System.out.println("\n-----------------");
		}
		
	}
	
	public static void lerArquivo() throws PagtoException{
		try {

			ObjectInputStream leitura = new ObjectInputStream(new FileInputStream("Lista.obj"));
			Cadastro.listaFuncionarios = (HashMap<String, Funcionario>) leitura.readObject();
			
			leitura.close();
			  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void gravarArquivo() throws PagtoException{
		
		try {
			
			ObjectOutputStream gravar = new ObjectOutputStream(new FileOutputStream("Lista.obj"));
			gravar.writeObject(Cadastro.listaFuncionarios);
			gravar.close();
			
		} catch (Exception e) {
			System.out.println("Rolou erro : " + e.getMessage());
}
	}
}