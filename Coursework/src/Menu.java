public class Menu {

    private Controller controller;
    private final Input input = new Input();

    enum Options{
        InputFile(1), InputText(2), Exit(3), Invalid(4);

        private final int id;
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
        System.out.printf("%d.) Exit/Close application\n", Options.Exit.getValue());
    }

    private void printStringArrayHorizontal(StringArray words){
        for (int i = 0; i < words.size(); i++) {
            System.out.printf("%d.) %s ",i + 1,words.get(i));
            if (i % 4 == 0 && i != 0){
                System.out.println();
            }
        }
        System.out.println();
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

        if(excludedWords.size() > 0){
            System.out.println("\nWords not in dictionary from the file\n");
            printStringArray(excludedWords);
        }else{
            System.out.println("\nAll words were in the dictionary\n");
        }
    }

    private String getInput(String text){
        System.out.printf(text + ": ");
        return input.nextLine();
    }

    public void handleWriteFile(String correctedText){

        System.out.printf("Corrected text is: %s\n", correctedText);

        String input = getInput("Would you like to write correction to a file?(Y/N)");

        if(input.toLowerCase().equals("y")){
            controller.writeToFile(getInput("Type in a file name"));
        }
    }

    public void handleNoSuggestions(String word){
        System.out.printf("There was no suggestions available for %s\n", word);
        System.out.println("Skipping correction....");
    }

    public void handleTextCorrection(String originalText, Corrections correction){
        while (true){
            System.out.printf("\nCurrent Text: %s\n", controller.getCurrentCorrectedText());
            System.out.printf("\nCorrection available for: %s\n", correction.getWord());
            this.printStringArrayHorizontal(correction.getSuggestions());
            System.out.println("\nPress 0 to skip this correction\n");

            try{
                int option = Integer.parseInt(input.nextLine());

                if (option == 0){
                    break;
                }

                if (option < 0 || option > correction.getSuggestions().size()){
                    System.out.println("Selected value was out or range");
                    continue;
                }
                controller.applyCorrection(correction.getWord(), correction.getSuggestions().get(option - 1));
                break;

            } catch(NumberFormatException e){
                System.out.println("Input was not a number");
            }
        }
    }

    private void handleText(){
        System.out.print("Please input your text: ");
        String text = input.nextLine();
        StringArray excludedWords = controller.getExcludedWordsFromString(text);

        if (excludedWords.size() > 0){
            System.out.println("\nWords not in dictionary from the string inputted\n");
            printStringArray(excludedWords);
            controller.handleCorrection(text, excludedWords);
        }else{
            System.out.println("\nAll words were in the dictionary\n");
        }
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
            case Exit:
                System.out.println("Exiting program");
                return false;
            default:
                System.out.println("Option selected was invalid, please try again");
                break;
        }
        return true;
    }

}
