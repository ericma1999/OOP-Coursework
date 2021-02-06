public class SpellChecker {

    private String fixedString;
    private Dictionary dictionary;
    private final int tolerance;
    private Correction[] corrections;

    public SpellChecker(Dictionary dictionary, int tolerance){
        this.dictionary = dictionary;
        this.tolerance = tolerance;
    }
    public Correction findCorrection(String word){
        for(Correction correction: corrections){
            if (correction.getWord().equals(word)){
                return correction;
            }
        }

        return null;
    }

    public void setText(String text){
        this.fixedString = text;
    }

    public String getFixedString(){
        return this.fixedString;
    }

    public String applyChanges(String word, String correctedWord){

        String regex = String.format("\\b%s\\b", word);
        this.fixedString = this.fixedString.replaceAll(regex, correctedWord);
        return this.fixedString;
    }

    public String fixedString(){
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

    public Correction[] getCorrections(){
        return this.corrections;
    }
}
