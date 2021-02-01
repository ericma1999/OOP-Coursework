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


    @Test()
    void findWordsInString()
    {
        StringArray wordsInDictionary = newDictionary.findWordsInString("hello my name is gandee");
        String testString = "";
        for (int i = 0; i < wordsInDictionary.size(); i++) {
            testString += wordsInDictionary.get(i) + " ";
        }
        assertEquals("hello my name is ", testString);

    }

}