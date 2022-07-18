https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem

1. Dedupe the ranked array first, as those with the same score will have the same rank and the next rank will not effected by the duplicated scores.
2. Since player's score is in ascending order and will be dealt from low to high, the rank shifting will be a non-factor here, as it only effects the score that's lower than the one that just got applied.
3. 2 extreme cases can be dealt without going to the array search step:
    - Current score is better than the top 1.
    - Current score is worse than the last 1.
4. Use binary search to find out the correct position for the current score.