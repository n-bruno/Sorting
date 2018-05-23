import java.io.*;
import java.util.*;

public class SortingMain {

    public static void main(String[] args) {
        //testPrint(10);
        /*entry[] array = new entry[10];
        array = insertSortTest(array);
        print(array);*/

        /*int i, n = 1;
        entry[] array = new entry[1000000];
        for (int h = 1; h < 1000000; h++) {
            array[h] = new entry();
        }
        try {
            DataInputStream ins = new DataInputStream(new FileInputStream(args[0]));
            DataInputStream inskey = new DataInputStream(new FileInputStream(args[1]));
            try {
                while (true) {
                    array[n].key = inskey.readInt();;
                    array[n].b = ins.readByte();
                    n++;
                }
            } catch (EOFException e) {
                System.out.println("Done reading file.");
            }
            ins.close();
            inskey.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file.");
        } catch (IOException e) {
            System.out.println("Input problem with file.");
        }
        n--;
        System.out.println("n is " + n);
        mergesort(array, 1, n);
        try {
            DataOutputStream outs = new DataOutputStream(new FileOutputStream("decoded" + args[0]));
            for (i = 1; i <= n; i++) {
                outs.writeByte(array[i].b);
            }
            outs.close();
            System.out.println("The output file is " + "decoded" + args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file.");
        } catch (IOException e) {
            System.out.println("Input problem with file.");
        }*/
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //                                                                       //  
    //      -------============= SORTING METHODS =============-------        //
    //                                                                       //
    ///////////////////////////////////////////////////////////////////////////
    
    //private static entry[] array;
    private static int m        = 0;
    private static int qcount   = 0;
    private static int icount   = 0;
    
    ////////////////////////////////////////////////////////
    //MergeSort
    ////////////////////////////////////////////////////////
    /**
     * @param a
     * @return 
     */
    public static entry[] mergeSortTest(entry[] a) {
        a = fillWithtRandom(a);
        int left = 0;
        int right = a.length - 1;
        
        mergesort(a, left, right);
        
        return a;
    }
    
    
    
    
    
    public static void mergesort(entry[] a, int top, int bottom) {
        if (top != bottom) {
            int middle = (top + bottom) / 2;
            mergesort(a, top, middle);
            mergesort(a, middle + 1, bottom);
            merge(a, top, bottom);
        }
    }

    /*
     * @param a
     */
    public static void merge(entry[] a, int top, int bottom) {
        //Variables to initialize
        int t = top;
        int middle = (top + bottom) / 2;
        int b = middle + 1;
        int i = 0;
        entry[] s = new entry[bottom - top + 1];

        while ((t <= middle) && (b <= bottom)) {
            if (a[t].key < a[b].key) {
                s[i] = a[t];
                t++;
            } else {
                s[i] = a[b];
                b++;
            }
            i++;
        }
        int last = middle;
        if (b <= bottom) {
            m++;
            t = b;
            last = bottom;
        }
        while (t <= last) {
            s[i] = a[t];
            t++;
            i++;
        }
        for (i = 0; i < s.length; i++) {
            a[i + top] = s[i];
        }
    }
    
    ////////////////////////////////////////////////////////
    // QuickSort Stuff
    ////////////////////////////////////////////////////////
    /**
     * @param a
     * @return 
     */
    public static entry[] quickSortTest(entry[] a) {
        a = fillWithtRandom(a);
        
        int left = 0;
        int right = a.length - 1;
        
        quickSort(a, left, right);
        
        return a;
    }
    
    @SuppressWarnings("empty-statement")
    private static void quickSort(entry[] a, int left, int right) {
        qcount++;
        if (left < right) {
            int pivot = a[right].key;
            int leftCursor = left;
            int rightCursor = right;
            while (leftCursor < rightCursor) {
                while (a[leftCursor].key < pivot)
                    leftCursor++;
                while (rightCursor > 0 && a[--rightCursor].key > pivot);
                if (leftCursor >= rightCursor)
                    break;
                else 
                    swap(leftCursor, rightCursor, a);
            }
            swap(leftCursor, right, a);
            quickSort(a, 0, leftCursor - 1);
            quickSort(a, leftCursor + 1, right);
        }
    }

    ////////////////////////////////////////////////////////
    // InsertSort stuff
    ////////////////////////////////////////////////////////
    
    /**
     * @param a
     * @return 
     */
    public static entry[] insertSortTest(entry[] a) {
        a = fillWithtRandom(a);  
        
        int left = 0;
        int right = a.length - 1;
        
        a = insertSort(a, left, right);
        return a;
    }
    
    public static entry[] insertSort(entry[] a, int left, int right) {
        for (int i = 1; i < a.length; i++){
            int temp = a[i].key;
            int j;
            for (j = i - 1; j >= 0 && temp < a[j].key; j--){
                a[j + 1].key = a[j].key;
                icount++;
            }
            a[j + 1].key = temp;
        }
        return a;
    }
        
    ////////////////////////////////////////////////////////
    // Stuff
    ////////////////////////////////////////////////////////
        
    private static entry[] fillWithtRandom(entry[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = new entry();
        }
        return a;
    }

    private static void swap(int left, int right, entry[] a) {
        m++;
        int temp = a[left].key;
        a[left].key = a[right].key;
        a[right].key = temp;
    }
    
    public static void print(entry[] a) {
        System.out.println("╔═════════════════════════╗");
        System.out.println("║   The contents of this lil array      ║");
        System.out.println("╠═════════════════════════╣");
        System.out.print("║  ");
        
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i].key);
            
