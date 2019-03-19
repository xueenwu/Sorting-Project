import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProjectSorts 
{
	public static int[] SIZES = {
//			5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 
//			60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 
//			800, 900, 1000, 2000, 3000, 4000, 5000, 6000, 
//			7000, 8000, 9000, 10000, 10001, 10005, 10025, 10050,
//			10060, 10070,
//			10075,
//			10100, 10200, 10250,
//			10500, 11000
			10040, 10050, 10060, 10061, 10062, 10063, 
			10064, 10065, 10066, 10067, 10068, 10069, 
			10070, 10080, 10090, 10100
			};
	public static int TRIALS = 50;
	
	public static void main(String[] args)
	{
		double[][] trials = mergeTester(SIZES, TRIALS);
		for (int i = 0; i < SIZES.length; i++)
		{
//			try
//			{
				System.out.println((int) trials[i][0] + ", " + trials[i][1]);
//			}
//			catch (FileNotFoundException ex)
//			{
//				
//			}
		}
//		System.out.println();
//		trials = mergeTester(SIZES, TRIALS);
//		for (int i = 0; i < SIZES.length; i++)
//		{
//			System.out.println((int) trials[i][0] + ": " + trials[i][1]);
//		}
		
	}
	
	public static void printOutput(String str) throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter("//Users//dtn4//Documents//projectsorts.txt");
		out.print(str);
		out.close();
	}
	
	public static double[][] bubbleTester(int[] sizes, int trials)
	{
		double[][] times = new double[sizes.length][2];
		for (int i = 0; i < sizes.length; i++)
		{
			times[i][0] = sizes[i];
			times[i][1] = averageBubble(SIZES[i], trials);
		}
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
	
	public static double[][] mergeTester(int[] sizes, int trials)
	{
		double[][] times = new double[sizes.length][2];
		for (int i = 0; i < sizes.length; i++)
		{
			times[i][0] = sizes[i];
			times[i][1] = averageMerge(SIZES[i], trials);
		}
		return times;
	}
	
	public static double averageMerge(int size, int trials)
	{
		double total = 0;
		double time;
		for (int i = 0; i < trials; i++)
		{
			time = mergeTime(size) * 1000;
			total += time;
		}
		double average = total / trials;
		return average;
	}
	
	private static double mergeTime(int size)
	{
		int[] values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = (int) (Math.random() * 100);
		long startingTime = System.nanoTime();
		mergeSort(values, 0, values.length);
		long endingTime = System.nanoTime();
		double timeTaken = (endingTime - startingTime) / Math.pow(10, 9);
		return timeTaken;
	}
	
	public static void pancakeSort(int[] array)
    {
      int currentSize = array.length;
      int max = 0;
      while (currentSize > 1)
      {
          max = maxIndex(values, 0, currentSize-1);
          flip(array, max);
          flip(array, currentSize-1);
          currentSize--;
      }
    }
    
    public static double[][] pancakeTester(int[] sizes, int trials)
    {
        double[][] times = new double[sizes.length][2];
        for (int i = 0; i < sizes.length; i++)
        {
            times[i][0] = sizes[i];
            times[i][1] = averagePancake(sizes[i], trials);
        }
        return times;
    }
    
    public static double averagePancake(int size, int trials)
    {
        double total = 0;
        double time;
        for (int i = 0; i < trials; i++)
        {
            time = pancakeTime(size) * 1000;
            total += time;
        }
        double average = total / trials;
        return average;
    }
    
    private static double pancakeTime(int size)
    {
        int[] values = new int[size];
        for (int i = 0; i < values.length; i++)
            values[i] = (int) (Math.random() * 100);
        long startingTime = System.nanoTime();
        pancakeSort(values);
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
	
	/*
   * Flips the given array from index 0 and ending at index end, inclusive
   * end must be less than SIZE
   */
   public static void flip(int[] array, int end)
   {
      int[] temp = new int[end+1];
      for (int i = 0; i <= end; i++)
      {
          temp[i] = array[end-i];
      }
      for (int i = 0; i <= end; i++)
      {
          array[i] = temp[i];
      }
   }

   /*
   * Finds the maximum value in an array between 2 indices, inclusive.
   */
   public static int maxIndex(int[] array, int min, int max)
   {
      int maxIndex = min;
      for (int i = min+1; i <= max; i++)
      {
          if(array[i] > array[maxIndex])
          {
              maxIndex = i;
          }
      }
      return maxIndex;
   }

   public static void pancakeSort(int[] array)
   {
      int currentSize = array.length;
      int max = 0;
      while (currentSize > 1)
      {
          max = maxIndex(values, 0, currentSize-1);
          flip(array, max);
          flip(array, currentSize-1);
          currentSize--;
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
