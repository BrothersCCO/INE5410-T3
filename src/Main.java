import java.util.ArrayList;
import java.util.List;

public class Main {
	public static final int fatorTempo = 100;

	public static void main(String[] args) throws InterruptedException {
		int[] tempos = { 4, 4, 4, 4, 7, 7, 7, 7 };
		List<Livro> livros = new ArrayList<Livro>(tempos.length);
		for (int i = 0; i < tempos.length; ++i) {
			livros.add(new Livro("Livro" + i, tempos[i]));
		}
		Estante estante = new Estante(livros);

		List<Aluno> alunos = new ArrayList<Aluno>(60);
		Aluno aux;
		for (int i = 0; i < 60; ++i) {
			aux = new Aluno("Aluno" + i);
			alunos.add(aux);
		}

		/**
		 * Criação das equipes com os líderes sendo convertidos a partir de um
		 * aluno
		 */
		List<Equipe> equipes = new ArrayList<Equipe>(15);
		for (int i = 0; i < 15; ++i) {
			equipes.add(new Equipe("Equipe" + i, new Lider(alunos
					.remove((int) (Math.random() * alunos.size()))), estante));
		}

		/**
		 * Sorteio dos alunos entre as equipes
		 */
		int i = 0;
		while (alunos.size() > 0) {
			equipes.get(i).addAluno(
					alunos.remove((int) (Math.random() * alunos.size())));
			i = (i + 1) % equipes.size();
		}

		for (Equipe equipe : equipes) {
			equipe.lider.start();
		}

		Thread controle = new Thread() {
			public void run() {
				try {
					for (int i = 0; i < 200; ++i) {
						Thread.sleep(Main.fatorTempo);
						System.err.println("==> Já se passou " + (i + 1)
								+ " unidade de tempo.");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		controle.start();

		while (!equipes.isEmpty() && controle.isAlive()) {
			for (Equipe equipe : equipes) {
				if (!equipe.lider.isAlive()) {
					System.err.println("\n\nUMA DAS EQUIPES FOI REMOVIDA\n\n");
					equipes.remove(equipe);
				}
			}
		}

		System.out.println("\nHooray!\n");

		for (Equipe equipe : equipes) {
			System.err.println(equipe.getNome());
			System.out.println("Livros lidos: " + equipe.livrosLidos.size());
		}
	}
}
