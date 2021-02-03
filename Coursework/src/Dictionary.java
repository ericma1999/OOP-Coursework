public class Dictionary extends StringArray{

    public Dictionary(String dictionarySource){
        super();
        this.readFileContents(dictionarySource);
    }

//    search can utilise binary search instead of normal loop since its in order
    public boolean search(String word){
        return this.contains(word);
    }

    //    assume file is in correct order of words.txt
    private void readFileContents(String source){
        FileInput file = new FileInput(source);
        while(file.hasNextLine()){
            this.add(file.nextLine());
        }
        file.close();
    }

}
