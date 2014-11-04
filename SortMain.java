import java.util.*;

public class SortMain {

	static final int SIZE = 100;

	public static void main(String[] args) {
		int[] x = new int[SIZE];
		populate(x);
		MergeSort ms = new MergeSort();
		long start = System.nanoTime();
		ms.sort(x);
		long end = System.nanoTime();
		System.out.println("Time: " + (end - start));
		//print(x);

		populate(x);
		ThreadedMergeSort tms = new ThreadedMergeSort();
		start = System.nanoTime();
		tms.sort(x);
		end = System.nanoTime();
		System.out.println("Time: " + (end - start));
		//print(x);

	}

	private static void populate(int[] x) {
		Random r = new Random();
		for (int i = 0; i < SIZE; i++) {
			x[i] = r.nextInt(100);
		}
	}

	private static void print(int[] x) {
		System.out.print("{" + x[0]);
		for (int i = 1; i < SIZE; i++) {
			System.out.print(", " + x[i]);
		}
		System.out.println("}");
	}

}