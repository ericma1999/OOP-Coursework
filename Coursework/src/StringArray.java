public class StringArray {

    private String[] stringArray;
    private int length = 0;

    public StringArray(){
        this.stringArray = new String[100];
    }

    public StringArray(StringArray a){

    }



    public int size(){
        return this.length;
    }

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
        if (this.length == this.stringArray.length){
            resizeContainerArray(this.stringArray.length + 100);
        }
    }

    public void insert(int index, String s){
        if (this.length == 0){
            this.add(s);
            return;
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