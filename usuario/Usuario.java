package usuario;
import dados.*;
import cadastro.*;
public class Usuario {
	
	public static void main(String argd[]){
		Horas horas = new Horas();
		horas.setAno(2002);
		System.out.println(horas.getDataCompleta() + " --- " + horas.getHoraCompleta());
	}
	
}
