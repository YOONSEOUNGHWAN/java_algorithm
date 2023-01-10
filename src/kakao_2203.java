import java.util.*;

class kakao_2203 {
    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> intime = new HashMap<>();
        //정렬
        TreeMap<String, Integer> result = new TreeMap<>();

        for(String record : records){
            StringTokenizer st = new StringTokenizer(record);
            String time = st.nextToken();
            String number = st.nextToken();
            String cmd = st.nextToken();

            if(cmd.equals("IN")){
                intime.put(number, timeToMin(time));
                if(result.containsKey(number) == false){
                    result.put(number, 0);
                }
            }else{
                result.put(number, result.get(number)
                        + timeToMin(time) - intime.get(number));
                intime.remove(number);
            }
        }

        intime.forEach((key, val) -> {
            result.put(key, result.get(key) + 23 * 60 + 59 - val);
        });

        int[] answer = new int[result.size()];

        int idx = 0;
        // for(String key : result.keySet()){
        //     System.out.print(key + " ");
        // }
        for(int val : result.values()){
            answer[idx] = fees[1];
            if(val > fees[0]){
                answer[idx] += Math.ceil((val - fees[0]) / (double)fees[2]) * fees[3];
            }
            idx++;
        }
        return answer;
    }

    int timeToMin(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
}