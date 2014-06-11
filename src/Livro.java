import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livro {
	public final String nome;
	public final int tempoLeitura;
	private Lock lock;

	public Livro(String nome, int tempoLeitura) {
		this.nome = nome;
		this.tempoLeitura = tempoLeitura;
		this.lock = new ReentrantLock();
	}
	
	public boolean tryLock() {
		return lock.tryLock();
	}
	
	public void unlock() {
		lock.unlock();
	}
}
