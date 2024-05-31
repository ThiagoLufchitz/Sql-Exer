package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import bean.Funcionario;


public class FuncionarioDAO {
	private Connection con;
	
	/*construtor*/
	public FuncionarioDAO() {
		try {
			con = FabricaConexao.getConnection(); //Cria uma conexao com o servidor do BD referenciada por con
		}catch(RuntimeException e) {
			throw new RuntimeException(e); 
		}
	}
	
	/*insere um funcionario*/
	public void insere(Funcionario funcionario) {
	    
		//comando parametrizado
		String sql = "INSERT INTO FUNCIONARIO " +
	              "(Pnome,UNome,Cpf,DataNascimento,Endereco,Sexo,Salario,CpfSupervisor,Dnr)" +
	              " VALUES (?,?,?,?,?,?,?,?,?)";

	      try {
	          // prepared statement para inserção
	          PreparedStatement stmt = con.prepareStatement(sql);

	          // seta os valores
	          stmt.setString(1, funcionario.getPnome());
	          stmt.setString(2, funcionario.getUnome());
	          stmt.setString(3, funcionario.getCpf());
	          stmt.setDate(4, Date.valueOf(funcionario.getDataNascimento()));
	          stmt.setString(5, funcionario.getEndereco());
	          stmt.setString(6, funcionario.getSexo());
	          stmt.setFloat(7, funcionario.getSalario());
	          stmt.setString(8, funcionario.getCpfSupervisor());
	          stmt.setInt(9, funcionario.getNumDept());
	          
	          // executa
	          stmt.executeUpdate();
	          stmt.close();
	      } catch (SQLException e) {
	          throw new RuntimeException(e);
	      }
	  }
	
	
	
	/*consulta que retorna todos os funcionarios*/
	public Vector<Funcionario> consultaTodos() {
		String sql = "SELECT * FROM FUNCIONARIO";
		
		// faz a busca e prepara os dados.
		try {
		  PreparedStatement stmt = con.prepareStatement(sql);

		  // executa o select
		  ResultSet rs = stmt.executeQuery();

		  //monta a lista de funcionarios
		  Vector<Funcionario> lista = new Vector<>(); //inicialmente vazio
		  while (rs.next()) {
			    
			 Funcionario novo = new Funcionario(); 
			 novo.setPnome(rs.getString("Pnome"));
			 novo.setUnome(rs.getString("UNome"));
			 novo.setCpf(rs.getString("Cpf"));
			 novo.setDataNascimento(rs.getDate("DataNascimento").toLocalDate());
		     novo.setEndereco(rs.getString("Endereco"));
		     novo.setSexo(rs.getString("Sexo"));
		     novo.setSalario(rs.getFloat("Salario"));
		     novo.setCpfSupervisor(rs.getString("CpfSupervisor"));
		     novo.setNumDept(rs.getInt("Dnr"));
		     lista.add(novo);
		  }
		  rs.close();
		  stmt.close();
		  return lista;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/* fecha a conexao */
	public void close() {
	  try {
		con.close();
	  } catch (SQLException e) {
		throw new RuntimeException(e);
	  }
	}
}
