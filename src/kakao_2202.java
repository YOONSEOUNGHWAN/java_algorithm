import java.util.*;

class kakao_2202 {
    public int solution(int n, int k) {
        //진수 변환 -> 원하는 숫자로 나눈 나머지
        String digitString = parseDigitString(n,k);
        //소수 확인
        StringTokenizer st = new StringTokenizer(digitString,"0");
        // digitString.split("0+");
        int answer = 0;

        while(st.hasMoreTokens()){
            if(isPrime(Long.parseLong(st.nextToken()))) answer++;
        }
        return answer;
    }

    boolean isPrime(long num){
        if(num == 2) return true;
        if(num == 1 || num % 2 == 0) return false;
        for(long i = 3; i <= (long)Math.sqrt(num); i += 2){
            if(num % i == 0) return false;
        }
        return true;
    }

    String parseDigitString(int num, int base){
        if(base == 10) return String.valueOf(num);

        StringBuilder sb = new StringBuilder();
        while(num != 0){
            int digit = num % base;
            sb.append(String.valueOf(digit));
            num /= base;
        }
        return sb.reverse().toString();
    }

    String parseDigit(int num, int base){
        int q = num / base, r = num % base;
        if(q!=0) return parseDigit(q, base) + String.valueOf(r);
        return String.valueOf(r);
    }
}