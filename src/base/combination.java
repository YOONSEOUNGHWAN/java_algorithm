package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class combination {

    static int N;
    static int[] Arr = new int[10];


    public static void main(String[] args) throws IOException {
        char[] data = {'A', 'B', 'C', 'D'};
        printSubsets(data);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            Arr[i] = Integer.parseInt(st.nextToken());
        }
        int solve = solve();
        System.out.println("solve = " + solve);
    }

    private static int solve() {
        int cnt = 0;
        for(int i=0; i < (1<<N); i++){
            if(Integer.bitCount(i) != 2) continue;
            int sum = 0;
            for(int j=0; j<N; j++){
                if((i & 1 << j) != 0){
                    sum += Arr[j];
                }
            }
            if(sum == 7) cnt++;
        }
        return cnt;
    }

    private static void printSubsets(char[] data) {
        int size = data.length;
        //부분 집합의 개수 -> 2^size
        for(int i=0; i<(1<<size); i++){
            System.out.print("{ ");
            //겹치는 원소 찾기
            for(int j=0; j<size; j++){
                //원하는 위치에 존재할 경우
                if((i & 1 << j) != 0){
                    System.out.print(data[j] + " ");
                }
            }
            System.out.print(Integer.bitCount(i));
            System.out.println("}");
        }
    }
}
