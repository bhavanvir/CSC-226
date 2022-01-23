//Template is created by Zhuoli Xiao, on Sept. 19st, 2016.
//Only used for Lab 226, 2016 Fall.
//All Rights Reserved.

//modified by Rich Little on Sept. 23, 2016
//modified by Rich Little on May 12, 2017


import java.util.Random;
public class QuickSelect {
    //Function to invoke quickSelect
    public static int QS(int[] S, int k){
        if (S.length==1)
            return S[0];

        return quickSelect(0,S.length-1,S,k);

    }

    //do quickSelect in a recursive way
    private static int quickSelect(int left,int right, int[] array, int k) {
        //TODO if there is only one element now, just record.
        if (left == right)
            return array[left];

        //TODO pick a random pivot
        int pivot = pickRandomPivot(left, right);

        //TODO do the partition based on the pivot
        int pIndex = partition(left, right, array, pivot);

        //TODO recursive call for quickSelect
        if (pIndex == k - 1)
            return array[pIndex];
        else if (pIndex < k - 1)
            return quickSelect(pIndex + 1, right, array, k);
        else
            return quickSelect(left, pIndex - 1, array, k);
    }
    //TODO do Partition with a pivot
    private static int partition(int left, int right, int[] array, int pIndex){
        int pivot = array[pIndex];

        swap(array, pIndex, right);

        int pLocation = left;

        while(left <= right - 1){
            if(array[left] < pivot){
                swap(array, pLocation, left);
                pLocation++;
            }
            left++;
        }
        swap(array, right, pLocation);

        return pLocation;
    }

    //Pick a random pivot to do the QuickSelect
    private static int pickRandomPivot(int left, int right){
        int index=0;
        Random rand= new Random();
        index = left+rand.nextInt(right-left+1);
        return index;
    }
    //swap two elements in the array
    private static void swap(int[]array, int a, int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }


    //Our main function to test the algorithm
    public static void main (String[] args){
        //array one has duplicate elements
        int[] array1 ={12,13,17,14,21,3,4,9,21,8};
        //sorted array1 = {3,4,8,9,12,13,14,17,21,21}

        int[] array2 ={14,8,22,18,6,2,15,84,13,12};
        //sorted array2 = {2,6,8,12,13,14,15,18,22,84}

        int[] array3 ={6,8,14,18,22,2,12,13,15,84};

        int[] array4 = {1,2};

        int[] array5 = {1,1,1,2,2,4};

        System.out.println("Correct answer is 12 = " + "Your answer: "+QS(array1,5));
        System.out.println("Correct answer is 21 = " + "Your answer: "+QS(array1,10));

        System.out.println("Correct answer is 15 = " + "Your answer: "+QS(array2,7));
        System.out.println("Correct answer is 13 = " + "Your answer: "+QS(array3,5));
        System.out.println("Correct answer is 1 = " + "Your answer: "+QS(array4,1));
        System.out.println("Correct answer is 2 = " + "Your answer: "+QS(array5,5));
    }
}

