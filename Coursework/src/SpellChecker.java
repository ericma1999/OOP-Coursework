// gets a string and a dictionary and do correction on it
// generate suggestions

public class SpellChecker {

    private String fixedString;
    private Dictionary dictionary;
    private final int tolerance;

    public SpellChecker(Dictionary dictionary, int tolerance){
        this.dictionary = dictionary;
        this.tolerance = tolerance;
    }

    public void setText(String text){
        this.fixedString = text;
    }

    public String getFixedString(){
        return this.fixedString;
    }

    public String applyChanges(String word, String correctedWord){

        String regex = String.format("\\b(?i)%s\\b", word);
        this.fixedString = this.fixedString.replaceAll(regex, correctedWord);
        return this.fixedString;
    }

    public Correction generateSuggestions(String excludedWord){

        StringArray suggestions = new StringArray();

        for (int i = 0; i < dictionary.size(); i++) {
            String currentDictionaryWord = dictionary.get(i);

            if (currentDictionaryWord.contains(excludedWord.toLowerCase()) &&
                    currentDictionaryWord.length() < excludedWord.length() + this.tolerance){
                suggestions.add(currentDictionaryWord);
            }
        }
        return new Correction(excludedWord, suggestions);
    }

    public StringArray getExcludedWords(String input){
        return this.findWordsInString(input).getExcluded();
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
}
