package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Flood_fill_recursive {
    static int N;
    static int[][] Board = new int[100][100];
    static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                Board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int sRow = Integer.parseInt(st.nextToken());
        int sCol = Integer.parseInt(st.nextToken());

//        fill(sRow, sCol);
        dfs_fill(sRow, sCol, 3);

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(Board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void fill(int sRow, int sCol) {
        if(sRow < 0 || sCol > N-1 || sCol < 0 || sCol > N-1) return;

        if(Board[sRow][sCol] != 0) return;

        Board[sRow][sCol] = 1;

        fill(sRow-1, sCol);
        fill(sRow+1, sCol);
        fill(sRow, sCol-1);
        fill(sRow, sCol+1);

    }

    private static void dfs_fill(int sRow, int sCol, int color){
        boolean[][] visited = new boolean[N][N];
        Stack<Point> mystack = new Stack<>();
        mystack.push(new Point(sRow, sCol));

        while(!mystack.empty()){
            Point curr = mystack.pop();
            if(visited[curr.r][curr.c]) continue;

            visited[curr.r][curr.c] = true;
            Board[curr.r][curr.c] = color;

            for(int i=0; i<4; i++){
                int nRow = D[i][0] + curr.r;
                int nCol = D[i][1] + curr.c;
                if(nRow < 0 || nRow > N-1 || nCol < 0 || nCol > N-1) continue;
                if(visited[nRow][nCol]) continue;
                if(Board[nRow][nCol] != 0) continue;
                mystack.push(new Point(nRow, nCol));
            }
        }

    }
}
