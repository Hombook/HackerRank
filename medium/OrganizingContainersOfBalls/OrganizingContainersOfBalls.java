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
     * Complete the 'organizingContainers' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts 2D_INTEGER_ARRAY container as parameter.
     */

    public static String organizingContainers(List<List<Integer>> container) {
        int containerCount = container.get(0).size();
        int typeCount = container.size();
        
        List<Long> containerSizes = new ArrayList<Long>();
        List<Long> ballCounts = new ArrayList<Long>();

        for (int r=0; r < typeCount; r++) {
            Long ballCount = 0l;
            Long containerSize = 0l;
            for (int c=0; c < containerCount; c++ ) {
                ballCount += container.get(c).get(r);
                containerSize += container.get(r).get(c);
            }
            ballCounts.add(ballCount);
            containerSizes.add(containerSize);
        }
        Collections.sort(ballCounts);
        Collections.sort(containerSizes);
        
        System.out.println("======ballCounts: "+ ballCounts);
        System.out.println("======containerSizes: "+ containerSizes);
        
        for (int v=0; v < ballCounts.size(); v++) {
            if (!ballCounts.get(v).equals(containerSizes.get(v))) {
                return "Impossible";
            }
        }
        
        return "Possible";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> container = new ArrayList<>();

                IntStream.range(0, n).forEach(i -> {
                    try {
                        container.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                String result = Result.organizingContainers(container);

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
