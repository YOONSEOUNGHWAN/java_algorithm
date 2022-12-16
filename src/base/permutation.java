package base;

public class permutation {
    static int N = 4;
    static int[] Nums = {1, 2, 3, 4};

    public static void main(String[] args) {
        solve_sum(0, 0, 0);
        solve_num(0,0,0);
    }

    private static int solve_num(int cnt, int used, int val) {
        if(cnt == 2) return val;
        int ret = 0;
        for(int i=0; i<N; i++){
            if((used & 1 << i) != 0) continue;
            ret = Math.max(ret, solve_num(cnt+1, used | 1 << i, val * 10 + Nums[i]));
        }
        return ret;
    }

    private static int solve_sum(int pos, int cnt, int val){
        if(cnt == 2) return val;
        if(pos == N) return -1; // 1 -> 2 -> 3 -> 4

        int ret = 0;
        ret = Math.max(ret, solve_sum(pos+1, cnt+1, val + Nums[pos]));
        ret = Math.max(ret, solve_sum(pos+1, cnt, val));

        return ret;
    }
}
