import java.util.Scanner;

public class Controller {

    private Dictionary dictionary;
    private Menu view;

    public Controller(Menu view, Dictionary dictionary){
        this.dictionary = dictionary;
        this.view = view;
        view.setController(this);
    }

    public StringArray getExcludedWordsFromFile(String filename){
        FileInput file = new FileInput(filename);
        while (file.hasNextLine()){
            System.out.println("test");
        }
        return new StringArray();
    }

    public StringArray getExcludedWordsFromString(String text){
        return dictionary.getExcludedWordsFromString(text);
    }

    public void start(){
        Scanner in = new Scanner(System.in);
        while(true){
            view.displayMenu();
            String userInput = in.next();
            int option;
            try{
                option = Integer.parseInt(userInput);
            }catch(NumberFormatException e){
                System.out.println("You have input a text instead of a valid integer option");
                continue;
            }

            if (!view.handleSelection(option, dictionary)){
                break;
            }
        }
    }

}
