package bean;

import java.time.LocalDate;


/* usado para armazenar dados de um funcionario */
public class Funcionario {
	private String pnome;
	private String unome;
	private String cpf;
	private LocalDate dataNascimento;
	private String endereco;
	private String sexo;
	private float salario;
	private String cpfSupervisor;
	private int numDept;
	
	//construtor
	public Funcionario() {}
	
	// getters and setters
	public String getPnome() {
		return pnome;
	}
	public void setPnome(String pnome) {
		this.pnome = pnome;
	}
	public String getUnome() {
		return unome;
	}
	public void setUnome(String unome) {
		this.unome = unome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public String getCpfSupervisor() {
		return cpfSupervisor;
	}
	public void setCpfSupervisor(String cpfSupervisor) {
		this.cpfSupervisor = cpfSupervisor;
	}
	public int getNumDept() {
		return numDept;
	}
	public void setNumDept(int numDept) {
		this.numDept = numDept;
	}
	
}
