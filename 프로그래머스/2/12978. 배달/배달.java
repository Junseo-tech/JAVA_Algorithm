import java.util.*;
// K시간 이하로 음식 주문을 받을 수 있는 마을의 개수 return

class Node implements Comparable<Node> {
    int to;
    int weight;
    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
    
    public int compareTo(Node n) {
        return this.weight - n.weight;
    }
}

class Solution {
    static PriorityQueue<Node> pq;
    static List<List<Node>> graph;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        pq = new PriorityQueue<>();
        graph = new ArrayList<>();
        
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] r : road) {
            int start = r[0];
            int end = r[1];
            int weight = r[2];
            graph.get(start).add(new Node(end, weight));
            graph.get(end).add(new Node(start, weight));
        }
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        pq.add(new Node(1, 0));
        
        while(!pq.isEmpty()) {
            Node n = pq.poll();
            int currentNode = n.to;
            int currentWeight = n.weight;
            
            for(Node next : graph.get(currentNode)) {
                int cost = currentWeight + next.weight;
                
                if(cost < dist[next.to]) {
                    dist[next.to] = cost;
                    pq.add(new Node(next.to, cost));
                }
            }
        }
        
        for(int i = 1; i <= N; i++) {
            if(dist[i] <= K) answer++;
        }
        
        return answer;
    }
}