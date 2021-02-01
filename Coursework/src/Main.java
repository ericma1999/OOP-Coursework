public class Main {


    public static void main(String[] args) {
        Dictionary test = new Dictionary("words");

        StringArray wordsInDictionary = test.findWordsInString("hello my name is gandee");

        wordsInDictionary.print();


    }
}
