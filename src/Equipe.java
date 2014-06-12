import java.util.ArrayList;
import java.util.List;

public class Equipe {
	private String nome;
	public final List<Aluno> alunos;
	public final Lider lider;
	private Livro livroAtual;
	public final List<Livro> livrosLidos;

	public Equipe(String nome, Lider lider, Estante estante) {
		this.nome = nome;
		this.alunos = new ArrayList<Aluno>();
		lider.getAluno().setEquipe(this);
		lider.setEstante(estante);
		this.lider = lider;
		this.livrosLidos = new ArrayList<Livro>(5);
	}

	public void addAluno(Aluno aluno) {
		aluno.setEquipe(this);
		this.alunos.add(aluno);
	}

	public String getNome() {
		return nome;
	}

	public Livro getLivroAtual() {
		return livroAtual;
	}

	public void setLivroAtual(Livro livroAtual) {
		this.livroAtual = livroAtual;
	}
	
	public boolean todosLeram(List<Thread> alunos) {
		for (Thread aluno : alunos)
			if (aluno.isAlive())
				return false;
		return true;
	}

	public void pegouLivro() {
		List<Thread> alunos = new ArrayList<Thread>(this.alunos.size());
		Thread aux;
		
		for (Aluno aluno : this.alunos) {
			aux = new Thread(aluno);
			alunos.add(aux);
			aux.start();
		}
		
		while (!todosLeram(alunos)) {
		}
		
		System.out.println(nome + " terminou de ler " + livroAtual);
	}
}
