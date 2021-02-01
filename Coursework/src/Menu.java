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

    public boolean handleSelection(int input){
        Options value = Options.getOptionFromInteger(input);
        switch (value){
            case InputFile:
                System.out.println("selected inputFile");
                break;
            case InputText:
                System.out.println("selected inputText");
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
