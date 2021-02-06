public class Corrections {
    private String word;
    private StringArray suggestions;

    public Corrections(String incorrectWord, StringArray suggestions){
        this.word = incorrectWord;
        this.suggestions = suggestions;
    }

    public String getWord(){
        return this.word;
    }

    public StringArray getSuggestions(){
        return this.suggestions;
    }
}