            if (i + 1 < a.length) {
                System.out.print(", ");
            }
        }
        
        System.out.println("\n╚════════════════"
                + "═════════╝\n\n\n");
    
        System.out.println("╔═════════════════════════╗");
        System.out.println("║            Some variables             ║");
        System.out.println("╠════════════╦════════════╣");
        System.out.println("║   qcount          ║      " + qcount);
        System.out.println("║   m               ║      " + m);
        System.out.println("║   icount          ║      " + icount);
        System.out.println("╚════════════╩════════════╝");
    }
    
    
    
    public static void testPrint(int checks) {
        System.out.println("***********Running \"testPrint\"***********");
        testPrintPart2("MergeSortTest", checks);
        testPrintPart2("QuickSortTest", checks);
        testPrintPart2("InsertSortTest", checks);
    }
    
    private static void testPrintPart2(String text, int checks) {
        entry[] a;
        int[] anArray;
        int size = 1024;
        boolean error = false;
        
        for (int i = 1; i <= 4; i++){
            size *= 2;
            //System.out.println(text + " size: " + size + " in progressing.");
            a = new entry[size];
            anArray = new int[checks - 1];
                for (int j = 1; j <= checks; j++){
                    //System.out.println("Test " + j + "/" + checks);
                    if (text.equals("MergeSortTest")){
                        mergeSortTest(a);
                        anArray[i - 1] = m;
                    } else if (text.equals("QuickSortTest")){
                        quickSortTest(a);
                        anArray[i- 1] = qcount;
                    } else if (text.equals("InsertSortTest")){
                        quickSortTest(a);
                        anArray[i- 1] = icount;
                    } else {
                        System.out.println("Invalid input");
                        error = false;
                        break;
                    }
                }
            
            if (error == true)
                break;
            
            System.out.println(anArray[0]);
            System.out.println(anArray[1]);
            System.out.println(anArray[2]);
            System.out.println(anArray[3]);
            System.out.println(anArray[4]);
            System.out.println(anArray[5]);
            System.out.println(anArray[6]);
            System.out.println(anArray[7]);
            System.out.println(anArray[8]);
            System.out.println(anArray[9]);
            
            int min = anArray[0];
            int max = anArray[0];
            int sum = anArray[0];
                
            for(int l = 0; l < anArray.length; l++){
                min = anArray[0];
                max = anArray[0];
                sum = anArray[0];
                
                for(int crabs = 1; crabs < anArray.length; crabs++){
                    sum += anArray[crabs];
                    if (anArray[crabs] > max)
                        max = anArray[crabs];
                    if (anArray[crabs] < min)
                        min = anArray[crabs]; 
                }
            }
            
            System.out.println("************************");
            System.out.println("Test: " + text);
            System.out.println("Size: " + size);
            System.out.println("************************");  
                
                String string = "";
                if (text.equals("MergeSortTest"))
                    string = "m";
                else if (text.equals("QuickSortTest"))
                    string = "qcount";  
                else if (text.equals("InsertSortTest"))
                    string = "icount";  
                
                System.out.println("For the values of " + string + ":");
                System.out.println("The mininium value is " + min);
                System.out.println("The maxinum value is  " + max);
                System.out.println("The average is        " + sum / checks);
            System.out.println();
        }   
    }
}
