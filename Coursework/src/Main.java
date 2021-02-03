import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Dictionary dictionary = new Dictionary("words");

        Controller applicationController = new Controller(menu, dictionary);

        applicationController.start();
    }
}
