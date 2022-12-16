import java.util.*;

public class baek_17140 {
    static int R, C, K;
    static int[][] Arr = new int[100][100];

    static class Number{
        int n, c;
        public Number(int num, int cnt){
            this.n = num;
            this.c = cnt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt()-1;
        C = sc.nextInt()-1;
        K = sc.nextInt();

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                Arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        int rsize = 3, csize = 3;
        for(int t=0; t<=100; t++){
            if(Arr[R][C] == K) return t;

            if(rsize >= csize){
                for(int i=0; i<rsize; i++){
                    List<Number> numberList = new ArrayList<>();
//                    int[]
                }
            }else{

            }
        }
        return -1;
    }
}
