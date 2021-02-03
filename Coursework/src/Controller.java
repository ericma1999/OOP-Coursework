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
        return findWordsInFile(filename).getExcluded();
    }

    public StringArray getExcludedWordsFromString(String text){
        return findWordsInString(text).getExcluded();
    }

    public StringArray getIncludedWordsFromString(String text) {return findWordsInString(text).getIncluded();}

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

    private Tuple findWordsInFile(String fileName){
        FileInput file = new FileInput(fileName);
        StringArray included = new StringArray();
        StringArray excluded = new StringArray();

        while (file.hasNext()){
            String word = file.next();
            if (dictionary.search(word) && !included.contains(word)){
                included.add(word);
            }else if (!dictionary.search(word) && !excluded.contains(word)){
                excluded.add(word);
            }
        }
        return new Tuple(included, excluded);
    }

    private Tuple findWordsInString(String input){
        StringArray included = new StringArray();
        StringArray excluded = new StringArray();

        for(String word: this.sanitiseString(input)){
            if(dictionary.search(word) && !included.contains(word)){
                included.add(word);
            }else if (!dictionary.search(word) && !excluded.contains(word)){
                excluded.add(word);
            }
        }
        return new Tuple(included, excluded);
    }


    private String[] sanitiseString(String input){
        return input.replaceAll("\\W", " ").split("\\s+");
    }









}
