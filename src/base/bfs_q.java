package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class bfs_q {
    static final int MAX_N = 10;
    static int N, E;
    static int[][] Graph = new int[MAX_N][MAX_N];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = stringArrayToIntegerArray(br.readLine().split(" "));
        N = tmp[0];
        E = tmp[1];
        tmp = stringArrayToIntegerArray(br.readLine().split(" "));
        for(int i=0; i<E; i++){
            int u = tmp[i*2];
            int v = tmp[i*2 + 1];
            Graph[u][v] = Graph[v][u] = 1;
        }
        bfs_queue(0);
    }

    private static void bfs_queue(int node) {
        boolean[] visited = new boolean[MAX_N];

        Queue<Integer> myqueue = new LinkedList<>();

        visited[node] = true;

        myqueue.add(node);

        while(!myqueue.isEmpty()){
            Integer curr = myqueue.remove();

            System.out.print(curr + " ");

            for(int next = 0; next < N; next++){
                if(!visited[next] && Graph[curr][next] != 0){
                    visited[next] = true;
                    myqueue.add(next);
                }
            }
        }
    }

    private static int[] stringArrayToIntegerArray(String[] strings){
        return Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
    }
}
