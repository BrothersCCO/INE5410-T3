public class Aluno extends Thread {
	protected Equipe equipe;
	private int livrosLidos;
	
	public Aluno(String nome) {
		super(nome);
		this.livrosLidos = 0;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public void run() {
		while (livrosLidos < 5) {
			try {
				//esperar?
			} catch (InterruptedException e) {
				System.out.println(getName());
				equipe.getLivroAtual().ler();
			}
		}
	}
}
