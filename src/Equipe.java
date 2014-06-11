import java.util.ArrayList;
import java.util.List;

public class Equipe {
	public final List<Aluno> alunos;
	public final Lider lider;
	private Livro livroAtual;
	public final List<Livro> livrosLidos;

	public Equipe(String nome, Lider lider, Estante estante) {
		//super(nome);
		this.alunos = new ArrayList<Aluno>();
		lider.aluno.setEquipe(this);
		lider.setEstante(estante);
		this.lider = lider;
		this.livrosLidos = new ArrayList<Livro>(5);
	}

	public void addAluno(Aluno aluno) {
		aluno.setEquipe(this);
		this.alunos.add(aluno);
	}

	public Livro getLivroAtual() {
		return livroAtual;
	}

	public void setLivroAtual(Livro livroAtual) {
		this.livroAtual = livroAtual;
	}

	public void pegouLivro() {
		for (Aluno aluno : alunos) {
			aluno.interrupt();
		}
		
		boolean todosLeram = false;
		while (!todosLeram) {
			todosLeram = true;
			for (Aluno aluno : alunos) {
				if (aluno.isAlive()) {
					todosLeram = false;
					break;
				}
			}
		}
		livrosLidos.add(livroAtual);
		livroAtual = null;
	}
}
