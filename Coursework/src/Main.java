public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Dictionary dictionary = new Dictionary("words");
        Corrector corrector = new Corrector(dictionary, 4);
        
        Controller applicationController = new Controller(menu, dictionary, corrector);

        applicationController.start();
    }
}
