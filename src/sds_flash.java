import java.util.*;
import java.io.*;

public class sds_flash{
    static final int MAX_N = 100;

    static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[][] Graph = new int[MAX_N][MAX_N];
    static int Row, Col, HitCount, T;

    static int ret = 123456789;

    static class Point{
        int x, y, d;
        Point(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int k=0; k<T; k++){
            int[] tmp = stringToIntArray(br.readLine());
            Row = tmp[0];
            Col = tmp[1];
            HitCount = tmp[2];
            for(int i=0; i<Row; i++){
                String line = br.readLine();
                for(int j=0; j<line.length(); j++){
                    if(line.charAt(j) == '#'){
                        Graph[i][j] = 1; // 벽
                    }
                }
            }
            for(int i=0; i<HitCount; i++){
                tmp = stringToIntArray(br.readLine());
                Graph[tmp[0]-1][tmp[1]-1] = 2; // 불
            }
            sb = sb.append("#" + (k + 1) + " ").append(solve()).append('\n');
            clearGraph();
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);

    }

    private static void clearGraph() {
        for(int i=0; i<Row; i++){
            for(int j=0; j<Col; j++){
                Graph[i][j] = 0;
            }
        }
        ret = 123456789;
    }

    private static int solve() {
        for(int i=0; i<Row; i++){
            for(int j=0; j<Col; j++){
                if(Graph[i][j] != 1){
                    int dis = bfs(i,j);
                    if(dis == -1) return -1;
                    ret = Math.min(ret, dis);
                }
            }
        }
        return ret;
    }

    private static int bfs(int row, int col) {
        boolean[][] visited = new boolean[MAX_N][MAX_N];
        LinkedList<Point> myqueue = new LinkedList<>();
        int hit = 0;

        visited[row][col] = true;

        myqueue.add(new Point(row, col, 0));

        while(!myqueue.isEmpty()){
            Point curr = myqueue.poll();
            if(Graph[curr.x][curr.y] == 2) hit++;
            if(hit == HitCount) return curr.d;
            if(curr.d > ret) return curr.d;

            for(int i=0; i<4; i++){
                int nRow = curr.x + D[i][0];
                int nCol = curr.y + D[i][1];
                if(nRow < 0 || nRow > Row-1 || nCol < 0 || nCol > Col-1) continue;
                if(visited[nRow][nCol]) continue;
                if(Graph[nRow][nCol] == 1) continue;
                visited[nRow][nCol] = true;
                myqueue.add(new Point(nRow, nCol, curr.d + 1));
            }
        }

        return -1;
    }

    static int[] stringToIntArray(String line){
        StringTokenizer st = new StringTokenizer(line);
        int[] ret = new int[st.countTokens()];
        for(int i=0; st.hasMoreTokens(); i++){
            ret[i] = Integer.parseInt(st.nextToken());
        }
        return ret;
    }
}