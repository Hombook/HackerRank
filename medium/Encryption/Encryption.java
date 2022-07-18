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
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String encryption(String s) {
        String sNoSpace = s.replaceAll("\\s", "");
        int stringLength = sNoSpace.length();
        // Calculate the size of row and column
        double sLengthSqrt = Math.sqrt(stringLength);
        int rows = (int)sLengthSqrt;
        int columns = (int)sLengthSqrt;
        if (rows * columns < stringLength) {
            columns++;
        }

        // Forming the string grid.
        List<String> stringGrid = new ArrayList<String>();
        int beginIndex = 0;
        int endIndex = columns;
        int rowsLeft = (int)Math.ceil(stringLength / rows);
        while (rowsLeft > 0) {
            String rowString = sNoSpace.substring(beginIndex, endIndex);
            stringGrid.add(rowString);

            beginIndex = endIndex;
            endIndex += columns;
            if (endIndex > stringLength) {
                endIndex = stringLength;
            }
            rowsLeft--;
        }

        String encodedString = "";
        for (int i = 0; i < columns; i++) {
            for (int c = 0; c < stringGrid.size(); c++) {
                try {
                    encodedString = encodedString + stringGrid.get(c).charAt(i);
                } catch (Exception e) {
                    // Ignore out of bound exception.
                }
            }
            encodedString += " ";
        }

        return encodedString;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
