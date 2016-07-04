package exercise.exercise0;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i=0;i<10;i++)
            list.add(i);

        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)

        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements

        Iterator<Integer> it = list.iterator();
        System.out.println("Iterator");
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        System.out.println();
        System.out.println("For");
        for(int i=0;i<list.size();i++)
            System.out.print(list.get(i) + " ");

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        System.out.println();
        System.out.println("For each");
        for (int i : list) {
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
        Exercise0 ex0= new Exercise0();
        ex0.iterateThroughList();

        String s1="Pet";
        String s2="Pet";
        System.out.println(s1==s2);
    }
}
