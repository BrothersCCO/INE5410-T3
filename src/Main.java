import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		int[] tempos = { 4, 4, 4, 4, 7, 7, 7, 7 };
		List<Livro> livros = new ArrayList<Livro>(tempos.length);
		for (int tempo : tempos) {
			livros.add(new Livro(null, tempo));
		}
		Estante estante = new Estante(livros);

		List<Aluno> alunos = new ArrayList<Aluno>(60);
		Aluno aux;
		for (int i = 0; i < 60; ++i) {
			aux = new Aluno("Aluno" + i);
			aux.start();
			alunos.add(aux);
		}

		/**
		 * Criação das equipes com os líderes sendo convertidos a partir de um
		 * aluno
		 */
		List<Equipe> equipes = new ArrayList<Equipe>(15);
		for (int i = 0; i < 15; ++i) {
			equipes.add(new Equipe("Equipe" + i, new Lider(alunos
					.remove((int) (Math.random() * alunos.size()))),
					estante));
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
	}
}
