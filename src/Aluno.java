import java.util.ArrayList;
import java.util.List;


public class Aluno extends Thread {
	private String nome;
	private Equipe equipe;
	private List<Livro> lidos;
	
	public Aluno(String nome, Equipe equipe) {
		this.nome = nome;
		this.equipe = equipe;
		this.lidos = new ArrayList<Livro>(5);
	}

	public void run() {
		//this.equipe;
	}
}
