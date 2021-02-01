import java.util.Scanner;

public class Menu {
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

    public void displayMenu(){
        System.out.println("Coursework Dictionary Application");
        System.out.printf("%d.) Read a file\n", Options.InputFile.getValue());
        System.out.printf("%d.) Input text in console\n", Options.InputText.getValue());
        System.out.printf("%d.) Set dictionary source\n", Options.SetDictionary.getValue());
        System.out.printf("%d.) Exit/Close application\n", Options.Exit.getValue());
    }

    private void handleFile(String fileName, Dictionary dictionary){
        FileInput file = new FileInput(fileName);
        while (file.hasNextLine()){
            StringArray.list(handleText(file.nextLine(), dictionary));
        }
    }

    private StringArray handleText(String text, Dictionary dictionary){
        return dictionary.getExcludedWordsFromString(text);
    }

    public boolean handleSelection(int input, Dictionary dictionary){
        Scanner in = new Scanner(System.in);
        Options value = Options.getOptionFromInteger(input);
        switch (value){
            case InputFile:
                String fileName = in.nextLine();
                handleFile(fileName, dictionary);
                break;
            case InputText:
                String text = in.nextLine();
                StringArray.list(handleText(text, dictionary));
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
