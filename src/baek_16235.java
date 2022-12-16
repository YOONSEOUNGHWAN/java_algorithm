import java.util.*;

public class baek_16235 {
    static class Tree{
        int r, c, age;
        boolean alive;
        Tree(int r, int c, int age, boolean alive){
            this.r = r;
            this.c = c;
            this.age = age;
            this.alive = alive;
        }
    }
    static int N, M, K;
    static int A[][] = new int[10][10];
    static int Nourish[][] = new int[10][10];
    static LinkedList<Tree> Trees = new LinkedList<>();
    static int Dr[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int Dc[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                A[i][j] = sc.nextInt();
                Nourish[i][j] = 5;
            }
        }

        int x, y, z;
        for(int i=0; i<M; i++){
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            Trees.add(new Tree(x-1, y-1, z, true));
        }

        System.out.println(solve());

    }

    private static int solve() {
        for(int k=0; k<K; k++){
            //봄
            for(Tree t : Trees){
                //양분먹기
                if(t.age <= Nourish[t.r][t.c]){
                    Nourish[t.r][t.c] -= t.age;
                    t.age++;
                }else{
                    t.alive = false;
                }
            }

            //여름
            Iterator<Tree> it = Trees.iterator();
            while(it.hasNext()){
                Tree tree = it.next();
                //죽었다면
                if(!tree.alive){
                    Nourish[tree.r][tree.c] += tree.age/2;
                    it.remove();
                }
            }

            //가을
            LinkedList<Tree> trees = new LinkedList<>();
            for(Tree t : Trees){
                //번식
                if(t.age % 5 == 0){
                    for(int i=0; i<8; i++){
                        int Nr = t.r + Dr[i];
                        int Nc = t.c + Dc[i];
                        if(Nr < 0 || Nr > N-1 || Nc <0 || Nc > N-1)continue;
                        trees.add(new Tree(Nr, Nc, 1, true));
                    }
                }
            }
            //나이가 적은 순으로 삽입
            Trees.addAll(0, trees);

            //겨울
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++)
                    Nourish[i][j] += A[i][j];

        }
        return Trees.size();
    }
}