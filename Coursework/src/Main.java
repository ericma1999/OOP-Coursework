public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Dictionary dictionary = new Dictionary("words");
        Corrector corrector = new Corrector(dictionary, 4);

        StringArray excludedWords = new StringArray();
        excludedWords.add("llo");
        excludedWords.add("ac");


//        StringArray[] test = corrector.generateSuggestions(excludedWords);
//        for (int i = 0; i < test.length; i++) {
//            StringArray current = test[i];
//            for (int j = 0; j < current.size(); j++) {
//                System.out.println(current.get(j));
//            }
//        }


        Controller applicationController = new Controller(menu, dictionary);

        applicationController.start();
    }
}
