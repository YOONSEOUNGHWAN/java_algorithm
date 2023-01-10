import java.util.*;

class kakao_2204 {
    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        int[] tmp = new int[11];
        int maxDiff = 0;

        //부분집합
        for(int subset = 1; subset < (1 << 10); subset++){
            int ryan = 0, apeach = 0, cnt = 0;
            for(int i=0; i < 10; i++){
                if((subset & (1 << i)) != 0){
                    ryan += 10 - i;
                    tmp[i] = info[i] + 1;
                    cnt += tmp[i];
                }else{
                    tmp[i] = 0;
                    if(info[i] > 0) apeach += 10-i;
                }
            }


            if(cnt > n) continue;

            tmp[10] = n - cnt;

            if(ryan - apeach == maxDiff){
                for(int i=10; i >= 0; i--){
                    if(tmp[i] > answer[i]){
                        answer = tmp.clone();
                        break;
                    }else if(tmp[i] < answer[i]){
                        break;
                    }
                }
            }

            if(ryan - apeach > maxDiff){
                maxDiff = ryan - apeach;
                answer = tmp.clone();
            }
        }

        if(maxDiff == 0) return new int[] {-1};

        return answer;
    }
}