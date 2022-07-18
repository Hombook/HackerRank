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
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Create a deduped version of ranked scores.
        List<Integer> rankedScores = new ArrayList<Integer>();
        int currentScore = -1;
        for (int score : ranked) {
            if (currentScore != score) {
                // Put it in ascending order.
                rankedScores.add(0, score);
                currentScore = score;
            }
        }
        int totalRank = rankedScores.size();

        List<Integer> playerRanks = new ArrayList<Integer>();
        for (int pi =  0; pi < player.size(); pi++) {
            int playerScore = player.get(pi);
            if (playerScore < rankedScores.get(0)) { // Worse than the last 1
                playerRanks.add(totalRank+1);
            } else if (playerScore > rankedScores.get(totalRank - 1)) { // Better than the top 1
                playerRanks.add(1);
            } else {
                int playerRank = Collections.binarySearch(rankedScores, playerScore);
                if (playerRank < 0) {
                    // Add 1 to get the position.(when an insert happens, it's "-index - 1")
                    // Add 1 more to get the rank number. (Rank = index + 1)
                    playerRank = playerRank + totalRank + 2;
                } else {
                    playerRank = totalRank - playerRank;
                }
                playerRanks.add(playerRank);
            }
        }
        return playerRanks;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
