import java.util.*;
import java.io.*;

public class sds_cake {
    static int T;
    static final int MIN = -123456789;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            int[] arr1 = stringToIntArray(br.readLine());
            int[] arr2 = stringToIntArray(br.readLine());
            sb.append("#"+(i+1)).append(" ").append(solve(arr1, arr2)).append("\n");
        }
        sb.deleteCharAt(sb.length() -1);
        System.out.print(sb);
    }

    private static int solve(int[] arr1, int[] arr2) {
        int size = arr1.length; //10
        int case1 = MIN;
        for(int i=0; i<size-1; i++){
            int tmp = subMax(arr1, 0, i) + subMax(arr2, i + 1, size - 1);
            case1 = Math.max(case1, tmp);
        }
        int case2 = MIN;
        for(int i=0; i<size-1; i++){
            int tmp = subMax(arr2, 0, i) + subMax(arr1, i + 1, size - 1);
            case2 = Math.max(case2, tmp);
        }

        return Math.max(case1, case2);
    }

    private static int subMax(int[] arr, int start, int end) {
        int size = end - start + 1;
        int[] cache = new int[size];
        cache[0] = arr[start];

        for(int i=1; i<size; i++){
            cache[i] = Math.max(0, cache[i-1]) + arr[start+i];
        }

        Arrays.sort(cache);

        return cache[cache.length -1];
    }

    private static int[] stringToIntArray(String readLine) {
        return Arrays.stream(readLine.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
