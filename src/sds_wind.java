//import java.util.*;
//import java.io.*;
//
//public class sds_wind {
//    static int T;
//    static final int MAX_N = 100;
//    static int Row, Col, Cnt;
//    static int[][] Town = new int[MAX_N][MAX_N];
//
//    static char[] CMD = new char[] {'U', 'D', 'L', 'R'};
//
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        T = Integer.parseInt(br.readLine());
//        StringBuilder sb = new StringBuilder();
//        for(int k=0; k<T; k++){
//            int[] tmp = stringToIntArray(br.readLine());
//            Row = tmp[0];
//            Col = tmp[1];
//            Cnt = tmp[2];
//            for(int i=0; i<Row; i++){
//                Town[i] = stringToIntArray(br.readLine());
//            }
//            sb.append("#"+(k+1)).append(" ").append(solve()).append("\n");
//        }
//        sb.deleteCharAt(sb.length() -1);
//        System.out.print(sb);
//    }
//
//    private static int solve() {
//        //조합
//        char[][] cmdList = makeCmdList();
//    }
//
//    private static char[][] makeCmdList() {
//
//
//    }
//
//    private static int solve_num(int cnt, int used, int val) {
//        if(cnt == 2) return val;
//        int ret = 0;
//        for(int i=0; i<N; i++){
//            if((used & 1 << i) != 0) continue;
//            ret = Math.max(ret, solve_num(cnt+1, used | 1 << i, val * 10 + Nums[i]));
//        }
//        return ret;
//    }
//
//    static int[] stringToIntArray(String line){
//        return Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
//    }
//
//    static void clearMap(){
//        for(int i=0; i<MAX_N; i++){
//            for(int j=0; j<MAX_N; j++)
//                Town[i][j] = 0;
//        }
//    }
//}
