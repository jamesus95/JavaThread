import java.util.concurrent.*;

public class ThreadedMergeSort {

	private ExecutorService exec;

	public ThreadedMergeSort() {
		exec = Executors.newCachedThreadPool();
	}

}