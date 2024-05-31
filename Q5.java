
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Avaliacao {
	private int fID;
	private int rID;
	private String estrelas;
	private LocalDate date;
	
	//construtor
	public Avaliacao() {}
	
	// getters and setters
	public int getfID() {
		return fID;
	}
	public void setfID(int fID) {
		this.fID = fID;
	}
	public int getrID() {
		return rID;
	}
	public void setrID(int rID) {
		this.rID = rID;
	}
	public int getestrelas() {
		return estrelas;
	}
	public void setestrelas(int estrelas) {
		this.estrelas = estrelas;
	}
	public LocalDate getdate() {
		return date;
	}
	public void setdate(LocalDate date) {
		this.date = date;
	}
}


public class FilmesDAO {
	private Connection con;
	
	/*construtor*/
	public FilmesDAO() {
		try {
			con = FabricaConexao.getConnection(); //Cria uma conexao com o servidor do BD referenciada por con
		}catch(RuntimeException e) {
			throw new RuntimeException(e); 
		}
    }
	/*consulta que retorna todos os FILMEs*/
	public Vector<Avaliacao> consultaTodos() {
		String sql = "SELECT * FROM Avaliação";
		
		// faz a busca e prepara os dados.
		try {
		  PreparedStatement stmt = con.prepareStatement(sql);

		  // executa o select
		  ResultSet rs = stmt.executeQuery();

		  //monta a lista de FILMEs
		  Vector<Avaliacao> lista = new Vector<>(); //inicialmente vazio
		  while (rs.next()) {
			    
			 Avaliacao novo = new Avaliacao(); 
			 novo.setfID(rs.getInt("fID"));
			 novo.setrID(rs.getString("rID"));
			 novo.setestrelas(rs.getString("estrelas"));
			 novo.setdate(rs.getDate("date").toLocalDate());
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



public class Main {
	public static void main(String[] args) {
		try {
            FilmesDAO fdao = new FilmesDAO(); 
		   System.out.println("Conexao estabelecida...");
           //fazendo uma consulta
		   System.out.println("Buscando todos os filme...");
		   Vector<Avaliacao> listaFILMES = fdao.consultaTodos();
		   for(Avaliacao f: listaFILMES) {
			   System.out.println(" ID filme: "+f.getfID()+" ID Revisor: "+ f.getrID()+
					              " Estrelas: "+f.getestrelas()+" Data: "+f.getData());             
		   }
		   fdao.close();   
		}catch(RuntimeException e) {
			System.out.println("Problemas de acesso");
			System.out.println(e);
		}
		
	}

}
