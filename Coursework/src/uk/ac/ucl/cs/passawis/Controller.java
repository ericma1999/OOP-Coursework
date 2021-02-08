package uk.ac.ucl.cs.passawis;

public class Controller {

    private final Dictionary dictionary;
    private final Menu view;
    private final SpellChecker spellChecker;
    /* Opensource dictionary from http://www.gwicks.net/dictionaries.htm UK English-65000 words */
    private String dictionarySrc = "words.txt";

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

    /* dictionary file will be in correct order for this program */
    private void readDictionarySrc(String source){
        FileInput file = new FileInput(source);
        while(file.hasNext()){
            this.dictionary.add(file.next().toLowerCase());
        }
        file.close();
    }

    public StringArray getExcludedWordsFromString(String text) {
        return spellChecker.getExcludedWords(text);
    }

    public StringArray getExcludedWordsFromFile(String fileName) {
        FileInput file = new FileInput(fileName);
        StringArray excluded = new StringArray();

        while (file.hasNextLine()){
            String line = file.nextLine();
            excluded.uniqueCombine(spellChecker.getExcludedWords(line));
        }
        return excluded;
    }

    public void handleCorrection(String originalText, StringArray excludedWords) {
        /* Check that there was at least one suggestion available */
        if (this.handleSingleLineCorrection(originalText, excludedWords)){
            view.handleWriteStringToFile(spellChecker.getFixedString());
        }
    }

    public void handleFileCorrection(String fileName) {

        FileInput file = new FileInput(fileName);
        StringArray correctedContent = new StringArray();
        boolean suggestionAvailable = false;

        while (file.hasNextLine()) {
            String currentText = file.nextLine();
            StringArray excludedWords = this.getExcludedWordsFromString(currentText);
            if (this.handleSingleLineCorrection(currentText, excludedWords)){
                suggestionAvailable = true;
            }
            correctedContent.add(spellChecker.getFixedString());
        }
        file.close();

        if (suggestionAvailable) {
            view.handleWriteContentToFile(correctedContent);
        }
    }

    /* Returns true if there is at least one suggestion that is not empty */
    private boolean handleSingleLineCorrection(String originalText, StringArray excludedWords) {
        spellChecker.setText(originalText);
        boolean suggestionAvailable =  false;

        for (int i = 0; i < excludedWords.size(); i++) {
            String currentWord = excludedWords.get(i);
            Correction correction = spellChecker.generateSuggestions(currentWord);
            StringArray suggestions = correction.getSuggestions();

            if (suggestions.size() == 0) {
                view.handleNoSuggestions(currentWord);
            } else {
                suggestionAvailable = true;
                view.handleTextCorrection(correction);
            }
        }

        return suggestionAvailable;
    }

    public void writeToFile(String fileName) {
        FileOutput out = new FileOutput(fileName);
        out.writeString(spellChecker.getFixedString());
        out.close();
    }

    public void writeContentToFile(String fileName, StringArray contents) {
        FileOutput output = new FileOutput(fileName);

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
}
