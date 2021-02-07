package uk.ac.ucl.cs.passawis;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
//        main.Dictionary dictionary = new main.Dictionary("words");
//        main.SpellChecker spellChecker = new main.SpellChecker(dictionary, 4);

        Controller applicationController = new Controller(menu);

        applicationController.start();
    }
}
