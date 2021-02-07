public class StringArray {

    private String[] stringArray;
    private int length = 0;
    private final int initialSize = 100;
    private final double growthRate = 1.5;

    public StringArray(){
        this.stringArray = new String[initialSize];
    }

    public StringArray(StringArray a){

        int inputSize = a.size();
        int increment = ((inputSize + 99) / 100 ) * 100 - inputSize;
        String[] newArray = new String[inputSize + increment];

        this.length = inputSize;
        for (int i = 0; i < inputSize; i++) {
            newArray[i] = a.get(i);
        }

        this.stringArray = newArray;
    }

    public int size() {
        return this.length;
    }

    public boolean isEmpty(){
        return this.length == 0;
    }

    public String get(int index){
        if (index < 0 || index > this.length) {
            return null;
        }
        return this.stringArray[index];
    }

    public void set(int index, String s){
        if (index >= 0 && index < this.length){
            this.stringArray[index] = s;
        }
    }

    public void add(String s){
        this.stringArray[this.length] = s;
        this.length += 1;

        performResizeIfNeeded();
    }

    public void insert(int index, String s){
        if (this.length == 0 && index == 0){
            this.add(s);
            return;
        }

        if (index >= 0 && index < this.length){

            System.arraycopy(this.stringArray, index, this.stringArray, index+1, this.length - index);
            this.stringArray[index] = s;
            this.length += 1;

            performResizeIfNeeded();
        }
    }

    public void remove(int index){
        if (index < 0 || index > this.length) return;

        if (index == this.length - 1){
            this.stringArray = new String[initialSize];
            this.length = 0;
        }else{
            System.arraycopy(this.stringArray, index + 1, this.stringArray, index, this.length - 1);
            this.length -= 1;
        }

        performResizeIfNeeded();

    }

    public boolean contains(String s){
        return this.indexOf(s) != -1;
    }

    public boolean containsMatchingCase(String s){
        return this.indexOfMatchingCase(s) != -1;
    }

    public int indexOf(String s){
        return this.search(s, false);
    }

    public int indexOfMatchingCase(String s){
        return this.search(s, true);
    }

    private int search(String s, boolean matchingCase){
        String input;
        boolean isNull = false;

        if (s == null){
            input = s;
            isNull = true;
        }else{
            input = matchingCase ? s : s.toLowerCase();
        }
        for (int i = 0; i < this.length; i++) {

            if (isNull) {
                if (this.stringArray[i] == input){
                    return i;
                }
                continue;
            }
            String currentValue = matchingCase ? this.stringArray[i] : this.stringArray[i].toLowerCase();
            if (input.equals(currentValue)) return i;
        }
        return -1;
    }

    private void performResizeIfNeeded(){
        if(this.stringArray.length > initialSize && this.length <= this.stringArray.length / growthRate){
            resizeContainerArray((int) (this.stringArray.length / growthRate));
        }else if (this.length == this.stringArray.length){
            resizeContainerArray((int) (this.stringArray.length * growthRate));
        }
    }

    private void resizeContainerArray(int newSize){
        String[] newArray = new String[newSize];
        System.arraycopy(this.stringArray, 0, newArray, 0, this.length);
        this.stringArray = newArray;
    }

    public void uniqueCombine(StringArray newWords){
        if (newWords.size() == 0){
            return;
        }

        for (int i = 0; i < newWords.size(); i++) {
            String currentWord = newWords.get(i);
            if (!this.contains(currentWord)){
                this.add(currentWord);
            }
        }
    }

    public void print(){
        for (int i = 0; i < this.length; i++) {
            System.out.println(this.stringArray[i]);
        }
    }
//
    public void actualSize(){
        System.out.println(this.stringArray.length);
    }
}
