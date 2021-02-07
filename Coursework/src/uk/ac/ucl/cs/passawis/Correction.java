package uk.ac.ucl.cs.passawis;

public class Correction {
    private String word;
    private StringArray suggestions;

    public Correction(String incorrectWord, StringArray suggestions){
        this.word = incorrectWord;
        this.suggestions = suggestions;
    }

    public String getOriginalWord(){
        return this.word;
    }

    public StringArray getSuggestions(){
        return this.suggestions;
    }
}
