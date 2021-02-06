public class SpellChecker {

    private String fixedString;
    private Dictionary dictionary;
    private final int tolerance;
    private Corrections[] corrections;

    public SpellChecker(Dictionary dictionary, int tolerance){
        this.dictionary = dictionary;
        this.tolerance = tolerance;
    }
    public Corrections findCorrection(String word){
        for(Corrections correction: corrections){
            if (correction.getIncorrectWord().equals(word)){
                return correction;
            }
        }

        return null;
    }

    public String getFixedString(){
        return this.fixedString;
    }

    public String applyChanges(String word, int option){

        Corrections correction = this.findCorrection(word);
        String selectedValue = correction.getSuggestions().get(option);
        String incorrectWord = correction.getIncorrectWord();

        String regex = String.format("\\b%s\\b", incorrectWord);
        this.fixedString = this.fixedString.replaceAll(regex, selectedValue);
        return this.fixedString;
    }

    public String fixedString(){
        return this.fixedString;
    }

    public void generateSuggestions(String originalString, StringArray excludedWords){
        this.fixedString = originalString;
        StringArray suggestions;
        Corrections[] corrections = new Corrections[excludedWords.size()];
        for (int i = 0; i < excludedWords.size(); i++) {
            String currentWord = excludedWords.get(i);
            suggestions = new StringArray();
            for (int j = 0; j < dictionary.size(); j++) {
                String currentDictionaryWord = dictionary.get(j);
                if(currentDictionaryWord.contains(currentWord.toLowerCase()) &&
                        currentDictionaryWord.length() < currentWord.length() + this.tolerance){
                    suggestions.add(currentDictionaryWord);
                }
            }
            corrections[i] = new Corrections(currentWord, suggestions);
        }
        this.corrections = corrections;
    }

    public Corrections[] getCorrections(){
        return this.corrections;
    }
}
