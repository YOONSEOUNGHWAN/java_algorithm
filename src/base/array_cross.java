package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class array_cross {
    static int Row, Col, Sr, Sc;
    static int[][] Board = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Row = Integer.parseInt(st.nextToken());
        Col = Integer.parseInt(st.nextToken());

        for(int i=0; i<Row; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<Col; j++){
                Board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        Sr = Integer.parseInt(st.nextToken());
        Sc = Integer.parseInt(st.nextToken());

        cross();

        for(int i=0; i<Row; i++){
            for(int j=0; j<Col; j++){
                System.out.print(Board[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void cross() {
        if(Board[Sr][Sc] != 0) return;
        Board[Sr][Sc] = 1;
        //상하좌우
        for(int i = Sr-1; i >= 0 ; i--){
            if(Board[i][Sc] != 0) break;
            Board[i][Sc] = 1;
        }
        for(int i = Sr+1; i < Row ; i++){
            if(Board[i][Sc] != 0) break;
            Board[i][Sc] = 1;
        }
        for(int i = Sc-1; i >= 0 ; i--){
            if(Board[Sr][i] != 0) break;
            Board[Sr][i] = 1;
        }
        for(int i = Sc+1; i < Col ; i++){
            if(Board[Sr][i] != 0) break;
            Board[Sr][i] = 1;
        }
    }
}
