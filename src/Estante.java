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
	
	public boolean pegarLivro(Livro livro) {
		return locks.get(livros.indexOf(livro)).tryLock();
	}
	
	public void devolverLivro(Livro livro) {
		locks.get(livros.indexOf(livro)).unlock();		
	}
	
}
