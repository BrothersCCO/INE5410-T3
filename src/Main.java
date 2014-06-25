import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Main {
	public static final int fatorTempo = 100;

	public static void main(String[] args) throws InterruptedException {
		Date inicio = new Date();

		int[] tempos = { 4, 4, 4, 4, 7, 7, 7, 7 };
		List<Livro> livros = new ArrayList<Livro>(tempos.length);
		for (int i = 0; i < tempos.length; ++i) {
			livros.add(new Livro("Livro" + i, tempos[i]));
		}
		final Estante estante = new Estante(livros);

		List<Aluno> alunos = new ArrayList<Aluno>(60);
		for (int i = 0; i < 60; ++i)
			alunos.add(new Aluno("Aluno" + i));

		/**
		 * Criação das equipes com os líderes sendo convertidos a partir de um
		 * aluno
		 */
		List<Equipe> equipes = new ArrayList<Equipe>(15);
		for (int i = 0; i < 15; ++i)
			equipes.add(new Equipe("Equipe" + i, new Lider(alunos
					.remove((int) (Math.random() * alunos.size()))), estante));

		/**
		 * Sorteio dos alunos entre as equipes
		 */
		int i = 0;
		while (alunos.size() > 0) {
			equipes.get(i).addAluno(
					alunos.remove((int) (Math.random() * alunos.size())));
			i = (i + 1) % equipes.size();
		}

		final List<Equipe> todas = new ArrayList<Equipe>(equipes);

		/**
		 * Inicializa os líderes
		 */
		for (Equipe equipe : equipes)
			equipe.lider.start();

		/**
		 * Thread de controle, quando ela morre, mostra as estatísticas
		 */
		Thread controle = new Thread() {
			public void run() {
				try {
					Thread.sleep(200 * Main.fatorTempo);

					for (Equipe equipe : todas) {
						System.out.println(equipe.getNome()
								+ "\nLivros lidos: "
								+ equipe.livrosLidos.size()
								+ (equipe.livrosLidos.size() == 5
								? " \u001B[32mterminou:)\u001B[0m"
								: " \u001B[31mnão terminou :(\u001B[0m"));
					}
					System.out.println();

					Collections.sort(estante.livros, new Comparator<Livro>() {
						public int compare(Livro a, Livro b) {
							return b.vezesLido() - a.vezesLido();
						}
					});
					for (Livro livro : estante.livros) {
						System.out.println(livro
								+ "\nVezes lido: "
								+ livro.vezesLido());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		controle.start();

		/**
		 * Verifica se ainda faltam equipes
		 */
		while (!equipes.isEmpty()) {
			for (Equipe equipe : equipes) {
				if (!equipe.lider.isAlive()) {
					equipes.remove(equipe);
					break;
				}
			}
		}
		Date fim = new Date();
		System.out.println("\nTempo para todas terminarem: " + ((fim.getTime() - inicio.getTime()) / fatorTempo));
	}
}
