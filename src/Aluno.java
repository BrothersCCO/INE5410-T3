public class Aluno implements Runnable {
	protected String nome;
	protected Equipe equipe;

	public Aluno(String nome) {
		this.nome = nome;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public void run() {
		equipe.getLivroAtual().lock();
		System.out.println(nome + " vai ler "
				+ equipe.getLivroAtual().getNome());
		try {
			Thread.sleep(equipe.getLivroAtual().tempoLeitura * Main.fatorTempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		equipe.getLivroAtual().unlock();
	}
}
