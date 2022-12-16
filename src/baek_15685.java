import java.util.*;

/**
 * 방향만 나타내면 된다.
 * 0 : 오른쪽
 * 1 : 위쪽
 * 2 : 왼쪽
 * 3 : 아래쪽
 * 0
 * 01
 * 0121
 * 0121|2321 <- 대칭 축을 기준으로 한칸의 차이
 */
public class baek_15685 {
    static int[][] Map = new int[101][101];
    static int[] Dx = {1, 0, -1, 0};
    static int[] Dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=0; i<n; i++){
            List<Integer> curves = new ArrayList<>();
            int x, y, d, g;
            x = sc.nextInt();
            y = sc.nextInt();
            d = sc.nextInt();
            g = sc.nextInt();

            curves.add(d);
            for(int j=0; j<g ;j++){
                for(int k=curves.size() -1; k>=0; k--){
                    curves.add((curves.get(k)+1) % 4);
                }
            }

            Map[y][x] = 1;
            for(int dir : curves){
                x += Dx[dir];
                y += Dy[dir];
                Map[y][x] = 1;
            }

            System.out.println(solve());
        }
    }
    static int solve(){
        int ret = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(Map[i][j] == 1 && Map[i][j+1] == 1 && Map[i+1][j] == 1 && Map[i+1][j+1] == 1){
                    ret++;
                }
            }
        }
        return ret;
    }
}
