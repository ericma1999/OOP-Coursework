package uk.ac.ucl.cs.passawis;

/* Learnt from https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance */
/* Reference video used: https://youtu.be/9QgTUMJ1cpQ?t=247 */
public class JaroWinkler {

    private static int samePrefix(String input, String correctWord){
        int matches = 0;
        for (int i = 0; i < input.length() && i < correctWord.length(); i++) {
            if (input.charAt(i) == correctWord.charAt(i)) {
                matches += 1;
            }else{
                break;
            }
        }
        return matches;
    }

    private static int getStartIndex(int currentIndex){
        for (int i = 2; i >= 0; i--) {
            int startIndex = currentIndex - i;
            if (startIndex >= 0){
                return startIndex;
            }
        }
        return currentIndex;
    }

    private static int getEndIndex(int currentIndex, int length){
        for (int i = 2; i >= 0; i--) {
            int endIndex = currentIndex + i;
            if (endIndex <= length){
                return endIndex;
            }
        }
        return currentIndex;
    }

    public static double calculateJaro(int matches, double transpositions, int firstLength, int secondLength){
        double transposition = transpositions / 2;

        return .3333 * ((matches / firstLength) + (matches / secondLength) + ((matches - transposition) / matches));
    }

    public static double calculateJaroWinkler(String input, String dictionaryWord){
        int matches = 0;
        int transpositions = 0;

        for (int i = 0; i < input.length() && i < dictionaryWord.length(); i++) {
            int start = getStartIndex(i);
            int end = getEndIndex(i, dictionaryWord.length());

            String splicedString = dictionaryWord.substring(start,end);

            int splicedResult = splicedString.indexOf(input.charAt(i));

            if (splicedResult != -1){
                matches += 1;

                if (splicedResult != i){
                    transpositions += 1;
                }
            }
        }
        double jaro = calculateJaro(matches, transpositions, input.length(), dictionaryWord.length());

        return jaro + ((samePrefix(input, dictionaryWord) * 0.15) * (1 - jaro));
    }

}
