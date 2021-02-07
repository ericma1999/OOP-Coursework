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
        testArray.insert(3, "insert at real end");
        assertEquals(null, testArray.get(3));


        testArray.insert(2, "insert at end");
        assertEquals("insert at end", testArray.get(2));
        assertEquals("hello3", testArray.get(testArray.size() - 1));
    }

    @Test
    void insertWhenEmpty(){
        testArray = new StringArray();
        testArray.insert(0, "hello");
        assertEquals(1, testArray.size());
        assertEquals("hello",testArray.get(0));

        testArray = new StringArray();

        testArray.insert(100, "hello");
        assertEquals(0, testArray.size());
    }

    @Test
    void removeFirst(){
        testArray.remove(0);
        assertEquals(2, testArray.size());
        assertEquals("hello2", testArray.get(0));
    }

    @Test
    void removeSingleItem(){
        testArray = new StringArray();
        testArray.add("hello");

        System.out.println(testArray.size());
        testArray.remove(0);
        System.out.println(testArray.size());

        assertTrue(testArray.isEmpty());
    }

    @Test
    void removeInvalidIndex(){
        testArray.remove(5);
        assertEquals(3, testArray.size());
    }

    @Test
    void removeLastStringReference(){
        testArray.remove(2);
        assertTrue(testArray.isEmpty());
    }

    @Test
    void removeMidItems(){
        testArray.add("hello4");

        testArray.remove(2);
        assertEquals("hello4", testArray.get(2));
        assertEquals(3, testArray.size());
    }

    @Test
    void testContains(){
        assertTrue(testArray.contains("hello"));
        assertFalse(testArray.contains("hello999"));
    }

    @Test
    void testContainsNull(){
        testArray.add(null);

        assertTrue(testArray.contains(null));
    }

    @Test
    void containsMatchingCase(){
        assertTrue( testArray.containsMatchingCase("hello"));
        assertFalse(testArray.containsMatchingCase("HELLO"));
    }

    @Test
    void indexOf(){
        assertEquals(1, testArray.indexOf("hello2"));
        assertEquals(-1, testArray.indexOf("hello555"));
    }

    @Test
    void indexOfMatchingCase(){
        assertEquals(0, testArray.indexOfMatchingCase("hello"));
        assertEquals(-1, testArray.indexOfMatchingCase("HELLO"));
    }

    @Test
    void testArrayResizing(){
        StringArray test = new StringArray();

        for (int i = 0; i < 100; i++) {
            test.add("hello".concat(String.valueOf(i)));
        }

        assertEquals(100, test.size());

        test.add("another");

        assertEquals(101, test.size());
    }

    @Test
    void testArrayResizingReduction(){
        StringArray test = new StringArray();

        for (int i = 0; i < 101; i++) {
            test.add("hello".concat(String.valueOf(i)));
        }

        assertEquals(101, test.size());

        test.actualSize();

        for (int i = 0; i < 50; i++) {
            test.remove(0);
        }

        test.actualSize();
    }

    @Test
    void testArrayResizingReduction2(){
        StringArray test = new StringArray();

        for (int i = 0; i < 151; i++) {
            test.add("hello".concat(String.valueOf(i)));
        }

//        assertEquals(101, test.size());

        test.actualSize();

        for (int i = 0; i < 50; i++) {
            test.remove(0);
        }

        test.actualSize();
    }
    @Test
    void secondConstructor(){
        StringArray testArray2 = new StringArray(testArray);

        assertEquals(3, testArray2.size());

    }

    @Test
    void testCombineEmptyStringArray(){
        StringArray secondStringArray = new StringArray();

        testArray.uniqueCombine(secondStringArray);
        assertEquals(3, testArray.size());

        testArray.print();
    }

    @Test
    void testCombineUniqueArray(){
        StringArray secondStringArray = new StringArray();
        secondStringArray.add("teehee");
        secondStringArray.add("hello2");
        secondStringArray.add("hello3");

        testArray.uniqueCombine(secondStringArray);

        assertTrue(testArray.contains("teehee"));
        assertEquals(4, testArray.size());
        testArray.print();

    }

    @Test
    void testCombineEmpty(){
        StringArray secondStringArray = new StringArray();

        secondStringArray.uniqueCombine(testArray);

        assertTrue(secondStringArray.contains("hello2"));
        assertTrue(secondStringArray.contains("hello3"));
    }

    @Test
    void testCombineNull(){
        StringArray secondStringArray = new StringArray();
        secondStringArray.add(null);

        testArray.uniqueCombine(secondStringArray);
        assertEquals(4, testArray.size());
        testArray.contains(null);
    }

}

