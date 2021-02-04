public class Corrector {

    private String originalString;
    private String alteredString;
    private Dictionary dictionary;
    private final int tolerance;

    public Corrector(Dictionary dictionary, int tolerance){
        this.dictionary = dictionary;
        this.tolerance = tolerance;
    }

    public StringArray[] generateSuggestions(StringArray excludedWords){
        StringArray[] suggestions = new StringArray[excludedWords.size()];
        for (int i = 0; i < excludedWords.size(); i++) {
            String currentWord = excludedWords.get(i);
            suggestions[i] = new StringArray();

            for (int j = 0; j < dictionary.size(); j++) {
                String currentDictionaryWord = dictionary.get(j);
                if(currentDictionaryWord.contains(currentWord.toLowerCase()) &&
                        currentDictionaryWord.length() < currentWord.length() + this.tolerance){
                    suggestions[i].add(currentDictionaryWord);
                }
            }
        }
        return suggestions;
    }



}
