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
	
	/**
     * Tests the time calculation and sorting methods
     */
	public static void main(String[] args)
	{
		testRandomSortingTimes();
	}
	/**
     * Tests the average sorting times of random int arrays 
     * Tests bubble sort, merge sort, and pancake sort
     */
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
	/**
     * Tests bubble sort with a collection of array sizes
     * @param sizes An array containing the various sizes to use
     * @param trials The number of times to sort each array size
     * @return times A 2D array containing the size values and average runtimes
     */
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
	/**
     * Tests merge sort with a collection of array sizes
     * @param sizes An array containing the various sizes to use
     * @param trials The number of times to sort each array size
     * @return times A 2D array containing the size values and average runtimes
     */
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
    /**
     * Tests pancake sort with a collection of array sizes
     * @param sizes An array containing the various sizes to use
     * @param trials The number of times to sort each array size
     * @return times A 2D array containing the size values and average runtimes
     */
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
    /**
     * Runs bubble sort multiple times and calculates the average runtime
     * @param size The size of the array to sort
     * @param trials The number of times to run bubble sort
     */
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
    /**
     * Runs merge sort multiple times and calculates the average runtime
     * @param size The size of the array to sort
     * @param trials The number of times to run merge sort
     */
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
    /**
     * Runs pancake sort multiple times and calculates the average runtime
     * @param size The size of the array to sort
     * @param trials The number of times to run pancake sort
     */
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
    /**
     * Runs merge sort on a randomly generated array of specified size, and returns the 
     * time it took to run.
     * @param size The size of the array to sort
     */
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
    /**
     * Runs merge sort on a randomly generated array of specified size, and returns the 
     * time it took to run.
     * @param size The size of the array to sort
     */
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
    /**
     * Runs pancake sort on a randomly generated array of specified size, and returns the 
     * time it took to run.
     * @param size The size of the array to sort
     */
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
    /**
     * Sorts an array by flipping given sections of the array
     * @param array The array to be sorted
     */
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
    /**
     * Sorts an array by calling bubbleUp for every index
     * @param array The array to be sorted
     */
	public static void bubbleSort(int[] array)
	{
		for (int i = 0; i < array.length - 1; i++)
		{
			bubbleUp(array);
		}
	}
    /**
     * Compares two values in an array, and switches them if the first is bigger than the
     * second. Moves down the array and continues to compare until it reaches index 0.
     * @param array The array to be altered
     */
	private static void bubbleUp(int[] array)
	{
		for (int i = array.length - 1; i > 0; i--)
			if (array[i] < array[i - 1])
				swap(array, i, i - 1);
	}
    /**
     * Sorts an array recursively by dividing it into sections and sorting those sections.
     * @param array The array to be sorted
     * @param firstIndex The first index of the array or section to be sorted
     * @param lastIndex The last index of the array or section to be sorted
     */
	public static void mergeSort(int[] array, int firstIndex, int lastIndex)
	{
		if (lastIndex - firstIndex > 1)
		{
			mergeSort(array, firstIndex, (firstIndex + lastIndex + 1) / 2);
			mergeSort(array, (firstIndex + lastIndex + 1) / 2, lastIndex);
			merge(array, firstIndex, (firstIndex + lastIndex + 1) / 2, (firstIndex + lastIndex + 1) / 2, lastIndex);
		}
	}
    /**
     * Merges two sections of an array into one sorted section of the array.
     * @param array The array to be sorted
     * @param leftFirst The first index of the left section
     * @param leftLast The last index of the left section
     * @param rightFirst The first index of the right section
     * @param rightLast The first index of the left section
     */
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
    /**
     * Swaps two elements in a given array
     * @param array The array to be altered
     * @param i The index of one of the elements to swap
     * @param j The index of the other element to swap
     */
	private static void swap(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
    /**
     * Finds the maximum value in an array between 2 indices, inclusive.
     * @param array The array to search for the value
     * @param min The index in which to start looking for the maximum value
     * @param max The last index to look for the maximum value
     * @return the index of the maximum value between min and max
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
    /**
     * Prints out the contents of an array.
     * @param array The array to be printed out.
     */
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
    /**
     * Determine whether the array values are sorted
     * @param array The array to be checked
     */
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