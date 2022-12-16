import java.util.*;
public class baek_15686 {
    static int N, M;
    static final int INF = 123456789;
    static List<Pair> House = new ArrayList<>();
    static List<Pair> Chicken = new ArrayList<>();
    static class Pair{
        Integer x, y;

        public Pair(Integer x, Integer y){
            this.x = x;
            this.y = y;
        }

        public Integer getX(){
            return x;
        }
        public Integer getY(){
            return y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int input = sc.nextInt();
                if(input == 1){
                    House.add(new Pair(i, j));
                }else if(input == 2){
                    Chicken.add(new Pair(i, j));
                }
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        int ret = INF;
        for(int subset = 0; subset < 1 << Chicken.size(); subset++){
            if(countBits(subset) == M){
                int sum = 0;
                for (Pair house : House) {
                    int dist = INF;
                    for(int j=0; j<Chicken.size(); j++){
                        if((subset & 1<<j) != 0){
                            Pair chicken = Chicken.get(j);
                            dist = Math.min(dist, Math.abs(house.getX() - chicken.getX()) + Math.abs(house.getY() - chicken.getY()));
                        }
                    }
                    sum += dist;
                }
               ret = Math.min(ret, sum);
            }
        }
        return ret;
    }

    private static int countBits(int subset) {
        int cnt = 0;
        while(subset > 0){
            if((subset & 1) == 1) cnt++;
            subset = subset >> 1;
        }
        return cnt;
    }
}

