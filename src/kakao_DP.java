import java.util.*;

class kakao_DP {
    final static int INF = 123456789;
    int[] Sales;
    List<List<Integer>> Children = new ArrayList<>();
    int[][] Cost = new int[300000][2];

    public int solution(int[] sales, int[][] links) {
        Sales = sales;
        for(int i=0; i<sales.length; i++){
            Children.add(new ArrayList<>());
        }

        for(int[] link : links){
            Children.get(link[0]-1).add(link[1]-1);
        }

        traversal(0);

        return Math.min(Cost[0][0], Cost[0][1]);
    }

    void traversal(int node){
        Cost[node][0] = 0;
        Cost[node][1] = Sales[node];

        if(Children.get(node).isEmpty()) return;
        int extraCost = INF;

        for(int child : Children.get(node)){
            traversal(child);
            if(Cost[child][0] < Cost[child][1]){
                Cost[node][0] += Cost[child][0];
                Cost[node][1] += Cost[child][0];
                extraCost = Math.min(extraCost, Cost[child][1] - Cost[child][0]);
            }else{
                Cost[node][0] += Cost[child][1];
                Cost[node][1] += Cost[child][1];
                extraCost = 0;
            }
        }
        Cost[node][0] += extraCost;
    }
}