package usuario;
import java.util.Scanner;

import utilitarios.Console;
import utilitarios.LtpUtil;
import dados.*;
import erro.PagtoException;
import cadastro.*;
public class Usuario {
	
	static Scanner leia = new Scanner(System.in);
	
	public static void main(String argd[]){
		
	}
	

	public static void menu() throws PagtoException{
		int opcao = 0;
		do{
			System.out.println("\n========| \tEscolha uma das opções abaixo:\t  |========\n"
					+ "\n\tCLIENTES \n"
					+ "\n\t 1 - INSERIR UM FUNCIONARIO"
					+ "\n\tHORISTA \n"
					+ "\n\t 6 - REGISTRAR HORA TRABALHADA"
					+ "\n\t 7 - ALTERAR UM PRODUTO"
					+ "\n\t 8 - EXCLUIR UM PRODUTO"
					+ "\n\t 9 - CONSULTA PRODUTOS PELO NOME \n"
					+ "\n\tVENDAS \n"
					+ "\n\t 10 - INSERIR UMA VENDA"
					+ "\n\t 11 - EXCLUIR UMA VENDA"
					+ "\n\t 12 - CONSULTA VENDAS POR PERIODO \n"
					+ "\n\tESTATISTICAS \n"
					+ "\n\t 13 - CONSULTA ESTATISTICA DE CLIENTES POR PERIODO"
					+ "\n\t 14 - CONSULTA ESTATISTICA DE PRODUTOS POR PERIODO"
					+ "\n\t 12 - CONSULTA VENDAS POR PERIODO"
					+ "\n\t 13 - CONSULTA ESTATISTICA DE VENDAS POR PERIODO"
					+ "\n\n\t 0 - SAIR \n");

			System.out.println("Entre com a opção desejada: ");
			try {
				opcao = Integer.parseInt(leia.nextLine());
			} catch (Exception e) {
				opcao = 99;
			}
			

			switch(opcao){
			case 0: 
				break;
			case 1:		
				incluirFuncionario();
				break;
			case 2:
				registrarTrabalhoHorista();
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
			
		}while(error = true);
		
		do{
			error = false;
			System.out.println("Entre com o nome do Funcionario: ");
			nome = Console.readLine();
			
			if(nome.isEmpty()){
				System.out.println("Nome Inválido");
				error = true;
			}
			
		}while(error = true);
		
		do{
			error = false;
			int opcao = 0;
			
			do{
				try {
					System.out.println("Escolha um tipo de Participante" + "\n[1]Horista [2]Mensalista");
					opcao = Integer.parseInt(leia.nextLine());
					
					if(opcao != 1 && opcao != 2){
						System.out.println("Opcao Inválida!");
						error = true;
					}
				} catch (Exception e) {
					System.out.println("Valor Inválido");
					error = true;
				}
			}while(error = true);
			
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
				}while(error = true);
				
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
				}while(error = true);
				
				funcionario = new FuncionarioMensalista(cpf, nome, salarioFixo);
				Cadastro.incluirFuncionario(funcionario);
				break;

			default:
				System.out.println("Opcao Inválida!");
				error = true;
				break;
			}
			
		}while(error = true);
	}
	
	public static void registrarTrabalhoHorista() throws PagtoException{
		
		String cpf;
		System.out.println("Digite o cpf do Funcionario: ");
		
		
		
	}
}
