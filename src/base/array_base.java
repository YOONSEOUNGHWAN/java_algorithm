package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class array_base {

    static int N;
    static int[] Score = new int[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            Score[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println("st = " + st);
        System.out.println("st.toString() = " + st.toString());
        for(int i=0; i<N; i++){
            System.out.print(Score[i] + " ");
        }
    }
}
