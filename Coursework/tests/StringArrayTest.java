import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayTest {

    @Test
    void defaultConstructor(){
        StringArray test = new StringArray();

        assertEquals(test.size(),0);
        assertEquals(true, test.isEmpty());
    }

    @Test
    void size() {
        StringArray test = new StringArray();
        assertEquals(test.size(),0);

        test.add("hello");
        test.add("hello2");

        assertEquals(2, test.size());

    }

    @Test
    void isEmpty() {

        StringArray test = new StringArray();

        assertEquals(true, test.isEmpty());

        test.add("hello");
        assertEquals(false, test.isEmpty());


    }

    @Test
    void get() {
        StringArray test = new StringArray();

        test.add("hello");
        test.add("hello2");
        test.add(null);

        assertEquals(null, test.get(-1));
        assertEquals(null, test.get(3));
        assertEquals("hello2", test.get(1));
    }

    @Test
    void set() {

        StringArray test = new StringArray();

        test.add("hello");
        test.add("hello2");
        test.add(null);

        test.set(2, "hello3");
        assertEquals("hello3", test.get(2));

        test.set(3, "should be nothing");

        assertEquals(null, test.get(3));

    }

    @Test
    void add() {
        StringArray test = new StringArray();

        test.add("hello");
        test.add("hello2");
        test.add(null);
        test.add("hello3");

        assertEquals("hello", test.get(0));
        assertEquals("hello2", test.get(1));
        assertEquals(null, test.get(2));
        assertEquals("hello3", test.get(3));
    }

    @Test
    void insert(){
        StringArray test = new StringArray();

        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");


//        insert in middle test
        test.insert(2, "test insert");

        assertEquals("hello", test.get(0));
        assertEquals("hello2", test.get(1));
        assertEquals("test insert", test.get(2));
        assertEquals("hello3", test.get(3));
        assertEquals("hello4", test.get(4));
        assertEquals(5, test.size());

//        insert at end test

        test.insert(4, "insert at end");

        assertEquals("hello3", test.get(3));
        assertEquals("insert at end", test.get(4));
        assertEquals("hello4", test.get(5));
        assertEquals(6, test.size());

        test = new StringArray();
        test.insert(100, "hello");
        assertEquals(true, test.isEmpty());
        assertEquals(0, test.size());


        test.insert(0, "hello");
        assertEquals(false, test.isEmpty());
        assertEquals(1, test.size());

    }

    @Test
    void removeFirstItem(){
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");

        test.remove(0);

        ArrayList<String> container = new ArrayList<String>();
        ArrayList<String> checker = new ArrayList<String>();
        checker.add("hello2");
        checker.add("hello3");
        checker.add("hello4");

        for (int i = 0; i < test.size(); i++) {
            container.add(test.get(i));
        }

        assertEquals(true, container.equals(checker));
    }
    @Test
    void removeLastItem(){
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");

        test.remove(3);

        ArrayList<String> container = new ArrayList<String>();
        ArrayList<String> checker = new ArrayList<String>();

        for (int i = 0; i < test.size(); i++) {
            container.add(test.get(i));
        }

        assertEquals(true, container.equals(checker));
        assertEquals(0, test.size());
        assertEquals(true, test.isEmpty());
    }

    @Test
    void removeMidItems(){
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");

        test.remove(1);
        ArrayList<String> container = new ArrayList<String>();
        ArrayList<String> checker = new ArrayList<String>();
        checker.add("hello");
        checker.add("hello3");
        checker.add("hello4");

        for (int i = 0; i < test.size(); i++) {
            container.add(test.get(i));
        }

        assertEquals(true, container.equals(checker));
        assertEquals(3, test.size());
        assertEquals(false, test.isEmpty());

        test.remove(1);

        container = new ArrayList<String>();
        checker = new ArrayList<String>();
        checker.add("hello");
        checker.add("hello4");

        for (int i = 0; i < test.size(); i++) {
            container.add(test.get(i));
        }

        assertEquals(true, container.equals(checker));
        assertEquals(2, test.size());
        assertEquals(false, test.isEmpty());
    }


    @Test
    void contains(){
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");

        assertEquals(false, test.contains("hello1"));
        assertEquals(true, test.contains("HELLO"));
        assertEquals(true, test.contains("hEllo2"));
        assertEquals(true, test.contains("heLLo3"));
        assertEquals(true, test.contains("hEllO4"));
    }

    @Test
    void containsMatchingCase(){
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");

        assertEquals(false, test.containsMatchingCase("hello1"));
        assertEquals(false, test.containsMatchingCase("HELLO"));
        assertEquals(true, test.containsMatchingCase("hello2"));
        assertEquals(false, test.containsMatchingCase("heLLo3"));
        assertEquals(true, test.containsMatchingCase("hello4"));
    }

    @Test
    void indexOf(){
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");
        assertEquals(-1, test.indexOf("hello1"));
        assertEquals(0, test.indexOf("HELLO"));
        assertEquals(1, test.indexOf("hello2"));
        assertEquals(2, test.indexOf("heLLo3"));
        assertEquals(3, test.indexOf("hello4"));
    }

    @Test
    void indexOfMatchingCase(){
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");
        assertEquals(-1, test.indexOfMatchingCase("hello1"));
        assertEquals(-1, test.indexOfMatchingCase("HELLO"));
        assertEquals(1, test.indexOfMatchingCase("hello2"));
        assertEquals(-1, test.indexOfMatchingCase("heLLo3"));
        assertEquals(3, test.indexOfMatchingCase("hello4"));
    }

    @Test
    void testArrayResizing(){
        StringArray test = new StringArray();

        for (int i = 0; i < 21; i++) {
            test.add("hello".concat(String.valueOf(i)));
            test.actualSize();
        }

        assertEquals(21, test.size());

        assertEquals("hello20", test.get(20));

        test.add("another");

        assertEquals(22, test.size());

        test.actualSize();
    }

    @Test
    void secondConstructor(){
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");

        StringArray test2 = new StringArray(test);

        assertEquals(4, test2.size());
        assertEquals("hello",test.get(0));
        assertEquals("hello2",test.get(1));
        assertEquals("hello3",test.get(2));
        assertEquals("hello4",test.get(3));
    }

}

