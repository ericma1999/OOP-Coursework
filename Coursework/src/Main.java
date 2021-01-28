public class Main {


    public static void main(String[] args) {
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");

        test.insert(2, "test insert");

        test.actualSize();
    }
}
