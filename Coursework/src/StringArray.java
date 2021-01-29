public class StringArray {

    private String[] stringArray;
    private int length = 0;
    private final int increment = 20;

    public StringArray(){
        this.stringArray = new String[increment];
    }

    public StringArray(StringArray a){

        int inputSize = a.size();
        String[] newArray = new String[inputSize + increment];

        this.length = inputSize;
        for (int i = 0; i < inputSize; i++) {
            newArray[i] = a.get(i);
        }

        this.stringArray = newArray;
    }

    public int size() {return this.length;}

    public boolean isEmpty(){
        return this.length == 0;
    }

    public String get(int index){
        if (index < 0 || index > this.length){
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
        if (this.length == 0 || index == this.length - 1){
            this.add(s);
            return;
        }

//        either insert at index 0 or somewhere in the middle
        if (index >= 0 && index < this.length){
            if (index == 0){
                System.arraycopy(this.stringArray, 0, this.stringArray, 1, this.length);
                this.stringArray[0] = s;
                this.length += 1;
            }else{
                String[] newStringArray = new String[this.stringArray.length + 1];
                System.arraycopy(this.stringArray, 0, newStringArray, 0, index);
                System.arraycopy(this.stringArray, index, newStringArray, index+1, this.length - index);
                newStringArray[index] = s;
                this.length += 1;
                this.stringArray = newStringArray;
            }

            performResizeIfNeeded();

        }
    }

    public void remove(int index){
        if (index < 0 || index > this.length) return;

        if (index == this.length - 1){
            this.stringArray = new String[increment];
            this.length = 0;
        }else if (index == 0){
            String[] newArray = new String [this.length - 1];
            System.arraycopy(this.stringArray, 1, newArray, 0, this.length - 1);
            this.length -= 1;
            this.stringArray = newArray;
        }
        else {
            String[] newArray = new String [this.length - 1];
            System.arraycopy(this.stringArray, 0, newArray, 0, index);
            System.arraycopy(this.stringArray, index + 1, newArray, index, this.length - (index + 1));
            this.length -= 1;
            this.stringArray = newArray;
        }

    }

    public boolean contains(String s){
        return this.search(s, false) != -1;

    }

    public boolean containsMatchingCase(String s){
        return this.search(s, true) != -1;
    }

    public int indexOf(String s){
        return this.search(s, false);
    }

    public int indexOfMatchingCase(String s){
        return this.search(s, true);
    }

    private int search(String s, boolean matchingCase){

        String input = matchingCase ? s : s.toLowerCase();

        for (int i = 0; i < this.length; i++) {
            String currentValue = matchingCase ? this.stringArray[i] : this.stringArray[i].toLowerCase();
            if (input.equals(currentValue)) return i;
        }
        return -1;
    }

    private void performResizeIfNeeded(){
        if (this.length == this.stringArray.length){
            resizeContainerArray(this.stringArray.length + increment);
        }
    }


    private void cloneIntoNewArray(String[] newArray){
        for (int i = 0; i < this.length; i++) {
            newArray[i] = this.stringArray[i];
        }
    }
    private void resizeContainerArray(int newSize){
//        increase the size of the array
        String[] newArray = new String[newSize];
        cloneIntoNewArray(newArray);
        this.stringArray = newArray;
    }


    public void print(){
        for (int i = 0; i < this.length; i++) {
            System.out.println(this.stringArray[i]);
        }
    }

    public void actualSize(){
        System.out.println(this.stringArray.length);
    }


}
