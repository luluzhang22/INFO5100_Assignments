
import java.util.ArrayList;
import java.util.Arrays;

public class MidTerm {
    public static void main(String[] args){
        System.out.println("1.reverseEvenIndices of {6,4,1,0,3,2} -> "+ Arrays.toString(reverseEvenIndices(new int[] {6,4,1,0,3,2})));
        System.out.println("2.arrangeCoins of 8 -> " + arrangeCoins(8));
        System.out.println("3.minMoves of {1,2,3} -> "+minMoves(new int[]{1,2,3}));
        System.out.println("4.countNumberOfPossibleWays when {m,n,x} = {5,3,7} -> "+countNumberOfPossibleWays(5,3,7));

        int[][] ratMaze = {
                {1, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };
        System.out.println("5.find a path in a maze -> "+findPath(ratMaze));
    }

    /**question1
     * Write a method named reverseEvenIndices that takes an integer array as input
     * and outputs an array such that all the values with odd indices remain in the same position.
     * However, elements with even indices should be output in reverse order.
     * That is, the first element with even index should be swapped with the last element with even index,
     * the second even-indexed element with the second-to-last even-indexed element, and so on.
     * Note that zero is an even index.
     */
    public static int[] reverseEvenIndices(int[] nums){
        if(nums==null||nums.length<3)
            return nums;
        int n = nums.length;
        int mid = (n+1)/2;
        if(n%2==0)
            n-=1;
        for(int i = 0; i < mid;i+=2){
            int temp = nums[i];
            nums[i] = nums[n-i-1];
            nums[n-i-1] = temp;
        }
        return nums;
    }

    /**question 2
     * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
     * Given n, find the total number of full staircase rows that can be formed.
     * n is a non-negative integer and fits within the range of a 32-bit signed integer.**/
    public static int arrangeCoins(int n){
        long low = 1;
        long high = n;
        while(low<=high){
            long mid = low + (high - low)/2;
            long check = mid*(mid + 1)/2;
            if(check == n)
                return (int)mid;
            else if(check < n)
                low = mid+1;
            else
                high = mid - 1;
        }
        return (int)high;
    }

    /**question 3
     *Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal,
     *  where a move is incrementing n - 1 elements by 1.
     */
    public static int minMoves(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int min = nums[0];
        for(int n : nums){
            if(n<min)
                min = n;
        }
        int result = 0;
        for(int n : nums){
            result += n - min;
        }
        return result;
    }

    /**question 4
     * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X.
     * X is the summation of values on each face when all the dice are thrown.
     * Your function should take, number of faces, number of dice and required sum as input and return a number of possible ways.
     * @param m number of faces
     * @param n number of dice
     * @param x required sum
     * @return
     */
    public static int countNumberOfPossibleWays(int m, int n, int x){
        //int[n+1][x+1] stores the number of possible ways to get sum x by using n dice
        int[][] dp = new int[n+1][x+1];

        //first, store the situation that when using a dice
        for(int i = 1; i <= m && i<=x; i++){
            dp[1][i] = 1;
        }

        //example:m=5,n=3,n=7 -> [3,7] = [2,2] + [2,3] + [2,4] + [2,5] + [2,6]
        for(int i = 2; i <= n; i++){
            for(int j = i; j <= x;j++ ){
                for(int k = 1; k <= m && k<j;k++){
                    dp[i][j] += dp[i-1][j-k];
                }
            }
        }
        return dp[n][x];
    }

    /**
     * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e., maze[0][0]
     * and destination block is lower rightmost block i.e., maze[N-1][N-1].
     * A rat starts from source and has to reach destination. The rat can move only in two directions: forward and down.
     * In the maze matrix, 0 means the block is dead and 1 means the block can be used in the path from source to destination.
     * Your function should take the maze as input and return an arrayList of the resulting path. If no path is found return empty list.
     *
     */
    static class Cell{
        int x,y;
        Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString(){
            return "[" + this.x + "," + this.y + "]";
        }
    }


    public static ArrayList<Cell> findPath(int[][] maze){
        int[][] path = new int[maze.length][maze[0].length];
        boolean pathExists = isPathExist(maze, path, 0, 0);
        ArrayList<Cell> result = new ArrayList<>();
        if(pathExists) {
            for (int i = 0; i < path.length; i++) {
                for (int j = 0; j < path[0].length; j++) {
                    if (path[i][j] == 1) {
                        result.add(new Cell(i, j));
                    }
                }
            }
        }
        return result;
    }

    private static boolean isPathExist(int[][] maze,int[][] path, int curX, int curY){
        if(curX < 0 || curY < 0 || curX >= maze.length || curY >= maze[0].length || maze[curX][curY] == 0)
            return false;

        path[curX][curY] = 1;

        //got destination
        if(curX == maze.length - 1 && curY == maze[0].length - 1){
            return true;
        }

        //move forward or down
        if(isPathExist(maze,path,curX+1,curY)||isPathExist(maze,path,curX,curY+1)){
            return true;
        }else
            path[curX][curY] = 0;
        return false;
    }
}
