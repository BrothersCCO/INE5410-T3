import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lider extends Thread {
	public final Aluno aluno;
	private Estante estante;

	public Lider(Aluno aluno) {
		super();
		this.aluno = aluno;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
	}

	public void run() {
		List<Livro> seq = new ArrayList<Livro>(this.estante.livros);
		seq.removeAll(this.aluno.equipe.livrosLidos);
		Collections.shuffle(seq);

		for (Livro liv : seq) {
			if (this.estante.pegarLivro(liv)) {
				aluno.equipe.setLivroAtual(liv);
				liv.ler();
				aluno.equipe.pegouLivro();
				break;
			}
		}
	}
}
