import java.util.Scanner;

public class Menu {

    private Controller controller;
    private Input input = new Input();

    enum Options{
        InputFile(1), InputText(2), SetDictionary(3), Exit(4), Invalid(5);

        private int id;
        Options(int id) {
            this.id = id;
        }

        public int getValue(){
            return this.id;
        }

        public static Options getOptionFromInteger(int input){
            Options currentOption = Invalid;
            for (Options option: Options.values()){
                if (input == option.getValue()){
                    currentOption = option;
                    break;
                }
            }
            return currentOption;
        }
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void initialiseMenu(){
        while(true){
            this.displayMenu();
            String userInput = this.input.nextLine();
            int option;
            try{
                option = Integer.parseInt(userInput);
            }catch(NumberFormatException e){
                System.out.println("\nYou have input a text instead of a valid integer option\n");
                continue;
            }

            if (!this.handleSelection(option)){
                break;
            }
        }
    }

    private void displayMenu(){
        System.out.println("Coursework Dictionary Application\n");
        System.out.printf("%d.) Read a file\n", Options.InputFile.getValue());
        System.out.printf("%d.) Input text in console\n", Options.InputText.getValue());
        System.out.printf("%d.) Set dictionary source\n", Options.SetDictionary.getValue());
        System.out.printf("%d.) Exit/Close application\n", Options.Exit.getValue());
    }

    private void printStringArray(StringArray words){
        for (int i = 0; i < words.size(); i++) {
            System.out.printf("%d.) %s \n",i + 1,words.get(i));
        }
        System.out.println();
    }

    private void handleFile(){
        System.out.print("Please input your filename: ");
        String fileName = input.nextLine();
        StringArray excludedWords = controller.getExcludedWordsFromFile(fileName);
        System.out.println("\nWords not in dictionary from the file\n");
        printStringArray(excludedWords);
    }

    private void handleText(){
        System.out.print("Please input your text: ");
        String text = input.nextLine();
        StringArray excludedWords = controller.getExcludedWordsFromString(text);
        System.out.println("\nWords not in dictionary from the string inputted\n");
        printStringArray(excludedWords);
    }

    private boolean handleSelection(int input){
        Options value = Options.getOptionFromInteger(input);
        switch (value){
            case InputFile:
                handleFile();
                break;
            case InputText:
                handleText();
                break;
            case SetDictionary:
                System.out.println("selected setdictionary");
                break;
            case Invalid:
                System.out.println("Option selected was invalid, please try again");
                break;
            case Exit:
                System.out.println("Exiting program");
                return false;
            default:
                break;
        }

        return true;
    }

}
