import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'biggerIsGreater' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING w as parameter.
     */

    public static String biggerIsGreater(String w) {
        // Find the smallest greater diff char and their index.
        int smallestDiff = Integer.MIN_VALUE;
        int leftIndex = 0;
        int rightIndex = 0;

        char[] wArray = w.toCharArray();

        // Iterate the char from right to left.
        for (int r = wArray.length - 1; r > 0; r--) {
            // Search through the letters on our candidate's left side.
            for (int l = r - 1; l >= 0; l--) {
                int charDiff = (int)wArray[l] - (int)wArray[r];
                // Can't be the same letter(diff == 0).
                // Right char must be bigger than the left one.
                if (charDiff < 0) {
                    // Add index weight.
                    charDiff = charDiff + (l - wArray.length) * 26;
                    // Smallest diff in negative value
                    if (smallestDiff < charDiff) {
                        smallestDiff = charDiff;
                        leftIndex = l;
                        rightIndex = r;
                    }
                }
            }
        }

        // There is no letter that's bigger than any of the 
        // letters on its left side.
        if (smallestDiff ==  Integer.MIN_VALUE) {
            return "no answer";
        }

        // Do the smallest greater char swap
        char charBuffer = wArray[leftIndex];
        wArray[leftIndex] = wArray[rightIndex];
        wArray[rightIndex] = charBuffer;

        // From the left swap point, sort the rest of array
        Arrays.sort(wArray, leftIndex + 1, wArray.length);

        String result = new String(wArray);
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String w = bufferedReader.readLine();

                String result = Result.biggerIsGreater(w);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
