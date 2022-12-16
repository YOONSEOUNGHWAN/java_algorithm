package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class dfs_recursive {
    static final int Max_N = 10;
    static int N, E;
    static int[][] Graph = new int[Max_N][Max_N];
    static boolean[] Visited = new boolean[Max_N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<E; i++){
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Graph[u][v] = Graph[v][u] = 1;
        }
        dfs_r(0);
        dfs_s(0);
    }

    private static void dfs_r(int node) {
        Visited[node] = true;
        System.out.print(node + " ");

        for(int next = 0; next < N; next++){
            if(!Visited[next] && Graph[node][next] != 0){
                dfs_r(next);
            }
        }
    }

    private static void dfs_s(int node) {
        boolean[] visited = new boolean[Max_N];

        Stack<Integer> mystack = new Stack<>();
        mystack.push(node);

        while(!mystack.empty()){
            int curr = mystack.pop();

            if(visited[curr]) continue;

            visited[curr] = true;
            System.out.print(curr + " ");

            for(int next = 0; next<N; next++){
                if(!visited[next] && Graph[curr][next] != 0){
                    mystack.push(next);
                }
            }
        }
    }

}
