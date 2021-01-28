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

        test.insert(2, "test insert");

        assertEquals("hello", test.get(0));
        assertEquals("hello2", test.get(1));
        assertEquals("test insert", test.get(2));
        assertEquals("hello3", test.get(3));
        assertEquals("hello4", test.get(4));
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
}

