// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.*;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {

        /*// normal case, freq are diff
        int partitions = maxFreq - 1;
        int emptySpots = n * partiions;
        int remainingTasks = tasks.length - maxFreq;
        int idleSPots = emptySpots - remainingTasks;

        return tasks.length + idleSPots;
        */

        /*// special case, freq are same for any two
        int partitions = maxFreq - 1;
        int emptySpots = (n - (maxCount - 1)) * partiions;
        int remainingTasks = tasks.length - (maxFreq * maxCount);
        int idleSPots = max(0, emptySpots - remainingTasks);

        return tasks.length + idleSPots;
        */

        int maxFreq = 0;
        int maxCount = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < tasks.length; i++){
            char c = tasks[i];
            map.put(c, map.getOrDefault(c,0) + 1);
            maxFreq = Math.max(maxFreq, map.get(c));
        }

        for(int val : map.values()){
            if(val == maxFreq){
                maxCount++;
            }
        }

        int partitions = maxFreq - 1;
        int emptySpots = (n - (maxCount - 1)) * partitions;
        int remainingTasks = tasks.length - (maxCount * maxFreq);
        int idleSpots = Math.max(0, emptySpots - remainingTasks);

        return tasks.length + idleSpots;

    }

    // Time Complexity : O(n)
    // Space Complexity : O(n)
    // Did this code successfully run on Leetcode : Yes
    // Any problem you faced while coding this : No

    public int leastIntervalUsingPQ(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : tasks){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                return b - a;
            }
        });

        pq.addAll(map.values());

        int cycles = 0;

        while(!pq.isEmpty()){
            List<Integer> list = new ArrayList<>();
            for(int i=0; i < n+1; i++){
                if(!pq.isEmpty()){
                    list.add(pq.remove());
                }
            }

            for(int i : list){
                i--;
                if(i > 0){
                    pq.add(i);
                }
            }

            cycles += pq.isEmpty() ? list.size() : n + 1;
        }

        return cycles;
    }
}
