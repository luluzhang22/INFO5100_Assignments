/* good work
 * score 10 + extra credit 3.5; Total Score = 10
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class assignment2 {
    public static void main(String[] args){

        System.out.println("****Assignment2.1****");
        System.out.println("If you work 33.5 hours, get salary $" + employeeSalary(13.5));
        System.out.println("If you work 40 hours, get salary $" + employeeSalary(40));
        System.out.println("If you work 47 hours, get salary $" + employeeSalary(47));

        System.out.println();
        System.out.println("****Assignment2.2****");
        System.out.println("The sum of digits of number 3456 is:" + addDigits(3456));

        System.out.println();
        System.out.println("****Assignment2.3****");
        printPerfectNumbers(10000);

        System.out.println();
        System.out.println("****Assignment2.4 and Assignment 2.5****");
        Customer luna = new Customer("luna");

        luna.orderPizza("A", 2);//luna orders 2 pizzas of type A
        luna.orderPizza("B", 5);//luna orders 5 pizzas of type B

        System.out.print(luna.name + " had ordered ");
        Iterator i = luna.pizzas.keySet().iterator();
        while(i.hasNext()){
            String type = (String)i.next();
            int number = luna.pizzas.get(type);
            System.out.print(number + " pizza of type " + type +", ");
        }
        System.out.print("spent $" + luna.purchase());


        System.out.println();
        System.out.println();
        System.out.println("****Assignment2.6****");
        printIsoscelesTriangle(6);

    }

    /**1. Write a java function to calculate the salary of an employee based on the following rules.
    i. function takes input of number of hours an employee worked and returns the salary.
    ii.  The first 36 hours worked are paid at a rate of 15.0,
     then the next 5 hours are paid at a rate of 15 * 1.5.
     Hours after that up to a max of 48 are paid at a rate of 15 * 2.**/

    // score 2
    public static double employeeSalary( double hours){
        if(hours<=0){
            return 0;
        }
        double first = 36;
        double second = 41;
        double third = 48;

        double rate1 = 15;
        double rate2 = 22.5;
        double rate3 = 30;
        double salary;
        double extra;
        if(hours <= first){
            salary = hours * rate1;
        }else if(hours <= second){
            extra = hours-first;
            salary = first*rate1 + extra*rate2;
        }else{
            if(hours < third)
                extra = hours - second;
            else
                extra = third - second;
            salary = first*rate1 + (second-first)*rate2 + extra*rate3;
        }

        return salary;
    }

    /**2. Write a java function that adds all the digits of an integer until it is single digit.
    i. function takes an integer as input and returns its sum of digits.
    ii. for example input = 37, sum = 3+7 = 10, sum = 1+0 = 1. result = 1.**/
    //score 2
    public static int addDigits( int input){
        input = Math.abs(input);
        int result = input;
        while (result >= 10) {
            result = 0;
            while (input >= 10) {
                result += input%10;
                input = input/10;
            }
            result += input;
            input = result;
        }
        return result;
    }


    /**3. Write a java function to print all perfect number between 1 and n.
    i. Perfect number is a positive integer which is equal to the sum of its proper positive divisors.
    ii. For example: 6 is the first perfect number, Proper divisors of 6 are 1, 2, 3.
     Sum of its proper divisors = 1 + 2 + 3 = 6.**/
    // score 2
    public static void printPerfectNumbers( int n){
        if(n<=1){
            System.out.println("There is no perfect number.");
        }else {
            String number = "";
            int sum;
            for (int i = 2; i <= n; i++) {
                sum = 0;
                for (int j = 1; j < i; j++) {
                    if (i % j == 0) {
                        sum += j;
                    }
                }
                if (sum == i) {
                    number = number + sum + " ";
                }
            }
            if (number.equals(""))
                System.out.println("There is no perfect number between 1 and " + n);
            else
                System.out.println("The perfect number between 1 and " + n + " is: " + number);
        }
    }

    /**4. Write a java class called pizza with (Add detail as needed) :
     i. Create atleast 3 attributes :pizza type , unit price and loyalty points. More attributes are welcome to have.
     ii. Create constructors . Extra-credit of 0.5 point if you write more than 1 right constructor to this class**/
    // score 2 + extra credit 0.5
    static class Pizza{
        String pizzaType;
        double unitPrice;
        int points;

        Pizza(String type){ // if I want to create a pizza of type "L" this doesn't create what I require
            switch (type) {
                case "A":
                    points = 5;
                    unitPrice = 5.5;
                    break;
                case "B":
                    points = 4;
                    unitPrice = 4.4;
                    break;
                case "C":
                    points = 3;
                    unitPrice = 3.3;
                    break;
                case "D":
                    points = 2;
                    unitPrice = 2.2;
                    break;
                default:
                    System.out.println("Don't provide this type of pizza!");
            }
            if(points>0) { // you can just assign type to pizzaType without this condition
                pizzaType = type;
            }
        }

        Pizza(){
            pizzaType = "E";
            points = 1;
            unitPrice = 1.1;
        }

        Pizza(String type, Double price, int points){
            pizzaType = type;
            unitPrice = price;
            this.points = points;
        }
    }

    /**5. Write a java class called customer (Add detail as needed) :
     i. Create Attributes: customer name and which pizzas customer has ordered.
     ii. Think about what kind of data structure can be used to record the pizza name and number of each kind of pizza.( Give me your thought, Extra credit of 1 point)
     iii. In main method , sum up how much money the customer spent.**/
    // score 2 + 1
    static class Customer{
        String name;

        //I think HashMap is a good data structure to record the pizza and number of each kind of pizza.
        // Set pizza type as key, and number as value.
        Map<String, Integer> pizzas;
        double bill;

        Customer(String name){
            this.name = name;
            pizzas = new HashMap<>();
        }

        Customer(String name, HashMap<String, Integer> pizzas){
            this.name = name;
            this.pizzas = pizzas;
        }

        Customer(){
            name = "luna";
            pizzas = new HashMap<>();
        }

        void orderPizza(String pizzaType, int number){
            Pizza pizza = new Pizza(pizzaType);
            if(pizza != null) {     // always true as you are creating a new Pizza
                if(pizzas.containsKey(pizzaType)){
                    number += pizzas.get(pizzaType);
                }
                pizzas.put(pizzaType, number);
                bill += pizza.unitPrice * number;
            }
        }

        double purchase(){
            return bill;
        }

    }


     // EXTRA CREDIT
     /**6. Write a Java program that generates an isosceles right angled triangle made of asterisks.
     i. function should take input of one equal side as integer. Other than the edges the inner part of the triangle should be empty.
     ii. For example input is 6. the function should print—

     *
     **
     * *
     *  *
     *   *
     ******/
     // extra credit 2
     public static void printIsoscelesTriangle( int n){
         if(n<1){
             System.out.println("Equal side of an isosceles right angled triangle must lager than 0!");
         }
         String line;
         for(int i = 1;i < n; i++){
             line = "";
             for(int j = 1; j<=i; j++){
                 if(j==1||j==i) {
                     line += "*";
                 }else{
                     line +=" ";
                 }
             }
             System.out.println(line);
         }
         line = "";
         for(int k = 1; k<=n; k++){
             line += "*";
         }
         System.out.println(line);
     }

}
