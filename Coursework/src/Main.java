import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner in = new Scanner(System.in);
        Dictionary dictionary = new Dictionary("words");

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

            if (!menu.handleSelection(option, dictionary)){
                break;
            }
        }
    }
}
