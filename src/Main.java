//Keith Francisco Emmanuel De Jervis Sequeira
//SID: 2031320
import java.util.Random;   //Inbuilt package that creates random numbers
import java.util.Scanner;  //Scanner

public class Main {
    public static void main(String[] args) {         //The main function
        Search objectOne = new Search();             //Makes an object of Search class
        int[] arrayOne = new int[250];               //Initialization of Array
        Scanner userInput = new Scanner(System.in);  //Creating Scanner object
        int size, valueTop = 250;                          //Creating required variables
        int valueBottom = 0;

        System.out.println("Enter any array size below 250:");
        size = userInput.nextInt();                                  //Scanner accepts array size
        randomArray(arrayOne, size, valueBottom, valueTop);          //Method called to create random array
        int target = getSearchTarget();                              //Returns search target
        objectOne.orderOfSearch(arrayOne, size, target);               //Calls searchOrder method of Search class
    }

    public static void randomArray(int[] array, int size, int valueBottom, int valueTop) {  //Method to create a random array
        Random objectTwo = new Random();                                                    //Creates an object of random type
        int temp;
        int tally = 0;                                                                      //Counter for the number of elements added

        while (tally < size) {                                                              //Creates an unsorted random array in a specified number range
            temp = objectTwo.nextInt(valueTop - valueBottom) + 1;                    //Range
            array[tally] = temp;
            tally++;
        }
        for (int i = 0; i < size; i++) {          //Sorting the random array
            for (int j = i + 1; j < size; j++) {
                temp = 0;
                if (array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (int x = 0; x < size; ) {  //Printing out the elements of the array
            System.out.println(array[x] + "      " + array[x + 1] + "      " + array[x + 2] + "      " + array[x + 3] + "      " + array[x + 4] + "      " + array[x + 5] + "      " + array[x + 6] + "      " + array[x + 7] + "      " + array[x + 8] + "      " + array[x + 9] + "\n");
            x = x + 10;
        }
    }

    public static int getSearchTarget() {  //Returns the search target
        System.out.println("Enter the search target");
        Scanner searchTarget = new Scanner(System.in);
        int searchElement = searchTarget.nextInt();
        return searchElement;
    }
}

class Search {
    public static int orderOfSearch(int[] array, int size, int target) {  //Order of the searches is organized
        interpolationSearch(array, size, target);                       //Calling the interpolationSearch method
        binarySearch(array, size, target);                              //Calling the binarySearch method
        hybridSearch(array, size, target);                              //Calling the hybridSearch method which is a hybrid of the Interpolation search
        return 1;
    }

    public static int hybridSearch(int[] array, int size, int target) {  //Hybrid search : Interpolation Binary hybrid search
        System.out.println("\nHybrid Search: ");
        int valueTop = (size - 1);                                       //Variables for Hybrid search
        int valueBottom = 0;
        int iteration = 0;
        int pointOfDivision = size;
        pointOfDivision = pointOfDivision / 4;                           //Array divided into 3/4
        while (target >= array[valueBottom] && target <= array[valueTop] && valueBottom <= valueTop) {
            int probe = valueBottom + (valueTop - valueBottom) * (target - array[valueBottom]) / (array[valueTop] - array[valueBottom]);  //Interpolation search equation
            iteration++;
            if (array[probe] == target) {
                System.out.println("Found search target : " + target + " at array index : " + probe);
                System.out.println("Number of iterations : " + iteration);
                return 1;
            }
            if (array[probe] < target) {
                valueBottom = probe + 1;
            } else {
                valueTop = probe - 1;
            }
            if (probe + 1 <= pointOfDivision) {                                             //Conditional statement that checks the validity of the division (3/4)
                binaryPartOfHybridSearch(array, valueBottom, valueTop, target, iteration);  //Calling the binarySearch method
                return 1;
            }
        }
        System.out.println("The search target was not found.");
        System.out.println("Number of iterations : " + iteration);
        return 1;
    }


    public static int interpolationSearch(int[] array, int size, int target) {                                                            //Interpolation search
        System.out.println("Interpolation search: ");
        int valueTop = (size - 1);                                                                                                        //Variables for Interpolation search
        int valueBottom = 0;
        int iterations = 0;                                                                                                               //Counter for the number of Iterations
        while (target >= array[valueBottom] && target <= array[valueTop] && valueBottom <= valueTop) {                                    //While loop with the condition for Interpolation
            int probe = valueBottom + (valueTop - valueBottom) * (target - array[valueBottom]) / (array[valueTop] - array[valueBottom]);
                                                                                                                                          //Finding the index where the target might be
            iterations++;
            if (array[probe] == target) {                                                                                                 //Comparing the index to the search target to see if we have a match
                System.out.println("Found search target : " + target + " at array index : " + probe);
                System.out.println("Number of iterations : " + iterations);
                return 1;
            }
            if (array[probe] < target) {                                                                                                  //Either changing the value of the bottom or the value of the top
                valueBottom = probe + 1;
            } else {
                valueTop = probe - 1;
            }
        }
        System.out.println("The search target was not found.");
        System.out.println("Number of iterations : " + iterations);
        return 1;
    }

    public static int binarySearch(int[] array, int size, int target) {  //Binary search
        System.out.println("\nBinary search: ");
        int valueBottom = 0;                                             //Variables for Binary search
        int valueTop = size - 1;
        int iterations = 0;
        int mid;
        mid = valueBottom + valueTop;                                    //Calculating the middle point
        if (mid % 2 == 1) {
            mid++;
        }
        mid = mid / 2;
        while (valueBottom <= valueTop) {                                //Condition for while loop: valueTop and valueBottom must not collide
            iterations++;
            if (array[mid] == target) {                                  //Comparing the index to the search target to see if we have a match
                System.out.println("Found search target : " + target + " at index : " + mid);
                System.out.println("Number of iterations : " + iterations);
                return 1;
            } else if (array[mid] < target) {  //Shifts valueBottom
                valueBottom = mid + 1;
            } else {                           //Shifts valueTop
                valueTop = mid - 1;
            }
            mid = valueBottom + valueTop;
            if (mid % 2 == 1) {
                mid++;
            }
            mid = mid / 2;
            if (valueBottom > valueTop) {
                System.out.println("The search target was not found.");
                System.out.println("Number of iterations : " + iterations);
            }
        }
        return 1;
    }

    public static int binaryPartOfHybridSearch(int[] array, int valueBottom, int valueTop, int iterations, int target) {
        int mid;
        mid = valueBottom + valueTop;  //Calculating the middle point
        if (mid % 2 == 1) {
            mid++;
        }
        mid = mid / 2;
        while (valueBottom <= valueTop) {
            iterations++;
            if (array[mid] == target) {  //Comparing the index to the search target to see if we have a match
                System.out.println("Found search target : " + target + " at index : " + mid);
                System.out.println("Number of iterations : " + iterations);
                return 1;
            } else if (array[mid] < target) {  //Shifts valueBottom
                valueBottom = mid + 1;
            } else {                           //Shifts valueBottom
                valueTop = mid - 1;
            }
            mid = valueBottom + valueTop;
            if (mid % 2 == 1) {
                mid++;
            }
            mid = mid / 2;
            if (valueBottom > valueTop) {
                System.out.println("The search target was not found.");
                System.out.println("Number of iterations : " + iterations);
            }
        }
        return 1;
    }
}