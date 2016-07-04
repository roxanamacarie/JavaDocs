package exercise3;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private int capacity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyHashMap myHashMap = (MyHashMap) o;

        if (capacity != myHashMap.capacity) return false;
        return buckets != null ? buckets.equals(myHashMap.buckets) : myHashMap.buckets == null;

    }


    public int hashCode(String key) {

        return key.hashCode()%capacity;
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;


        // Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>();
        for(Integer i = 0; i < capacity; i++) {

            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {
        // TODO
        String s=null;
        for(int i=0;i<capacity;i++){
            LinkedList<MyEntry> l = buckets.get(i);
            for(int j=0;j<l.size();j++)
                if(l.get(j).equals(key)){
                    s=l.get(j).getValue();
                    break;
                }

        }
        return s;
    }

    public void put(String key, String value) {
        // TODO
    }

    public Set<String> keySet() {
        // TODO
        return null;
    }

    public Collection<String> values() {
        // TODO
        return null;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        return null;
    }

    public boolean containsKey(String key) {
        boolean found=false;
        for(int i=0;i<capacity;i++){
            LinkedList<MyEntry> l = buckets.get(i);
            for(int j=0;j<l.size();j++)
                if(hashCode(key)==l.get(j).hashCode()){
                    found=true;
                    break;
                }

        }
        return found;
    }

    public boolean containsValue(String value) {
        // TODO
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        return 0;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        return null;
    }

    public boolean isEmpty() {
        // TODO
        return false;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
