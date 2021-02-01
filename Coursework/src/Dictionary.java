public class Dictionary {

    private StringArray words;

    public Dictionary(String source){
        this.words = new StringArray();
        this.readFileContents(source);
    }

//    assume file is in correct order of words.txt

    private boolean readFileContents(String source){
        FileInput file = new FileInput(source);

        while(file.hasNextLine()){
            this.words.add(file.nextLine());
        }
        file.close();

        return true;
    }

}
