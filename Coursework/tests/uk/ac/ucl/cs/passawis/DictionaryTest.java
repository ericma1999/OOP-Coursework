package uk.ac.ucl.cs.passawis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    
    private boolean setUpIsDone = false;
    Dictionary newDictionary;

    @BeforeEach
    public void setup(){
        if (!setUpIsDone){
            newDictionary = new Dictionary();
            FileInput file = new FileInput("words");
            while(file.hasNextLine()){
                newDictionary.add(file.nextLine().toLowerCase());
            }
            file.close();
            this.setUpIsDone = true;
        }
    }

    @Test
    void constructorTest(){
        assertTrue(newDictionary.contains("f"));
    }

    @Test
    void search(){
        assertEquals(1, newDictionary.indexOf("a"));
    }

}