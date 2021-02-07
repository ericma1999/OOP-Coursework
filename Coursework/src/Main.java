public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
//        Dictionary dictionary = new Dictionary("words");
//        SpellChecker spellChecker = new SpellChecker(dictionary, 4);

        Controller applicationController = new Controller(menu);

        applicationController.start();
    }
}
