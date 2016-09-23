/*
 * Author: Lucas Auman
 * HWK 1: Recursion Review
 * CSC330-03 Fall 2016
 */
import java.util.Arrays;

//this class uses a recursive approach to attempt to find a desired sum within
//an array of integers
public class lwauman_hwk3{ 
    private static boolean isSumPossible(int[] arr, int desiredSum){
        if(arr.length==0 && desiredSum !=0)
            return false;
        
        int sumOfArray=calculateTotalSumOfArray(arr); //sum of all elements in array
        
        if(desiredSum > sumOfArray){
            System.out.println("No solution was found.");
            return false;
        }
        
        if(desiredSum == sumOfArray){
            System.out.println("Solution found.");
            return true;
        }
        
        if(checkForSum(arr, desiredSum, 0, 0, 0, 1, 0))
            return true;
        else
            return false;
    }
    //sum of all elements in array
    private static int calculateTotalSumOfArray(int[] arr){
        int sum=0;
        for(int i=0; i<arr.length; i++)
            sum += arr[i];
        return sum;
    }
    private static boolean checkForSum(int[] arr, int desiredSum, int currentSum, int currentIndex, int indexOfElementToAdd, int nextIndexOfElementToAdd, int attemptsOnThisIndex){
        int updatedSum;
        
        //every combination has been tried
        if(currentIndex >= arr.length) 
            return false;
        
        //every combination at currentIndex has been tried
        //recursive call of method at a new currentIndex
        if(indexOfElementToAdd >= arr.length) 
            return checkForSum(arr, desiredSum, 0, currentIndex+1, currentIndex+1, currentIndex+2, 0);
        
        //this if is needed because the first element to be added 
        //to updatedSum is currentIndex
        if(attemptsOnThisIndex==0)
            updatedSum = currentSum + arr[currentIndex];
        else
            updatedSum = currentSum + arr[indexOfElementToAdd];
        
        //combination found
        if(updatedSum == desiredSum){
            System.out.println("Solution found.");
            return true;
        }
        
        //recursive call of method at the same currentIndex however the nextElement to be added
        //is incremented
        else if(updatedSum > desiredSum)
            return checkForSum(arr, desiredSum, 0, currentIndex, nextIndexOfElementToAdd, nextIndexOfElementToAdd+1, 0);
        
        //sum not found yet and currentSum is not >= desired sum. recalls method
        //so that it adds next element
        else
            return checkForSum(arr, desiredSum, updatedSum, currentIndex, indexOfElementToAdd+1, nextIndexOfElementToAdd, attemptsOnThisIndex+1);
    }
    public static void main(String[] args){
        int[] arr = {2, 5, 3, 12, 8, 10};
        System.out.println("----------------------------");
        System.out.println("Array: "+Arrays.toString(arr));
        System.out.println("----------------------------");
        System.out.println("Desired Sum: 1000");
        isSumPossible(arr, 1000);
        System.out.println("---------------------------");
        System.out.println("Desired Sum: 33");
        isSumPossible(arr, 33);
        System.out.println("---------------------------");
    }
}
