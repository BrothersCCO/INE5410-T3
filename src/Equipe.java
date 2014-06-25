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
		this.threads = new ArrayList<Thread>(3);
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
		return threads.size() == 0;
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
		  for (Thread th : threads) {
    		if (!th.isAlive()) {
    			threads.remove(th);
    			break;
    		}
		  }
		}
		
		//System.out.println(nome + " terminou de ler " + livroAtual);
	}
}
