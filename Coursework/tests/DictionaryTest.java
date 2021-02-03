import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    Dictionary newDictionary;
    @BeforeEach
    void setup(){
        newDictionary = new Dictionary("words");
    }


    @Test
    void constructorTest(){
        assertEquals(true, newDictionary.search("A"));
    }


//    @Test()
//    void findWordsInString()
//    {
//        Tuple wordsInDictionary = newDictionary.findWordsInString("hello my name is gandee");
//        String testIncluded = "";
//        String testExcluded = "";
//
//        for (int i = 0; i < wordsInDictionary.getIncluded().size(); i++) {
//            testIncluded += wordsInDictionary.getIncluded().get(i) + " ";
//        }
//
//        for (int i = 0; i < wordsInDictionary.getExcluded().size(); i++) {
//            testExcluded += wordsInDictionary.getExcluded().get(i) + " ";
//        }
//
//
//        assertEquals("hello my name is ", testIncluded);
//        assertEquals("gandee ", testExcluded);
//    }

}