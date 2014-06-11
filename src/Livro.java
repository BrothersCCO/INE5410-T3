import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livro {
	public final String nome;
	public final int tempoLeitura;
	public final Lock lock;

	public Livro(String nome, int tempoLeitura) {
		this.nome = nome;
		this.tempoLeitura = tempoLeitura;
		this.lock = new ReentrantLock();
	}
	
	public void ler() {
		lock.lock();
		try {
			Thread.sleep(this.tempoLeitura);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public void unlock() {
		lock.unlock();
	}
}
