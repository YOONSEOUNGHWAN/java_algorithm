import java.util.*;
import java.io.*;

class kakao_2201 {
    public int[] solution(String[] id_list, String[] report, int k) {
        //중복처리를 위한 Hash<String, Set>
        HashMap<String, HashSet<String>> reportHash = new HashMap<>();
        HashMap<String, HashSet<String>> resultHash = new HashMap<>();

        for (String r : report) {
            StringTokenizer st = new StringTokenizer(r);
            String src = st.nextToken();
            String dst = st.nextToken();
            if (reportHash.containsKey(src) == false) {
                reportHash.put(src, new HashSet<>());
            }
            reportHash.get(src).add(dst);

            if (resultHash.containsKey(dst) == false) {
                resultHash.put(dst, new HashSet<>());
            }
            resultHash.get(dst).add(src);
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < answer.length; i++) {
            String user = id_list[i];
            if (reportHash.containsKey(user) == false) continue;
            for (String bad : reportHash.get(user)) {
                if (resultHash.get(bad).size() >= k) {
                    answer[i]++;
                }
            }
        }
        return answer;
    }
}