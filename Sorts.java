//----------------------------------------------------------------------------
// Sorts.java                  by Dale/Joyce/Weems                  Chapter 10
//
// Test harness used to run sorting algorithms
//
// Edited by F. Deppe 01-18-11
//----------------------------------------------------------------------------

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Sorts
{
    public static final int SIZE = 50;          // Size of array to be sorted
    private static int[] values = new int[SIZE];  // Values to be sorted
    
    public static void main(String[] args) throws IOException
	// Tests the other methods of the Sorts class
	{
			testQuickSort();
	}

	/**
     * Tests the quickSort method
     */
    public static void testQuickSort()
	{
			initValues();
			printValues();
			quickSort(0, SIZE - 1);
			printValues();
			System.out.println("values is sorted: " + isSorted());
			System.out.println();
	}
    
    /**
     * Sorts from from to to using the quick sort algorithm
     * @param from The index we are sorting from
     * @param to The index we are sorting to
     */
    public static void quickSort(int from, int to)
    {
    		if (from < to)
    		{
    			int p = split(from, to);
    			quickSort(from, p - 1);
    			quickSort(p + 1, to);
    		}
    }
    
    /**
     * A helper method for quickSort
     * splits the values array
     * @param from The index we are starting
     * @param to The index we are ending at
     * @return
     */
    private static int split(int from, int to)
    {
    		int pivot = values[from];
    		int first = from;
    		int last = to;
    		while (first < last)
    		{
    			first++;
    			while (first < SIZE && values[first] <= pivot)
    			{
    				first++;
    			}
    			while (values[last] > pivot)
    			{
    				last--;
    			}
    			if (first < last)
    			{
    				int temp = values[first];
    				values[first] = values[last];
    				values[last] = temp;
    			}
    		}
    		int temp = values[from];
    		values[from] = values[last];
    		values[last] = temp;
    		return last;
    }
    
    /**
	 * Uses the selection sort algorithm to sort VALUES
	 * in increasing order
	 */
	public static void selectionSort()
	{
			for (int current = 0; current < SIZE; current++)
			{
				swap(current, minIndex(current, SIZE - 1));
			}
	}

	/**
	 * Uses the insertion sort algorithm to sort VALUES
	 * in increasing order
	 */
	public static void insertionSort()
	{
			for (int i = 0; i < SIZE; i++)
			{
				insertionSwap(i);
			}
	}

	/**
	 * Swaps the element at index until it is larger than the element at 
	 * the previous element
	 * @param index The index that we are swapping 
	 */
	private static void insertionSwap(int index)
	{
			int i = index;
			while (i > 0)
			{
				if (values[i - 1] > values[i])
				{
					swap(i, i - 1);
					i--;
				}
				else
				{
					i = 0;
				}
			}
	}

	/**
	 * Sorts values using the merge sort algorithm starting from
	 * firstIndex and ending at lastIndex
	 * @param firstIndex The first index to start sorting from
	 * @param lastIndex The last index to sort to
	 */
	public static void mergeSort(int firstIndex, int lastIndex)
	{
			if (lastIndex - firstIndex > 1)
			{
				mergeSort(firstIndex, (firstIndex + lastIndex + 1) / 2);
				mergeSort((firstIndex + lastIndex + 1) / 2, lastIndex);
				merge(firstIndex, (firstIndex + lastIndex + 1) / 2, (firstIndex + lastIndex + 1) / 2, lastIndex);
			}
	}

	/**
	 * Merges the two parts of the array to be sorted, with 
	 * the indices of the left part starting from leftFirst and 
	 * going to leftLast, and the indices of the right part 
	 * starting from rightFirst and going to rightLast
	 * @param leftFirst The starting index of the left section
	 * @param leftLast The ending index of the left section
	 * @param rightFirst The starting index of the right section
	 * @param rightLast The ending index of the right section
	 */
	private static void merge(int leftFirst, int leftLast, int rightFirst, int rightLast)
	{
			int[] tempArray = new int[SIZE];
			int saveFirst = leftFirst;
			int index = leftFirst;
			while (leftFirst < leftLast && rightFirst < rightLast)
			{
				if (values[leftFirst] < values[rightFirst])
				{
					tempArray[index] = values[leftFirst];
					leftFirst++;
				}
				else
				{
					tempArray[index] = values[rightFirst];
					rightFirst++;
				}
				index++;
			}
			while (leftFirst < leftLast)
			{
				tempArray[index] = values[leftFirst];
				leftFirst++;
				index++;
			}
			while (rightFirst < rightLast)
			{
				tempArray[index] = values[rightFirst];
				rightFirst++;
				index++;
			}
			for (int i = saveFirst; i < rightLast; i++)
			{
				values[i] = tempArray[i];
			}
	}

	/**
	     * Tests the selectionSort() method
	     */
	    public static void testSelectionSort()
	    {
	    		initValues();
	        printValues();
			System.out.println("values is sorted: " + isSorted());
			System.out.println();
	
	//		swap(0, 1);
	//		printValues();
	//		System.out.println("values is sorted: " + isSorted());
	//		System.out.println();
		
	    		selectionSort();
	    		printValues();
	    		System.out.println("values is sorted: " + isSorted());
	    		System.out.println();
	    }

	/**
	 * Tests the insertionSort() method
	 */
	public static void testInsertionSort()
	{
			initValues();
			printValues();
			selectionSort();
			printValues();
			System.out.println("values is sorted: " + isSorted());
			System.out.println();
	}
    
    /**
     * Tests the mergeSort() method
     */
    public static void testMergeSort()
    {
    		initValues();
    		printValues();
    		mergeSort(0, SIZE);
    		printValues();
    		System.out.println("values is sorted: " + isSorted());
    		System.out.println();
    }
    
    /**
     * Finds the index of the minimum element between startIndex and endIndex
     * @param startIndex The first index (inclusive) that is being searched from
     * @param endIndex The last index (inclusive) that is being searched to
     * @return The index of the minimum element between startIndex and endIndex
     */
    private static int minIndex(int startIndex, int endIndex)
    {
    		int minIndex = startIndex;
    		int minValue = values[startIndex];
    		for (int i = minIndex + 1; i <= endIndex; i++)
    		{
	    		if (values[i] < minValue)
	    		{
	    			minIndex = i;
	    			minValue = values[i];
	    		}
    		}
    		return minIndex;
    }
    
    private static void initValues()
	// Initializes the values array with random integers from 0 to 99
	{
			for (int i = 0; i < SIZE; i++)
			{
				values[i] = (int) (Math.random() * 100);
			}			
	}

	public static void swap(int index1, int index2)
	// Swaps the integers at locations index1 and index2 of array values
	// Precondition:  index1 and index2 are less than size
	{
			int temp = values[index1];
			values[index1] = values[index2];
			values[index2] = temp;
	}

	public static boolean isSorted()
    // Determine whether the array values are sorted
    {
    		boolean sorted = true;
    		for (int i = 0; i < SIZE - 1; i++)
    			if (values[i] > values[i + 1])
    				sorted = false;
    		return sorted;
    }

    public static void printValues()
    //prints out the integers in the array values 
    {
    		System.out.println("Values: ");
    		for (int i = 0; i < SIZE; i++)
    		{
    			if (i % 10 == 9)
    				System.out.println(String.format("%02d", values[i]));
    			else
	    			System.out.print(String.format("%02d", values[i]) + " ");
    		}
    		if (SIZE % 10 != 9)
    			System.out.println();
    }
    
     
}