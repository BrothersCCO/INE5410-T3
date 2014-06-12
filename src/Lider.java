import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lider extends Thread {
	private Aluno aluno;
	private Estante estante;

	public Lider(Aluno aluno) {
		super();
		this.aluno = aluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
	}

	private void devolverLivro() {
		try {
			this.estante.devolverLivro(this.aluno.equipe.getLivroAtual());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Livro pegarLivro() {
		while (true) {
			List<Livro> seq = new ArrayList<Livro>(this.estante.livros);
			seq.removeAll(this.aluno.equipe.livrosLidos);
			Collections.shuffle(seq);

			for (Livro livro : seq) {
				try {
					if (this.estante.pegarLivro(livro)) {
						return livro;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void run() {
		for (int i = 0; i < 5; ++i) {
			Livro livro = pegarLivro();

			// procedimento de ler o livro: lock, esperar por tempo * fator,
			// unlock, dar pra equipe
			aluno.equipe.setLivroAtual(livro);
			aluno.run();

			aluno.equipe.pegouLivro();
			
			aluno.equipe.livrosLidos.add(livro);

			devolverLivro();
			System.out.println(aluno.nome + " devolveu " + livro.getNome());
		}
	}
}
