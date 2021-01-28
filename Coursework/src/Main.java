public class Main {


    public static void main(String[] args) {
        StringArray test = new StringArray();
        test.add("hello");
        test.add("hello2");
        test.add("hello3");
        test.add("hello4");

        StringArray test2 = new StringArray(test);

        test2.print();

    }
}
