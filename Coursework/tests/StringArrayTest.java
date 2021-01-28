import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayTest {

    @Test
    void defaultConstructor(){
        StringArray test = new StringArray();

        assertEquals(test.size(),0);
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
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
    }

    @Test
    void add() {
        StringArray test = new StringArray();

        test.add("hello");
        test.add("hello2");
        test.add(null);

        assertEquals("hello", test.get(0));
        assertEquals("hello2", test.get(1));
        assertEquals(null, test.get(2));
    }
}

