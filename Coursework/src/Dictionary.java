public class Dictionary extends StringArray{

    public Dictionary(String dictionarySource){
        super();
        this.readFileContents(dictionarySource);
    }

//    search can utilise binary search instead of normal loop since its in order
    public boolean search(String word){
        return this.binarySearch(word) != -1;
    }

    private int binarySearch(String word){
        if (this.isEmpty()){
            return -1;
        }

        int start = 0;
        int end = this.size();

        while (start <= end){
            int mid = (start + end ) / 2;

            String midValue = this.get(mid);

            if (midValue.equals(word)){
                return mid;
            }

            if (midValue.compareTo(word) < 0){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return -1;

    }

    //    assume file is in correct order of words.txt
    private void readFileContents(String source){
        FileInput file = new FileInput(source);
        while(file.hasNextLine()){
            this.add(file.nextLine().toLowerCase());
        }
        file.close();

        this.print();
    }

}
