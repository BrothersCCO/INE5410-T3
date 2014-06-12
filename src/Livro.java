import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livro {
	private String nome;
	public final int tempoLeitura;
	private Lock lock;

	public Livro(String nome, int tempoLeitura) {
		this.nome = nome;
		this.tempoLeitura = tempoLeitura;
		this.lock = new ReentrantLock();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void tryLock() {
		lock.tryLock();
	}
	
	public void lock() {
		lock.lock();
	}
	
	public void unlock() {
		lock.unlock();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Livro) {
			return toString() == obj.toString();
		}
		return false;
	}

	public String toString() {
		return nome;
	}
}
