
package data;

public class Funcionario {

    private String matricula; //private indica que essas variaveis só podem ser manipuladas por esta classe
    private String nome;
    private String cargo;
    private double salario;
    
    //construtor da classe

    public Funcionario() {
    
    }
    //metodos getters e setters (pegue /coloque)

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
}