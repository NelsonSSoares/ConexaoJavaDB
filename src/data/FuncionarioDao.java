/*
 FuncionarioDao = data acess object
 */
package data;

import java.sql.Connection; // responsavel pela conexão com o banco
import java.sql.DriverManager; // Gerenciamento do endereço e log sql
import java.sql.SQLException; // tratamento de erros sql
import java.sql.Statement; // sessão pata executar operação no banco (canal de acesso) executa insert updates e delete





public class FuncionarioDao {
    
    Connection con; // variavel responsavel pela conexão
    Statement st; // variavel que executa os comandos sql, por meio de sessão de autorização
    
    public boolean conectar(){
        
         /* try/ctach é uma instrução para tratamento de erros, caso não encontre
        uma classe importada ou outros comandos que podem gerar erro e comprometer
        o funcionamento do sistema
        */
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");//  indica o endereço do driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle","root","");// responsavel pela indicação do endereço do banco de dados e loggin no banco
            st = con.createStatement();// cria uma sessão com o banco, autorização para execução de comandos sql
            
            return true; 
        } catch (ClassNotFoundException | SQLException ex) { //unificando o tratatamento de erro em catch só
        
            return false; // tratamento de erro caso driver não seja localizado
        }
        
        /* } catch (SQLException ex) {
           return false; // tratamento de erro caso o banco de dados ou o login estejam incorretos
        }*/
    }
    
    public int salvar(Funcionario func){ // metodo para salvar dados no banco de dados em int para pegar codigo do erro
        
        int status;
        
        try {
        
        String sql; // variavel String que vai receber o comando sql
        sql = "INSERT INTO tb_funcionario VALUES('"+ func.getMatricula() +"','"+ func.getNome() +"','"+ func.getCargo() +"',"+ func.getSalario() + ")";
        status = st.executeUpdate(sql); // executa o comando sql atraves da sessão iniciada no banco de dados e armazena na variavel status
        return status; // Retorna o codigo da operação quando concluida
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode()); // revela o codigo do erro na IDE
            return ex.getErrorCode(); // retorna o codigo numerico do erro (sempre numero inteiro) ex: duplicação de chave primaira
        }
    }   
    
    public void desconectar(){ // metodo que desconecta do banco de dadoss
        try {
            con.close(); // desconecta do banco de dados
            
        } catch (SQLException ex) {
            
        }
    }
    
    
    
}
