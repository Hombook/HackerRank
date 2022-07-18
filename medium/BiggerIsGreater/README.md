https://www.hackerrank.com/challenges/bigger-is-greater/problem

1. char type has integer value.
2. The leftmost side of the string has the highest weight.
    - Since the letter at 'i' will always a full char set higher than the one at 'i+1', we can add 26 as their weight differences.
    - e.g. "az" will have the letter 'a' being larger than the next letter 'z' due to the positioning, although 'z' is actually bigger than 'a' if they were in the same position.
3. Find a letter that's slightly bigger than the one at its left side in any position.
4. Due to the unlimited letter swapping:
    - We need to search through the whole string array and find out which letter pair has the lowest value difference.
    - The position of the letter at left side of the swapping will add its weight to the total of value difference.
5. Do the letter swap first.
6. Start from the left swapping point, sort the rest of string array in ascending order to ensure that it gets the minimum possible value.
    - The reason we can do this is also due to the unlimited swapping.

STEP 1: **dkhc**</br>
Swap 'd' with 'h'
* 'd' can be swapped with 'k' and 'h' to increase the string value.
* (h - d) < (k - d) so 'h' is the winner.</br>

STEP 2: **hkdc**</br>
Sort the letters after 'h'
* 'h' is the left side letter's position when the swap happens.</br>

RESULT: **hcdk**
