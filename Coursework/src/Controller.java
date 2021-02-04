public class Controller {

    private Dictionary dictionary;
    private Menu view;
    private Corrector corrector;

    public Controller(Menu view, Dictionary dictionary, Corrector corrector){
        this.dictionary = dictionary;
        this.view = view;
        this.corrector = corrector;
        view.setController(this);
    }

    public Corrections[] generatePossibleCorrection(String originalString, StringArray excludedWords){
        corrector.generateSuggestions(originalString, excludedWords);
        Corrections[] suggestions = corrector.getCorrections();
        return suggestions;
    }

    public String applyCorrection(String word, int option){
        return corrector.applyChanges(word, option);
    }

    public StringArray getExcludedWordsFromFile(String filename){
        return findWordsInFile(filename).getExcluded();
    }

    public StringArray getExcludedWordsFromString(String text){
        return findWordsInString(text).getExcluded();
    }

    public void start(){
        view.initialiseMenu();
    }

    private Tuple findWordsInFile(String fileName){
        FileInput file = new FileInput(fileName);
        StringArray included = new StringArray();
        StringArray excluded = new StringArray();

        while (file.hasNext()){
            String word = file.next();
            if (dictionary.contains(word) && !included.contains(word)){
                included.add(word);
            }else if (!dictionary.contains(word) && !excluded.contains(word)){
                excluded.add(word);
            }
        }
        return new Tuple(included, excluded);
    }

    private Tuple findWordsInString(String input){
        StringArray included = new StringArray();
        StringArray excluded = new StringArray();

        for(String word: this.sanitiseString(input)){
            if(dictionary.contains(word) && !included.contains(word)){
                included.add(word);
            }else if (!dictionary.contains(word) && !excluded.contains(word)){
                excluded.add(word);
            }
        }
        return new Tuple(included, excluded);
    }


    private String[] sanitiseString(String input){
        return input.replaceAll("\\W", " ").toLowerCase().split("\\s+");
    }

}
