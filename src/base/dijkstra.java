package base;

import java.util.*;
import java.io.*;

public class dijkstra {
    static final int INF = 123456789;
    static final int MAX_N = 10;
    static int N, E;
    static int[][] Graph = new int[MAX_N][MAX_N];
    static int[] Dist = new int[MAX_N];

    static int[] Prev = new int[MAX_N];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = stringToIntArray(br.readLine());
        N = tmp[0];
        E = tmp[1];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i==j) Graph[i][j] = 0;
                else Graph[i][j] = INF;
            }
        }

        for(int i=0; i<E; i++){
            tmp = stringToIntArray(br.readLine());
            int start = tmp[0];
            int end = tmp[1];
            int cost = tmp[2];
            Graph[start][end] = Graph[end][start] = cost;
        }

        int solve = solve(0, 4);
        System.out.println(solve);
        for(int i=0; i<N; i++){
            System.out.print(Dist[i] + " ");
        }
        System.out.println();
        int curr = 5;
        while(curr != -1){
            System.out.print(curr + " < ");
            curr = Prev[curr];
        }
    }

    private static int solve(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        boolean[] visited = new boolean[MAX_N];
        for(int i=0; i<N; i++){
            Dist[i] = INF;
            Prev[i] = -1;
        }
        Dist[start] = 0;

        pq.add(new int[] {0, start});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int node = curr[1];
            if(node == end) return curr[0];

            if(visited[node]) continue;
            visited[node] = true;

            for(int next = 0; next < N; next++){
                if(Dist[next] > Dist[node] + Graph[node][next]){
                    Dist[next] = Dist[node] + Graph[node][next];
                    Prev[next] = node;
                    pq.add(new int[]{Dist[next], next});
                }
            }
//            for(int next = 0; next < N; next++){
//                if(Graph[node][next] != INF || (node != next)){
//                    Dist[next] = Math.min(Dist[next], Dist[node] + Graph[node][next]);
//                    Prev[next] = node;
//                    pq.add(new int[]{Dist[next], next});
//                }
//            }
        }
        return INF;
    }

    private static int[] stringToIntArray(String readLine) {
        return Arrays.stream(readLine.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}