import java.util.List;


public class Equipe {
	List<Aluno> alunos;
	Lider lider;
	Livro livroAtual;
	
	public Equipe(List<Aluno> alunos, Lider lider) {
		this.alunos = alunos;
		for (Aluno aluno : alunos) {
			try {
				aluno.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.lider = lider;
	}	
}
