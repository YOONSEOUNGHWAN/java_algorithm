import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.*;
import java.io.*;

public class test {
    static final int MAX_N = 100;
    static int MIN = 123456789;

    static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[][] Graph = new int[MAX_N][MAX_N];
    static final int INF = 123456789;
    static int Row, Col, hitCount;
    static int T;

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
        for(int i=0; i<T; i++){
            int[] tmp = stringToIntArray(br.readLine());
            Row = tmp[0];
            Col = tmp[1];
            hitCount = tmp[2];
            for(int j=0; j<Row; j++){
                String line = br.readLine();
                for(int k=0; k<line.length(); k++){
                    if(line.charAt(k) == '#'){
                        Graph[j][k] = 2;
                    }
                }
            }
            for(int j=0; j<hitCount; j++){
                tmp = stringToIntArray(br.readLine());
                int fireR = tmp[0]-1;
                int fireC = tmp[1]-1;
                Graph[fireR][fireC] = 1;
            }
            sb.append("#"+(i+1)).append(" ").append(solve()).append("\n");
            for(int j=0; j<Row; j++){
                for(int k=0; k<Col; k++){
                    System.out.print(Graph[j][k] + " ");
                }
                System.out.println();
            }
            clearGraph();
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }

    private static int solve() {
        int ret = INF;
        int flag = 0;
        for(int i=0; i<Row; i++){
            for(int j=0; j<Col; j++){
                if(Graph[i][j] != 2){
                    int bfs = bfs(i, j);
                    if(bfs != -1 || bfs < MIN){
                        ret = Math.min(ret, bfs);
                        MIN = ret;
                    }
                }
            }
        }
        if(ret == INF) return -1;
        return ret;
    }

    private static int bfs(int row, int col) {
        boolean[][] visited = new boolean[MAX_N][MAX_N];
        LinkedList<Point> myqueue = new LinkedList<>();

        visited[row][col] = true;

        int hit = 0;

        myqueue.add(new Point(row, col, 0));

        while(!myqueue.isEmpty()){
            Point curr = myqueue.poll();
            if(Graph[curr.x][curr.y] == 1) hit++;
            if(hit == hitCount) return curr.d;

            if(curr.d > MIN) return -1;

            for(int i=0; i<4; i++){
                int nRow = D[i][0] + curr.x;
                int nCol = D[i][1] + curr.y;
                if(nRow < 0 || nRow > Row -1 || nCol < 0 || nCol > Col-1) continue;
                if(visited[nRow][nCol]) continue;
                if(Graph[nRow][nCol] == 2) continue;
                visited[nRow][nCol] = true;
                myqueue.add(new Point(nRow, nCol, curr.d+1));
            }
        }

        return -1;
    }
    private static void clearGraph(){
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                Graph[i][j] = 0;
            }
        }
        MIN = 123456789;

    }
    static int[] stringToIntArray(String line){
        return Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
