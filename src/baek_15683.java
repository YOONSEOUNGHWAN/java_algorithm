import java.util.*;

public class baek_15683 {
    static int INF = 123456789;
    static int N, M;
    static int[][] Map = new int[8][8];
    static ArrayList<Camera> cameras= new ArrayList<>();
    static class Camera{
        int type;
        int r, c;
        Camera(int r, int c, int type){
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }
    //시계방향 오른쪽, 아래, 왼쪽, 위에
    static int[] Dr = {0, 1, 0, -1};
    static int[] Dc = {1, 0, -1, 0};
    static int[][] Dcamera ={
            //각 경우마다 90도 회전으로 얻는 경우의 수
            {1, 0, 0, 0, 4}, //오른쪽만 탐색가능 1번카메라
            {1, 0, 1, 0, 2}, //오른쪽 왼쪽 탐색가능 2번카메라
            {1, 0, 0, 1, 4}, //오른쪽 위쪽 탐색가능 3번 카메라
            {1, 0, 1, 1, 4}, //오른쪽 왼쪽, 위쪽 탐색가능 4번카메라
            {1, 1, 1, 1, 1} //모든방향 탐색가능
    };
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                Map[i][j] = sc.nextInt();
                if(Map[i][j] >=1 && Map[i][j] <=5){
                    cameras.add(new Camera(i, j, Map[i][j]));
                }
            }
        }
        System.out.println(solve(0));
    }

    private static int solve(int position) {
        if(position == cameras.size()){
            int sum = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(Map[i][j] == 0) sum++;
                }
            }
            return sum;
        }
        int ret = INF;
        Camera camera = cameras.get(position);
        int[][] copy = new int[8][8];
        //90도 회전
        for(int i=0; i<Dcamera[camera.type-1][4]; i++){
            copymap(copy, Map);
            //어떤 카메라인지
            for(int j=0; j<4; j++) {
                if (Dcamera[camera.type - 1][j] == 1)
                    //90도 회전을 고려해야함.
                    //i 값에 따라 회전하는 것이고, j인덱스 위치에서 더해주어야 함
                    fill(camera.r, camera.c, (i + j) % 4);
            }

            ret = Math.min(ret, solve(position+1));

            copymap(Map, copy);
        }
        return ret;
    }

    private static void copymap(int[][] copy, int[][] map) {
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                copy[i][j] = map[i][j];
    }

    private static void fill(int r, int c, int d) {
        int Sr = r, Sc = c;
        while(true){
            int Nr = Sr + Dr[d], Nc = Sc + Dc[d];
            //넘어감
            if(Nr < 0 || Nr > N-1 || Nc < 0 || Nc > M-1) return;
            //벽을 만남
            if(Map[Nr][Nc] == 6)return;
            Map[Nr][Nc] = 9; //check

            Sr = Nr;
            Sc = Nc;
        }
    }
}

