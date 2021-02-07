public class Controller {

    private final Dictionary dictionary;
    private final Menu view;
    private final SpellChecker spellChecker;
    private String dictionarySrc = "words";

    public Controller(Menu view) {
        this.dictionary = new Dictionary();
        this.view = view;
        this.spellChecker = new SpellChecker(this.dictionary, 4);
        view.setController(this);
    }

    public void start() {
        this.readDictionarySrc(this.dictionarySrc);
        view.initialiseMenu();
    }

    public StringArray getExcludedWordsFromString(String text) {
        return findWordsInString(text).getExcluded();
    }

    private Tuple findWordsInString(String input) {
        StringArray included = new StringArray();
        StringArray excluded = new StringArray();

        for (String word : this.sanitiseString(input)) {
            if (dictionary.contains(word) && !included.contains(word)) {
                included.add(word);
            } else if (!dictionary.contains(word) && !excluded.contains(word)) {
                excluded.add(word);
            }
        }
        return new Tuple(included, excluded);
    }

    private String[] sanitiseString(String input) {
        return input.replaceAll("\\W", " ").toLowerCase().split("\\s+");
    }

    public StringArray getExcludedWordsFromFile(String filename) {
        return findWordsInFile(filename).getExcluded();
    }

    private Tuple findWordsInFile(String fileName) {
        FileInput file = new FileInput(fileName);
        StringArray included = new StringArray();
        StringArray excluded = new StringArray();

        while (file.hasNext()) {
            String word = file.next();
            if (dictionary.contains(word) && !included.contains(word)) {
                included.add(word);
            } else if (!dictionary.contains(word) && !excluded.contains(word)) {
                excluded.add(word);
            }
        }
        file.close();
        return new Tuple(included, excluded);
    }

    public void handleCorrection(String originalText, StringArray excludedWords) {
        this.handleSingleLineCorrection(originalText, excludedWords);
        view.handleWriteStringToFile(spellChecker.getFixedString());
    }

    public void handleFileCorrection(String fileName) {

        FileInput file = new FileInput(fileName);
        StringArray correctedText = new StringArray();

        while (file.hasNextLine()) {
            String currentText = file.nextLine();
            StringArray excludedWords = this.getExcludedWordsFromString(currentText);
            this.handleSingleLineCorrection(currentText, excludedWords);
            correctedText.add(spellChecker.getFixedString());
        }

        file.close();
        view.handleWriteContentToFile(correctedText);
    }

    private void handleSingleLineCorrection(String originalText, StringArray excludedWords) {
        spellChecker.setText(originalText);

        for (int i = 0; i < excludedWords.size(); i++) {
            String currentWord = excludedWords.get(i);
            Correction correction = spellChecker.generateSuggestions(currentWord);
            StringArray suggestions = correction.getSuggestions();
            if (suggestions.size() == 0) {
                view.handleNoSuggestions(currentWord);
            } else {
                view.handleTextCorrection(originalText, correction);
            }
        }

    }

    public void writeToFile(String fileName) {
        FileOutput out = new FileOutput(fileName + ".txt");
        out.writeString(spellChecker.getFixedString());
        out.close();
    }

    public void writeContentToFile(String fileName, StringArray contents) {
        FileOutput output = new FileOutput(fileName + ".txt");

        for (int i = 0; i < contents.size(); i++) {
            output.writeString(contents.get(i));
            output.writeEndOfLine();
        }

        output.close();
    }

    public String applyCorrection(String word, String correctedWord) {
        return spellChecker.applyChanges(word, correctedWord);
    }

    public String getCurrentCorrectedText() {
        return spellChecker.getFixedString();
    }

    /* dictionary file will be in correct order for this program */
    private void readDictionarySrc(String source){
        FileInput file = new FileInput(source);
        while(file.hasNextLine()){
            this.dictionary.add(file.nextLine().toLowerCase());
        }
        file.close();
    }
}
