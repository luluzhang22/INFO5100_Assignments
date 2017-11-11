
import java.util.ArrayList;
import java.util.List;

public class Assignment7 {
    public static void main(String[] args){
        System.out.println("----7.4 Pascal's triangle----");
        printPascalTriangle(6);
        System.out.println("----7.5 isPartition----");
        System.out.println("if {1,5,11,5} could be partitioned into two equal subsets: " + findPartition(new int[]{1,5,11,5}));
        System.out.println("if {1,5,3} could be partitioned into two equal subsets: " + findPartition(new int[]{1,5,3}));
    }

    /**
     * 7.4 print first n lines of the Pascal's triangle
     * @param n
     */
    public static void printPascalTriangle(int n){
        if(n<1)
            return;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = i - 1; j > 0; j --) {
                list.set(j,list.get(j)+list.get(j-1));
            }
            list.add(1);
            System.out.println(String.valueOf(list));
        }
    }

    public static boolean findPartition(int arr[]) {
        if (arr == null || arr.length == 0)
            return true;
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if (sum % 2 != 0)
            return false;
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = sum; j >= arr[i]; j--) {
                dp[j] = dp[j] || dp[j - arr[i]];
            }
        }
        return dp[sum];
    }
}
