import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayTest {

    StringArray testArray;

    @BeforeEach
    void setup(){
        testArray = new StringArray();
        testArray.add("hello");
        testArray.add("hello2");
        testArray.add("hello3");

    }

    @Test
    void testAddandSize() {
        testArray.add("hello4");
        assertEquals(4, testArray.size());
    }

    @Test
    void testAddingNull(){
        testArray.add(null);
        assertEquals(4, testArray.size());
    }

    @Test
    void testRemoveandEmptyProperty() {

        assertFalse(testArray.isEmpty());

        testArray.remove(0);
        testArray.remove(0);
        testArray.remove(0);

        assertTrue(testArray.isEmpty());

    }

    @Test
    void getWrongIndex() {
        assertNull(testArray.get(-1));
        assertNull( testArray.get(3));

    }

    @Test
    void getCorrectIndex(){
        assertEquals("hello2", testArray.get(1));
    }

    @Test
    void setCorrectIndex(){
        testArray.set(2, "set item");
        assertEquals("set item", testArray.get(2));
    }

    @Test
    void setWrongIndex(){
        testArray.set(3, "wrong index");
        assertNull(testArray.get(3));
    }

    @Test
    void setAtIndexValueNull(){
        testArray.add(null);
        testArray.set(3, "not null");
        assertEquals("not null", testArray.get(3));
    }

    @Test
    void insertInMiddle(){
        testArray.insert(1, "insert middle");
        assertEquals("insert middle", testArray.get(1));
        assertEquals("hello2", testArray.get(2));
    }

    @Test
    void insertAtEnd(){
        testArray.insert(2, "insert at end");
        assertEquals("insert at end", testArray.get(2));
        assertEquals("hello3", testArray.get(testArray.size() - 1));
    }

    @Test
    void insertWhenEmpty(){
        testArray = new StringArray();
        testArray.insert(0, "hello");
        assertEquals(1, testArray.size());

        testArray = new StringArray();

        testArray.insert(100, "hello");
        assertEquals(0, testArray.size());
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
        }

        assertEquals(21, test.size());

        assertEquals("hello20", test.get(20));

        test.add("another");

        assertEquals(22, test.size());

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

