import java.util.*;

/**
 * 사다리 조작
 * 1 -> 오른쪽
 * 2 -> 왼쪽
 * 0 -> 아래로
 * 0인 경우 가로선을 긋거나 안 그을 수 있음.
 * 경우의 수에서 가로선을 긋는 순서는 선택에 영향을 주지 않음 -> 조합
 * 모든 경우의 수에서 가로선을 긋고 / 안 긋고로 재귀호출
 */
public class baek_15684 {
    static final int INF = 123456789;
    static final int RIGHT = 1;
    static final int LEFT = 2;
    static int N, M, H;
    static int[][] Map = new int[30][10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        H = sc.nextInt();
        for(int i=0; i<M; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            Map[a - 1][b - 1] = RIGHT;
            Map[a - 1][b] = LEFT;
        }
        int answer = solve(0, 0);
        if(answer == INF)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
    static int solve(int pos, int cnt){
        if(cnt == 3 || pos >= N * H){
            if(check())
                return cnt;
            return INF;
        }
        int ret = INF;
        int row = pos/ N, col = pos % N;
        if(col != N-1 && Map[row][col] == 0 && Map[row][col+1] == 0){
            Map[row][col] = RIGHT;
            Map[row][col+1] = LEFT;
            ret = Math.min(ret, solve(pos +2, cnt +1));
            Map[row][col] = Map[row][col + 1] = 0;
        }
        ret = Math.min(ret, solve(pos+1, cnt));
        return ret;
    }

    static boolean check(){
        for(int i=0; i<N; i++){
            int row = 0, col = i;
            do{
                if(Map[row][col] == LEFT)
                    col--;
                else if(Map[row][col] == RIGHT)
                    col++;
                row++;
            }while(row != H);
            if(col != i) return false;
        }
        return true;
    }
}