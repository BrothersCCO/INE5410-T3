import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Estante {
	public final List<Livro> livros;
	private final List<Lock> locks;

	public Estante(List<Livro> livros) {
		this.livros = livros;
		this.locks = new ArrayList<Lock>(livros.size());
		for (int i = 0; i < livros.size(); ++i) {
			locks.add(new ReentrantLock());
		}
	}

	public boolean pegarLivro(Livro livro) throws Exception {
		if (livros.contains(livro)) {
			return locks.get(livros.indexOf(livro)).tryLock();
		}
		throw new Exception("Livro " + livro.getNome()
				+ " não está disponível.");
	}

	public void devolverLivro(Livro livro) throws Exception {
		if (livros.contains(livro)) {
			locks.get(livros.indexOf(livro)).unlock();
		} else {
			throw new Exception("Livro " + livro.getNome()
					+ " não está disponível.");
		}
	}

	public String toString() {
		String str = new String();
		for (Livro livro : livros) {
			str.concat(livro.toString() + ",");
		}
		return str.substring(0, str.length() - 2);
	}
}
