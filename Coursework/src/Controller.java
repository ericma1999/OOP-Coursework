public class Controller {

    private final Dictionary dictionary;
    private final Menu view;
    private final SpellChecker spellChecker;

    public Controller(Menu view, Dictionary dictionary, SpellChecker spellChecker){
        this.dictionary = dictionary;
        this.view = view;
        this.spellChecker = spellChecker;
        view.setController(this);
    }

    public void handleCorrection(String originalText, StringArray excludedWords){
        spellChecker.setText(originalText);

        for (int i = 0; i < excludedWords.size(); i++) {
            String currentWord = excludedWords.get(i);
            Corrections corrections = spellChecker.generateSuggestions(currentWord);
            StringArray suggestions = corrections.getSuggestions();
            if (suggestions.size() == 0){
                view.handleNoSuggestions(currentWord);
                continue;
            }else {
                view.handleTextCorrection(originalText, corrections);
            }
        }
        view.handleWriteFile(spellChecker.getFixedString());
    }

    public String applyCorrection(String word, String correctedWord){
        return spellChecker.applyChanges(word, correctedWord);
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

    public void writeToFile(String fileName){
        FileOutput out = new FileOutput(fileName + ".txt");
        out.writeString(spellChecker.getFixedString());
        out.close();
    }

    public String getCurrentCorrectedText(){
        return spellChecker.getFixedString();
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
