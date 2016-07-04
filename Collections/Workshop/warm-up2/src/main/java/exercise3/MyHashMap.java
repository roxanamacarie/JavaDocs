
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
        return Math.abs(key.hashCode()) % capacity;

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
                if(l.get(j).getKey().equals(key)){
                    s=l.get(j).getValue();
                    break;
                }

        }
        return s;
    }

    public void put(String key, String value) {
        // TODO
        int code = 0;
        if(key != null) {
            code = hashCode(key);
        }
        LinkedList<MyEntry> list = buckets.get(code);
        for (MyEntry entry: list) {
            if(entry.getKey().equals(key))
            {
                entry.setValue(value);
                return;
            }
        }
        MyEntry newEntry = new MyEntry(key, value);
        list.add(newEntry);
    }

    public Set<String> keySet() {
        Set<String> set = new HashSet<String>() ;
        for(LinkedList<MyEntry> list : buckets){
            for(MyHashMap.MyEntry entry: list){
                set.add(entry.getKey());
            }
        }
        return set;
    }

    public Collection<String> values() {
        List<String> newColection = new ArrayList<String>();
        for(LinkedList<MyEntry> elem1 : buckets){
            for( MyEntry elem2 : elem1){
                newColection.add(elem2.getValue());
            }
        }
        return newColection;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        int code = hashCode(key);
        LinkedList<MyEntry> list = buckets.get(code);
        ListIterator<MyEntry> listIterator = list.listIterator();
        boolean OK = false;
        String resultValue = null;
        while (listIterator.hasNext()) {
            MyEntry pair = listIterator.next();
            if(pair.getKey().equals(key)){
                OK = true;
                resultValue = pair.getValue();
                listIterator.remove();
                break;
            }
        }
        if(!OK)
            return null;
        return resultValue;
    }

    public boolean containsKey(String key) {
        boolean found=false;
        for(int i=0;i<capacity;i++){
            LinkedList<MyEntry> l = buckets.get(i);
            for(int j=0;j<l.size();j++)
                if(l.get(j).getKey().equals(key)){
                    found=true;
                    break;
                }

        }
        return found;
    }

    public boolean containsValue(String value) {
            // TODO
            boolean check = false;
            for(int i = 0; i< capacity ; i++)
            {
                LinkedList<MyEntry> list = buckets.get(i);
                for(int j = 0; j < list.size(); j ++ ){
                    if(list.get(j).getValue().equals(value)){
                        check = true;
                        break;
                    }

                }
            }
            return check;
        }

    public int size() {
        int count = 0;
        for(LinkedList<MyEntry> elem1 : buckets){
            for( MyEntry elem2 : elem1){
                count ++;
            }
        }
        return count;
    }

    public void clear() {


        for(LinkedList<MyEntry> elem1 : buckets){
            Iterator<MyEntry> iter = elem1.iterator();
            while(iter.hasNext()){
                iter.next();
                iter.remove();
            }
        }
    }

    public Set<MyEntry> entrySet() {
 Set<MyEntry> entry = new LinkedHashSet<MyEntry>();
        for(LinkedList<MyEntry> elem1 : buckets){
            Iterator<MyEntry> iter = elem1.iterator();
            while(iter.hasNext()){
                entry.add(iter.next());
            }
        }

        return entry;
    }

    public boolean isEmpty() {
        // TODO
        boolean empty=false;
        for(LinkedList<MyEntry> elem1 : buckets){
            if(!elem1.isEmpty()) {
            return  false;
            }
            }
            return  true;
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
