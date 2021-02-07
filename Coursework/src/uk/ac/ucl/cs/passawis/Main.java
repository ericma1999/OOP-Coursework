package uk.ac.ucl.cs.passawis;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();

        Controller applicationController = new Controller(menu);

        applicationController.start();
    }
}
