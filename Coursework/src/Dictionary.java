public class Dictionary extends StringArray{

    /* Variable to keep track of number of words with same starting alphabet */
    private final int[] alphabetCount = new int[26];

    /* search can utilise binary search instead of normal loop since its in alphabetical order */
    @Override
    public int indexOf(String s){
        return this.binarySearch(s);
    }

    @Override public void add(String word){
        super.add(word);
        this.alphabetCount[getAlphabetIndex(word)] += 1;
    }

    /*  get the offset of the alphabet*/
    private int getAlphabetIndex(String word){
        return word.charAt(0) - 'a';
    }

    /*  tally up the count for each of the word starting with same alphabet */
    public int getSearchStartPos(int alphabetIndex){
        if (alphabetIndex == 0){
            return 0;
        }else{
            return this.getSearchStartPos(alphabetIndex - 1) + this.alphabetCount[alphabetIndex - 1];
        }
    }

    public int getIndexEndForStartingChar(int alphabetIndex){
        return this.getSearchStartPos(alphabetIndex) + this.alphabetCount[alphabetIndex] - 1;
    }


    private int binarySearch(String word){
        if (this.isEmpty()){
            return -1;
        }

        /* find index section inside dictionary with same starting char to optimise search */
        int alphabetIndex = this.getAlphabetIndex(word);
        int start = getSearchStartPos(alphabetIndex);
        int end = this.getIndexEndForStartingChar(alphabetIndex);

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
}
