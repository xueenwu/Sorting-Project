import java.util.Arrays;

public class ProjectSorts 
{
	public static int[] SIZES = {5, 10, 25, 50, 100, 250, 500, 1000, 2500};
	public static int TRIALS = 50;
	
	public static void main(String[] args)
	{
		double[] trials = bubbleTester(SIZES, TRIALS);
		for (int i = 0; i < SIZES.length; i++)
		{
			System.out.println(SIZES[i] + ": " + trials[i]);
		}
		System.out.println();
		trials = bubbleTester(SIZES, TRIALS);
		for (int i = 0; i < SIZES.length; i++)
		{
			System.out.println(SIZES[i] + ": " + trials[i]);
		}
	}
	
	public static double[] bubbleTester(int[] sizes, int trials)
	{
		double[] times = new double[sizes.length];
		for (int i = 0; i < sizes.length; i++)
			times[i] = averageBubble(SIZES[i], trials);
		return times;
	}
	
	public static double averageBubble(int size, int trials)
	{
		//The first two times are always anomalously high 
		// - I don't know why, but if you call bubble sort
		// a couple of times first that doesn't occur.
		bubbleTime(size);
		bubbleTime(size);
		double total = 0;
		double time;
		for (int i = 0; i < trials; i++)
		{
			time = bubbleTime(size) * 1000;
//			System.out.println(i + 1 + ": " + time);
			total += time;
		}
//		System.out.println("total: " + total);
		double average = total / trials;
//		System.out.println("average: " + average);
		return average;
	}
	
	private static double bubbleTime(int size)
	{
		int[] values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = (int) (Math.random() * 100);
		long startingTime = System.nanoTime();
		bubbleSort(values);
		long endingTime = System.nanoTime();
		double timeTaken = (endingTime - startingTime) / Math.pow(10, 9);
		return timeTaken;
	}
	
	public static void bubbleSort(int[] array)
	{
		for (int i = 0; i < array.length - 1; i++)
		{
			bubbleUp(array);
		}
	}
	
	private static void bubbleUp(int[] array)
	{
		for (int i = array.length - 1; i > 0; i--)
			if (array[i] < array[i - 1])
				swap(array, i, i - 1);
	}
	
	private static void swap(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void mergeSort(int[] array, int firstIndex, int lastIndex)
	{
		if (lastIndex - firstIndex > 1)
		{
			mergeSort(array, firstIndex, (firstIndex + lastIndex + 1) / 2);
			mergeSort(array, (firstIndex + lastIndex + 1) / 2, lastIndex);
			merge(array, firstIndex, (firstIndex + lastIndex + 1) / 2, (firstIndex + lastIndex + 1) / 2, lastIndex);
		}
	}
	
	private static void merge(int[] array, int leftFirst, int leftLast, int rightFirst, int rightLast)
	{
			int[] tempArray = new int[array.length];
			int saveFirst = leftFirst;
			int index = leftFirst;
			while (leftFirst < leftLast && rightFirst < rightLast)
			{
				if (array[leftFirst] < array[rightFirst])
				{
					tempArray[index] = array[leftFirst];
					leftFirst++;
				}
				else
				{
					tempArray[index] = array[rightFirst];
					rightFirst++;
				}
				index++;
			}
			while (leftFirst < leftLast)
			{
				tempArray[index] = array[leftFirst];
				leftFirst++;
				index++;
			}
			while (rightFirst < rightLast)
			{
				tempArray[index] = array[rightFirst];
				rightFirst++;
				index++;
			}
			for (int i = saveFirst; i < rightLast; i++)
			{
				array[i] = tempArray[i];
			}
	}

	public static void printArray(int[] array)
	{
		System.out.println("Values: ");
		for (int i = 0; i < array.length; i++)
		{
			if (i % 10 == 9)
				System.out.println(String.format("%02d", array[i]));
			else
    			System.out.print(String.format("%02d", array[i]) + " ");
		}
		if (array.length % 10 != 9)
			System.out.println();
	}

	public static boolean isSorted(int[] array)
    // Determine whether the array values are sorted
    {
    		boolean sorted = true;
    		for (int i = 0; i < array.length - 1; i++)
    			if (array[i] > array[i + 1])
    				sorted = false;
    		return sorted;
    }
}