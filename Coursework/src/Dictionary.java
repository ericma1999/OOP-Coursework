public class Dictionary {

    private StringArray words;

    public Dictionary(String source){
        this.words = new StringArray();
        this.readFileContents(source);
    }

//    assume file is in correct order of words.txt

    public boolean search(String word){
        return this.words.contains(word);
    }

    public StringArray getIncludedWordsFromString(String input){
        return this.findWordsInString(input).getIncluded();
    }

    public StringArray getExcludedWordsFromString(String input){
        return this.findWordsInString(input).getExcluded();
    }

    public Tuple findWordsInString(String input){
        StringArray included = new StringArray();
        StringArray excluded = new StringArray();

        for(String word: this.sanitiseString(input)){
            if(this.search(word)){
                included.add(word);
            }else{
                excluded.add(word);
            }

        }
        return new Tuple(included, excluded);
    }

    private String[] sanitiseString(String input){
        return input.replaceAll("\\W", " ").split("\\s+");
    }

    private boolean readFileContents(String source){
        FileInput file = new FileInput(source);

        while(file.hasNextLine()){
            this.words.add(file.nextLine());
        }
        file.close();

        return true;
    }

}
