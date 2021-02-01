import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner in = new Scanner(System.in);

        while(true){
            menu.displayMenu();
            String userInput = in.next();
            int option;
            try{
                option = Integer.parseInt(userInput);
            }catch(NumberFormatException e){
                System.out.println("You have input a text instead of a valid integer option");
                continue;
            }

            if (!menu.handleSelection(option)){
                break;
            }
        }

//
//        Dictionary dictionary = new Dictionary("words");
//
//        Tuple wordsInDictionary = dictionary.findWordsInString("hello my name is gandee");
//
//        wordsInDictionary.getIncluded().print();

    }
}
