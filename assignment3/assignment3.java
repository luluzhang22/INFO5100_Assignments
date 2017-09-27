import java.util.Arrays;

public class assignment3 {
    public static void main(String[] args){
        String vowelString = "abcdef ABCDEF";
        System.out.println("3.3 Remove vowels in a String \""+vowelString+"\" get:\""+removeVowelsFromString(vowelString)+"\"");

        String s1 = "Anagram";
        String s2 = "nAgaram";
        System.out.println("3.4 Check if \""+s1+"\" and \""+s2+"\" are Anagrams or not:"+checkIfTwoStringsAreAnagrams(s1,s2));

        double a1 = 8;
        double a2 = 2.5;

        System.out.println();
        System.out.println("3.5 Calculator");
        calculator c = new calculator();
        System.out.println("Addition:"+ a1 +" + " + a2 + " = " + c.addition(a1,a2) );
        System.out.println("Subtraction:"+ a1 +" - " + a2 + " = " + c.subtraction(a1,a2) );
        System.out.println("Multiplication:"+ a1 +" * " + a2 + " = " + c.multiplication(a1,a2) );

        double temp = c.division(a1,a2);
        if(Double.isNaN(temp)||Double.isInfinite(temp)){
            System.out.println("Division:"+ a1 +" / " + a2 + " : dividend should not be zero!");
        }else{
            System.out.println("Division:"+ a1 +" / " + a2 + " = " + temp);
        }

        temp = c.squareRoot(a1);
        if(Double.isNaN(temp)){
            System.out.println("Square root of " + a1 + " : negative number doesn't have a square root!");
        }else{
            System.out.println("Square root of " + a1 + " is " + temp);
        }

        System.out.println("Square of " + a1 + " is " + c.square(a1));
        System.out.println("Cube of " + a1 + " is " + c.cube(a1));

        double fahrenheit = 80;
        double celsius = c.convertFToC(fahrenheit);
        System.out.println("Convert Fahrenheit " + fahrenheit + "F to Celsius:"+celsius+"C");
        System.out.println("Convert Celsius " + celsius +"C to Fahrenheit:"+c.convertCToF(celsius)+"F");

        double feet = 3.5;
        double inches = c.feetToInches(feet);
        System.out.println("Convert feet " + feet + " to inches:"+inches);
        System.out.println("Convert inches " + inches + " to feet:"+c.inchesToFeet(inches));

        System.out.println("****extra****");
        a1 = 1;
        a2 = -10;
        double a3 = 21;
        double[] result = c.solveQuadratic(a1,a2,a3);
        if(result == null){
            System.out.println("quadratic equation "+a1+"x^2+"+a2+"x+"+a3+"=0 doesn't have a solution");
        }else{
            System.out.print("the solution of "+a1+"x^2+"+a2+"x+"+a3+"=0 is:");
            for (double d : result){
                System.out.print(d+" ");
            }
        }
    }

    /**3.3 remove vowels in a string**/
    public static String removeVowelsFromString(String input){
        if(input!=null&&input.length()!=0){
//            input = input.replaceAll("a|e|i|o|u|A|E|I|O|U","");
            String vowels = "aeiouAEIOU";
            char[] chars = input.toCharArray();
            input = "";
            for(int i = 0; i < chars.length; i++){
                if(!vowels.contains(chars[i]+"")){
                    input+=chars[i];
                }
            }
        }
        return input;
    }

    /**3.4 check if two strings are Anagrams or not**/
    public static boolean checkIfTwoStringsAreAnagrams(String s1, String s2){
        if (s1 == null || s2 == null || s1.length()!=s1.length() || s1.equals(s2))
            return false;

        char[] c1 = s1.toCharArray();
//        for(char c : c1){
//            if (s2.contains(c+""))
//                s2 = s2.replaceFirst(c+"","");
//            else
//                return false;
//        }

        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1,c2);
//        return true;
    }

    /**3.5 Create a calculator**/
    static class calculator{
        double addition(double argument1, double argument2){
            return argument1+argument2;
        }
        double subtraction(double argument1, double argument2){
            return argument1 - argument2;
        }
        double multiplication(double argument1, double argument2){
            return argument1*argument2;
        }
        double division(double argument1, double argument2){ return argument1/argument2; }
        double squareRoot(double argument1){
            return Math.sqrt(argument1);
        }
        double square(double argument1){
            return argument1*argument1;
        }
        double cube(double argument1){
            return argument1*argument1*argument1;
        }

        double convertFToC(double fahrenheit){
            return division(subtraction(fahrenheit,32),1.8);
        }

        double convertCToF(double celsius){
            return addition(multiplication(celsius,1.8),32);
        }

        double feetToInches(double feet){
            return multiplication(feet,12);
        }
        double inchesToFeet(double inches){
            return division(inches,12);
        }

        //extra
        double[] solveQuadratic(double a, double b, double c){
            double[] result;
            if(a == 0){
                if(b == 0)
                    return null;
                else{
                    result = new double[1];
                    result[0] = division(-c,b);
                    return result;
                }
            }
            //check if b^2-4ac>=0
            double temp = subtraction(square(b),multiplication(multiplication(a,c),4));
            if(temp<0)
                return null;
            else if(temp == 0){
                result = new double[1];
                result[0] = division(addition(-b,squareRoot(temp)),multiplication(2,a));
            }else{
                result = new double[2];
                result[0] = division(addition(-b,squareRoot(temp)),multiplication(2,a));
                result[1] = division(subtraction(-b,squareRoot(temp)),multiplication(2,a));
            }
            return result;
        }
    }
}
