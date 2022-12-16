package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class bfs_shortest {
    static final int MAX_N = 10;
    static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N;
    static int[][] Board = new int[MAX_N][MAX_N];
    static class Point{
        int r, c, d;
        Point(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            Board[i] = stringToIntArray(br.readLine());
        }
        int[] tmp = stringToIntArray(br.readLine());
        int sRow, sCol, eRow, eCol;
        sRow = tmp[0];
        sCol = tmp[1];
        eRow = tmp[2];
        eCol = tmp[3];
        int ret = bfs_start_end(sRow, sCol, eRow, eCol);
        System.out.println("ret = " + ret);
    }

    private static int bfs_start_end(int sRow, int sCol, int eRow, int eCol) {
        boolean[][] visited = new boolean[MAX_N][MAX_N];
        Queue<Point> myqueue = new LinkedList<>();
        visited[sRow][sCol] = true;
        myqueue.add(new Point(sRow, sCol, 0));

        while(!myqueue.isEmpty()){
            Point curr = myqueue.remove();
            if(curr.r == eRow && curr.c == eCol) return curr.d;

            for(int i=0; i<4; i++){
                int nRow = curr.r + D[i][0];
                int nCol = curr.c + D[i][1];
                if (nRow < 0 || nRow > N - 1 || nCol < 0 || nCol > N - 1) {
                    continue;
                }
                if(visited[nRow][nCol]) continue;
                if(Board[nRow][nCol] == 1) continue;
                visited[nRow][nCol] = true;
                myqueue.add(new Point(nRow, nCol, curr.d + 1));
            }
        }
        return -1;
    }

    private static int[] stringToIntArray(String line){
        return Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}
