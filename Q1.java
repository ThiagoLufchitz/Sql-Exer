import conexao.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TesteDAO {

private final Connection connection;

public TesteDAO() {
   connection = new ConnectionFactory().getConnection();
}

public void media() {
    ResultSet rs;
    CallableStatement cs;
    try {
        cs = connection.prepareCall(&quot;{call Verificar_Q(?)}&quot;);
        // setando parâmetros
        cs.setInt(&quot;valor_um&quot;, 1);
        // registrando o parâmetro da variável de retorno
        cs.registerOutParameter(1, Types.INTEGER);
        // executando a procedure
        cs.execute();            
        // mostrando o @resultado
        rs = (ResultSet) cs.getObject(1);
        while (rs.next()){
            System.out.println(rs.getInt(1));
        }
        rs.close();
        cs.close();
    }