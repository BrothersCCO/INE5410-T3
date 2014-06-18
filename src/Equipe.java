import java.util.ArrayList;
import java.util.List;

public class Equipe {
	private String nome;
	public final List<Aluno> alunos;
	public final Lider lider;
	private Livro livroAtual;
	public final List<Livro> livrosLidos;
	private List<Thread> threads;

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
	
	public void suspend() {
		for (Thread th : threads) {
			th.stop();
		}
		lider.stop();
	}

	public void pegouLivro() {
		threads = new ArrayList<Thread>(alunos.size());
		Thread aux;
		
		for (Aluno aluno : alunos) {
			aux = new Thread(aluno);
			threads.add(aux);
			aux.start();
		}
		
		while (!todosLeram(threads)) {
		}
		
		System.out.println(nome + " terminou de ler " + livroAtual);
	}
}
