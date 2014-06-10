import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Estante {
	List<Livro> livros;
	List<Lock> locks;
	
	public Estante(List<Livro> livros) {
		this.livros = livros;
		this.locks = new ArrayList<Lock>(livros.size());
		for (int i = 0; i < livros.size(); ++i) {
			locks.add(new ReentrantLock());
		}
	}
	
	public Livro pegarLivro(int index) throws IllegalAccessException {
		if (locks.get(index).tryLock()) {
			return livros.get(index);
		}
		throw new java.lang.IllegalAccessException("Livro ocupado!");
	}
	
	public void devolverLivro(Livro livro) {
		locks.get(livros.indexOf(livro)).unlock();		
	}
	
}
