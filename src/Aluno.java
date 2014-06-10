import java.util.ArrayList;
import java.util.List;


public class Aluno extends Thread {
	private String nome;
	private Equipe equipe;
	private List<Livro> lidos;
	private Livro atual;
	private int tempoLendo;
	
	public Aluno(String nome, Equipe equipe) {
		this.nome = nome;
		this.equipe = equipe;
		this.lidos = new ArrayList<Livro>(5);
		this.atual = null;
		this.tempoLendo = 0;
	}

	public void run() {
		//this.equipe;
	}
	
	public void touch() {
		if (atual != null) {
			++tempoLendo;
		} else {
			tempoLendo = 0;
			if (lidos.size() < 5) {
				//TODO: pegar livro da equipe
			}
		}
	}
}
