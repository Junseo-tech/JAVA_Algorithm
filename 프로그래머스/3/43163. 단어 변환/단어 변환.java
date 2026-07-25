import java.util.*;

class Solution {
    static int answer;
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        
        if(!Arrays.asList(words).contains(target)) {
            return 0;
        }
        
        dfs(target, words, begin, 0, new boolean[words.length]);
        
        return answer;
    }
    
    
    private void dfs(String target, String[] words, String temp, int num, boolean[] visited) {
        if(temp.equals(target)) {
            answer = Math.min(answer, num);
            return;
        }
        
        for(int i = 0; i < words.length; i++) {
            int count = 0;
            if(!visited[i]) {
                for(int j = 0; j < temp.length(); j++) {
                    if(temp.charAt(j) != words[i].charAt(j)) {
                        count++;
                    }
                }   
            }
            if(count == 1) {
                visited[i] = true;
                dfs(target, words, words[i], num + 1, visited);
                visited[i] = false;
            }
        }
    }
}