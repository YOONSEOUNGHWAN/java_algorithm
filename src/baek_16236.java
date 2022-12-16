import java.util.*;

public class baek_16236 {
    static final int INF = 123456789;
    static int N;
    static int Map[][] = new int[20][20];
    static int Dr[] = {-1, 1, 0, 0};
    static int Dc[] = {0, 0, -1, 1};
    static class Point{
        int r, c, d; //움직인 거리
        Point(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        //상어 시작점
        //bfs탐색을 용이하게 하기 위하여 상어의 위치를 저장후 비워놔야 함
        int sR = 0, sC = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                Map[i][j] = sc.nextInt();
                if(Map[i][j] == 9){
                    sR = i;
                    sC = j;
                    Map[i][j] = 0;
                }
            }
        }
        System.out.println(solve(sR, sC));
    }

    private static int solve(int sR, int sC) {
        int ret = 0; //총 움직인 거리
        int size = 2, cnt = 2; // 상어 크기 및 진화를 위한 cnt
        //시작 위치
        Point minPoint = new Point(sR, sC, 0); // 초기값을 INF로 설정 -> 최단 거리를 찾는 로직이기 때문.
        //bfs : 최단경로 못 구하면 탐색 종료.
        while(minPoint.d != INF){
            boolean[][] visited = new boolean[20][20]; // 방문여부 초기화
            //queue
            Queue<Point> Q = new LinkedList<>();
            visited[minPoint.r][minPoint.c] = true;
            minPoint.d = INF;
            Q.add(new Point(minPoint.r, minPoint.c, 0)); // 시작 위치 집어넣고 BFS탐색 시작!!
            while (!Q.isEmpty()){
                Point curr = Q.poll();
                if(curr.d > minPoint.d) break; //최단 경로만 찾을 것임.
                if(Map[curr.r][curr.c] > size) continue; //나보다 큰 물고기면 skip
                //물고기 잡아먹기 로직(행기준, 왼쪽 열부터)
                if(Map[curr.r][curr.c] != 0 && Map[curr.r][curr.c] < size){
                    if(curr.d < minPoint.d){
                        minPoint = curr;
                    }else if(curr.d == minPoint.d){
                        //행 기준
                        if(curr.r < minPoint.r){
                            minPoint = curr;
                            //열 기준
                        }else if(curr.r == minPoint.r && curr.c < minPoint.c){
                            minPoint = curr;
                        }
                    }
                    continue;
                }
                //너비 탐색
                for(int i=0; i<4; i++){
                    int Nr = curr.r + Dr[i];
                    int Nc = curr.c + Dc[i];
                    if(Nr < 0 || Nr > N-1 || Nc < 0 || Nc > N-1) continue;
                    if(visited[Nr][Nc]) continue;
                    visited[Nr][Nc] = true;
                    Q.add(new Point(Nr, Nc, curr.d + 1));
                }
            }
            //최단 경로의 물고기를 잡아먹은 경우
            if(minPoint.d != INF){
                ret += minPoint.d;
                cnt--;
                //상어 진화
                if(cnt == 0){
                    size++;
                    cnt = size;
                }
                //먹은 물고기 삭제
                Map[minPoint.r][minPoint.c] = 0;
            }
        }
        return ret;
    }
}
