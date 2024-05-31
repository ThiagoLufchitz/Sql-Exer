package main;



import bean.Funcionario;
import dao.FuncionarioDAO;


/* Seria o pacote onde vc colocaria a lógica (parte de negocios) da aplicacao */
public class Main {
	public static void main(String[] args) {
		try {
		   FuncionarioDAO fdao = new FuncionarioDAO(); 
		   System.out.println("Conexao estabelecida...");
		   
		   //inserindo o funcionario
		   System.out.println("\nFazendo uma insercao...");
		   Funcionario novo = new Funcionario();
		   novo.setPnome("Lula");
		   novo.setUnome("Silva");
		   novo.setCpf("098761135");
		   novo.setDataNascimento(LocalDate.of(1955, Month.JANUARY, 27));
		   novo.setEndereco("Rua do Bem atibaia");
		   novo.setSexo("M");
		   novo.setSalario(20000.0f);
		   novo.setCpfSupervisor("333445555");
		   novo.setNumDept(4);
		   
		   fdao.insere(novo);
		   System.out.println("\nFuncionario inserido com sucesso");
		   
		   //fazendo uma consulta
		   System.out.println("Buscando todos os funcionarios...");
		   Vector<Funcionario> listaFuncionarios = fdao.consultaTodos();
		   for(Funcionario f: listaFuncionarios) {
			   System.out.println(" Nome: "+f.getPnome()+" Sobrenome: "+ f.getUnome()+
					              " CPF: "+f.getCpf()+" DataNascimento: "+f.getDataNascimento()+
					              " Endereco: "+f.getEndereco()+" Sexo: "+f.getSexo()+
					              " Salario: "+f.getSalario()+" CpfSupervisor: "+f.getCpfSupervisor()+
					              " Numero Depto: "+f.getNumDept());
			             
		   }
		   
		   //fechando a conexao
		   fdao.close();
		   
		}catch(RuntimeException e) {
			System.out.println("Problemas acessando o banco de dados Empresa");
			System.out.println(e);
		}
		
	}

}
