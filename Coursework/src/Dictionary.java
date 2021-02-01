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

    public StringArray findWordsInString(String input){

        StringArray output = new StringArray();

        for(String word: this.sanitiseString(input)){
            if(this.search(word)){
                output.add(word);
            }
        }
        return output;
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
