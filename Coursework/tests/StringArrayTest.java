import org.junit.jupiter.api.Test;

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
}

