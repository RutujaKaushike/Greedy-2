// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.Arrays;

public class Candy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] result = new int[n];

        Arrays.fill(result, 1);

        // Left pass
        for(int i = 1; i < n; i++){
            if(ratings[i] > ratings[i-1]){
                result[i] = result[i-1] + 1;
            }
        }

        // Right pass
        for(int i = n-2; i >=0; i--){
            if(ratings[i] > ratings[i+1] && result[i] <= result[i+1]){
                result[i] = result[i+1] + 1;
            }
        }

        int output = 0;
        for(int i : result){
            output += i;
        }

        return output;
    }
}
