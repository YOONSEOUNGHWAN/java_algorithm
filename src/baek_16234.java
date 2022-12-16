import java.io.InputStream;
import java.util.*;

public class baek_16234 {
    //입력값
    static int N, L, R;
    //국가
    static int Map[][] = new int[50][50];
    //방문여부
    static int Visited[][] = new int[50][50];
    //인접 국가 수
    static int Cnt;

    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                Map[i][j] = sc.nextInt();
        System.out.println(solve());

    }

    private static int solve() {
        int ret = 0;
        boolean flag = true;
        //인접한 국가가 있을 경우 무한 반복!
        while(flag){
            flag = false;
            //방분여부 초기화
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++)
                    Visited[i][j]=0;
            //인접 여부 확인
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    //방문한 적이 없다면
                    if(Visited[i][j] == 0){
                        Cnt = 0;
                        //인접한 마을의 총 인구수를 찾는다.
                        int sum = find(i, j, -1);
                        //인접한 마을이 있는 경우
                        if(Cnt > 1){
                            flag = true; // 인접 국가를 찾은 경우 한번더 반복해야함
                            move(i, j, sum/Cnt); // 인구 분배
                        }else{
                            // 인구 분배에 포함되면 안되므로 제외
                            // 일단 국가를 찾으면 Visited[i][j] = 1이 되므로
                            // 추후 인구분배를 할 때 포함이 될 수 있음.
                            Visited[i][j] = 2;
                        }
                    }
                }
            }
            if(flag) ret++;
        }
        return ret;
    }

    private static void move(int r, int c, int value) {
        if(r < 0 || r > N-1 || c < 0 || c > N-1) return;
        //방문이 가능한 경우만
        if(Visited[r][c] != 1) return;
        Map[r][c] = value;
        Visited[r][c] = 2; // 인구분배 완료.
        move(r-1, c, value);
        move(r+1, c, value);
        move(r, c-1, value);
        move(r, c+1, value);
    }

    private static int find(int r, int c, int pre) {
        if(r < 0 || r > N-1 || c < 0 || c > N-1) return 0;
        if(Visited[r][c] != 0) return 0;
        //인접 방문이 가능한 조건
        if(pre != -1){
            int diff = Math.abs(pre - Map[r][c]);
            //인접 불가능
            if(diff < L || diff > R) return 0;
        }
        Visited[r][c] = 1; // 방문
        Cnt++; // 인접개수
        int sum = Map[r][c];
        sum += find(r-1, c, Map[r][c]);
        sum += find(r+1, c, Map[r][c]);
        sum += find(r, c-1, Map[r][c]);
        sum += find(r, c+1, Map[r][c]);
        return sum;
    }

}
