import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livro {
	private String nome;
	private int tempoLeitura;
	private Lock lock;

	public Livro(String nome, int tempoLeitura) {
		this.nome = nome;
		this.tempoLeitura = tempoLeitura;
		this.lock = new ReentrantLock();
	}

	public String getNome() {
		return nome;
	}

	public int getTempoLeitura() {
		return tempoLeitura;
	}
	
	public boolean tryLock() {
		return lock.tryLock();
	}
	
	public void unlock() {
		lock.unlock();
	}
}
