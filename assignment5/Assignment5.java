
import java.util.ArrayList;
import java.util.List;

public class Assignment5 {
    public static void main(String[] args){
        System.out.println("----------Checkout system of dessert shop----------");
        Checkout checkout = new Checkout();

        checkout.enterItem(new Candy("Peanut Butter Fudge",2.25,399));
        checkout.enterItem(new IceCream("Vanilla Ice Cream",105));
        checkout.enterItem(new Sundae("Choc. chip Ice Cream", 145, "Hot Fudge", 50));
        checkout.enterItem(new Cookie("Oatmeal Raisin Cookies", 4, 399));

        System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
        System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
        System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
        System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
        System.out.println(checkout);

        checkout.clear();

        checkout.enterItem(new IceCream("Strawberry Ice Cream", 145));
        checkout.enterItem(new Sundae("Vanilla Ice Cream", 105, "Caramel", 50));
        checkout.enterItem(new Candy("Gummy Worms", 1.33, 89));
        checkout.enterItem(new Cookie("Chocolate Chip Cookies" ,4, 399));
        checkout.enterItem(new Candy("Salt Water Taffy", 1.5, 209));
        checkout.enterItem(new Candy("Candy corn", 3.0, 109));

        System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
        System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
        System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
        System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
        System.out.println(checkout);


        //Extra Credit
        System.out.println("----------Spiral order of a matrix----------");
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> spiral = spiralOrder(matrix);
        for(int i = 0; i<spiral.size();i++){
            System.out.print(spiral.get(i)+"  ");
        }
    }
    //return all elements of the matrix in spiral order
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0)
            return res;
        int r1 = 0 , r2 = matrix.length-1;
        int c1 = 0 , c2 = matrix[0].length-1;
        while(c1<=c2 && r1<=r2){
            for(int c = c1; c <= c2; c++){
                res.add(matrix[r1][c]);
            }
            for(int r = r1+1; r <= r2; r++){
                res.add(matrix[r][c2]);
            }
            if(c1<c2 && r1 < r2){
                for(int c = c2-1; c>=c1; c--){
                    res.add(matrix[r2][c]);
                }
                for(int r = r2-1; r>r1; r--){
                    res.add(matrix[r][c1]);
                }
            }
            c1++;
            r1++;
            c2--;
            r2--;
        }
        return res;
    }
}
