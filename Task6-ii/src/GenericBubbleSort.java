public class GenericBubbleSort { 
public static <T extends Comparable<T>> void bubbleSort(T[] array) { 
int n = array.length; 
for (int i = 0; i < n - 1; i++) { 
for (int j = 0; j < n - 1 - i; j++) { 
if (array[j].compareTo(array[j + 1]) > 0) { 
T temp = array[j]; 
array[j] = array[j + 1]; 
array[j + 1] = temp; 
				} 
			} 
		} 
	} 
public static void main(String[] args) { 
Integer[] intArray = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5}; 
bubbleSort(intArray); 
System.out.println("Sorted Integer Array: "); 
for (Integer num : intArray) { 
System.out.print(num + " "); 
} 
System.out.println(); 
Double[] doubleArray = {2.5, 3.8, 1.2, 5.6, 4.3}; 
bubbleSort(doubleArray); 
System.out.println("Sorted Double Array: "); 
for (Double num : doubleArray) { 
System.out.print(num + " "); 
} 
System.out.println(); 
String[] stringArray = {"Banana", "Apple", "Cherry", "Mango", "Peach"}; 
bubbleSort(stringArray); 
System.out.println("Sorted String Array: "); 
for (String str : stringArray) { 
System.out.print(str + " "); 
} 
} 
}