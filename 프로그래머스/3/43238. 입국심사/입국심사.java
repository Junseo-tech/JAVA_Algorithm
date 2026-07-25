// return : 모든 사람이 심사를 받는데 걸리는 시간의 최솟값
import java.util.*;

class Solution {
    static long answer;
    public long solution(int n, int[] times) {
        answer = Long.MAX_VALUE;
        
        Arrays.sort(times);
        
        binarySearch(times[0], (long) times[times.length - 1] * n , times, n);
        
        return answer;
    }
    
    private void binarySearch(long start, long end, int[] times, int n) {
        while(start <= end) {
            long mid = start + (end - start) / 2; // 걸리는 시간
            long count = 0;
            for(int i = 0; i < times.length; i++) {
                count += mid / times[i]; 
            }
            
            if(count >= n) {
                end = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                start = mid + 1;
            }
        }
    }
}