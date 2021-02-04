public class Corrections {
    private String incorrectWord;
    private StringArray suggestions;

    public Corrections(String incorrectWord, StringArray suggestions){
        this.incorrectWord = incorrectWord;
        this.suggestions = suggestions;
    }

    public String getIncorrectWord(){
        return this.incorrectWord;
    }

    public StringArray getSuggestions(){
        return this.suggestions;
    }
}
