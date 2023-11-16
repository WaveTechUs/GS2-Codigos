package cidades;

public class Cidade {
	String nome;
	int numCasos;
	double coberturaVacina;
	
	public Cidade(String nome, int numCasos, double coberturaVacina)
	{
		this.nome = nome;
		this.numCasos = numCasos;
		this.coberturaVacina = coberturaVacina;
	}
	
	
	public String getNome() {
		return this.nome;
	}
	
	public int getCasos() {
		return this.numCasos;
	}
	public double getVacina() {
		return this.coberturaVacina;
	}
	
	public int comparteTo(Cidade outraCidade){
		return Double.compare(this.getVacina(), outraCidade.getVacina());
	}

}
