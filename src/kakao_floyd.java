import java.util.*;

class kakao_floyd {
    static final int INF = 123456789;
    int[][] Dist = new int[200][200];

    public int solution(int n, int s, int a, int b, int[][] fares) {

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j) Dist[i][j] = 0;
                else Dist[i][j] = INF;
            }
        }

        for(int[] edge : fares){
            Dist[edge[0]-1][edge[1]-1] = edge[2];
            Dist[edge[1]-1][edge[0]-1] = edge[2];
        }

        floyd(n);

        --s;
        --a;
        --b;
        int answer = INF;

        for(int k=0; k<n; k++){
            answer = Math.min(answer,Dist[s][k] + Dist[k][a] + Dist[k][b]);
        }


        return answer;
    }

    void floyd(int n){
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(Dist[i][k] + Dist[k][j] < Dist[i][j]){
                        Dist[i][j] = Dist[i][k] + Dist[k][j];
                    }
                }
            }
        }
    }

}