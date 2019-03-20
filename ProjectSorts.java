public class ProjectSorts 
{
	public static int MAX_NUMBER = 100;
	public static int MIN_NUMBER = 0;
	public static int[] SIZES = {
//			5, 10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000, 
//			2000, 3000, 4000, 5000, 10000, 20000, 30000, 40000, 50000, 100000
			60000, 70000, 80000, 90000
			};
	public static int TRIALS = 30;
	
	public static void main(String[] args)
	{
		testRandomSortingTimes();
	}
	
	public static void testRandomSortingTimes()
	{
		double[][] trials = bubbleTester(SIZES, TRIALS);
		System.out.println("Bubble:")	;
		for (int i = 0; i < SIZES.length; i++)
			System.out.println((int) trials[i][0] + ", " + trials[i][1]);	
		trials = mergeTester(SIZES, TRIALS);
		System.out.println("\nMerge:")	;
		for (int i = 0; i < SIZES.length; i++)
			System.out.println((int) trials[i][0] + ", " + trials[i][1]);	
		trials = pancakeTester(SIZES, TRIALS);
		System.out.println("\nPancake:")	;
		for (int i = 0; i < SIZES.length; i++)
			System.out.println((int) trials[i][0] + ", " + trials[i][1]);	
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

	public static double averageBubble(int size, int trials)
	{
		double total = 0;
		double time;
		for (int i = 0; i < trials; i++)
		{
			time = bubbleTime(size);
			total += time;
		}
		double average = total / trials;
		return average;
	}

	public static double averageMerge(int size, int trials)
	{
		double total = 0;
		double time;
		for (int i = 0; i < trials; i++)
		{
			time = mergeTime(size);
			total += time;
		}
		double average = total / trials;
		return average;
	}

	public static double averagePancake(int size, int trials)
	{
	    double total = 0;
	    double time;
	    for (int i = 0; i < trials; i++)
	    {
	        time = pancakeTime(size);
	        total += time;
	    }
	    double average = total / trials;
	    return average;
	}

	private static double bubbleTime(int size)
	{
		int[] values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = (int) (Math.random() * (MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER);
		long startingTime = System.nanoTime();
		bubbleSort(values);
		long endingTime = System.nanoTime();
		double timeTaken = (endingTime - startingTime) / Math.pow(10, 9);
		return timeTaken;
	}
	
	private static double mergeTime(int size)
	{
		int[] values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = (int) (Math.random() * (MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER);
		long startingTime = System.nanoTime();
		mergeSort(values, 0, values.length);
		long endingTime = System.nanoTime();
		double timeTaken = (endingTime - startingTime) / Math.pow(10, 9);
		return timeTaken;
	}
	
	private static double pancakeTime(int size)
	{
	    int[] values = new int[size];
	    for (int i = 0; i < values.length; i++)
	        values[i] = (int) (Math.random() * (MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER);
	    long startingTime = System.nanoTime();
	    pancakeSort(values);
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
	      max = maxIndex(array, 0, currentSize-1);
	      flip(array, max);
	      flip(array, currentSize-1);
	      currentSize--;
	  }
	}

	/**
	 * 
	 * Flips the given array from index 0 and ending at index end, inclusive
	 * end must be less than SIZE
	 */
	private static void flip(int[] array, int end)
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

	private static void swap(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
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