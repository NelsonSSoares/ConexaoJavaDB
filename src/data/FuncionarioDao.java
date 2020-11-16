/*
 FuncionarioDao = data acess object
 */
package data;

import java.sql.Connection; // responsavel pela conexão com o banco
import java.sql.DriverManager; // Gerenciamento do endereço e log sql
import java.sql.SQLException; // tratamento de erros sql
import java.sql.PreparedStatement; // biblioteca que gera sessão(autorização) e conexão com DB de modo mais compriendivel 
//import java.sql.Statement; // sessão pata executar operação no banco (canal de acesso) executa insert updates e delete





public class FuncionarioDao {
    
    Connection con; // variavel responsavel pela conexão
    PreparedStatement st; // variavel que executa os comandos sql, por meio de sessão de autorização
    
    public boolean conectar(){
        
         /* try/ctach é uma instrução para tratamento de erros, caso não encontre
        uma classe importada ou outros comandos que podem gerar erro e comprometer
        o funcionamento do sistema
        */
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");//  indica o endereço do driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle","root","");// responsavel pela indicação do endereço do banco de dados e loggin no banco
           // st = con.createStatement();// cria uma sessão com o banco, autorização para execução de comandos sql            
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
        
        //String sql; // variavel String que vai receber o comando sql
        //sql = "INSERT INTO tb_funcionario VALUES('"+ func.getMatricula() +"','"+ func.getNome() +"','"+ func.getCargo() +"',"+ func.getSalario() + ")";
        st = con.prepareStatement("INSERT INTO tb_funcionario VALUES(?,?,?,?)"); // metodo preoperadeStatment para inserção de dados no banco de dados de forma mais legivel, porem dados tem que ser inseridos conforme coluna do banco de dados 
        st.setString(1, func.getMatricula()); // Numero indica a ordem de inserção das colunas
        st.setString(2, func.getNome()); // coloca dados no banco que estão encapsulados na classe funcionario
        st.setString(3, func.getCargo());
        st.setDouble(4, func.getSalario()); // como salario é valor real tem que colocar double ao invez de strings
        status = st.executeUpdate(); // executa o comando sql atraves da sessão iniciada no banco de dados e armazena na variavel status
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
