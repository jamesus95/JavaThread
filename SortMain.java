import java.util.*;

public class SortMain {

	static final int SIZE = 100000;

	public static void main(String[] args) {
		int[] x = new int[SIZE];
		Random r = new Random();
		for (int i = 0; i < SIZE; i++) {
			x[i] = r.nextInt(100);
		}
		MergeSort ms = new MergeSort();
		long start = System.nanoTime();
		ms.sort(x);
		long end = System.nanoTime();
		/*
		System.out.print("{" + x[0]);
		for (int i = 1; i < SIZE; i++) {
			System.out.print(", " + x[i]);
		}
		System.out.println("}");
		*/
		System.out.println("Time: " + (end - start));
	}

}