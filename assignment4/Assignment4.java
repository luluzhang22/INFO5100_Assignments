
public class Assignment4 {

    public static void main(String[] args){
        System.out.println("------4.1 ------");
        System.out.println(formatKey("2-4A0r7-4k",4));
        System.out.println(formatKey("2-4A0r7-4k",3));

        System.out.println("------4.2 ------");
        Scissors s = new Scissors(5);
        Paper p = new Paper(7);
        Rock r = new Rock(15);

        System.out.println(s.fight(p) + " , " + p.fight(s));
        System.out.println(p.fight(r) + " , " + r.fight(p));
        System.out.println(r.fight(s) + " , " + s.fight(r));

        System.out.println("------4.3 ------");
        IpAddress ip = new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));

        System.out.println("------4.4 ------");
        Course info5100 = new Course("java");
        String[] names = {"Lulu","Cass","Peter","Lily","Lucy","Gina","Ketty","David","Luna","Kevin","John"};
        int id = 1;
        for(String name : names){
            Student student = new Student(name,id);
            info5100.registerStudent(student);
            id++;
        }
        Student[] students = info5100.getStudents();
        System.out.println(info5100.getTitle()+" has "+info5100.getNumberOfStudent()+" students registered in it->[name,id]:");
        for(Student stu : students){
            System.out.print("["+stu.getName()+","+stu.getId()+"]  ");
        }
        System.out.println();

        System.out.println("------4.5 ------");
        System.out.println(intToRoman(3999));

        System.out.println("------extra ------");
        System.out.println("the median of [1,3] and [2] is: "+findMedianSortedArrays(new int[] {1,3},new int[] {2}));
        System.out.println("the median of [1,2] and [3,4] is: "+findMedianSortedArrays(new int[] {1,2},new int[] {3,4}));
    }

    static String formatKey(String s, int k){ // score 2
        char[] arr = s.toCharArray();
        String result ="";
        int part = k;
        for(int i = arr.length-1;i>=0;i--){
            char c = arr[i];
            if(c == '-')
                continue;
            if(part == 0){
                result = '-'+result;
                part = k;
            }
            if(c>=97&&c<=122)
                c -=32;
            result = c +result;
            part--;
        }
        return result;
    }

    static String intToRoman(int num){ // score 2
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000]+C[(num%1000)/100]+X[(num%100)/10]+I[num%10];
    }

    static double findMedianSortedArrays(int[] nums1, int[] nums2){ // extra credit 2
        if(nums1.length>nums2.length){
            int[] t = nums1;
            nums1 = nums2;
            nums2 = t;
        }
        int iMin = 0;
        int iMax = nums1.length;
        int i, j, mid = (nums1.length+nums2.length+1)/2;
        while(iMin<=iMax){
            i = (iMin+iMax)/2;
            j = mid-i;
            if(i>iMin&&nums1[i-1]>nums2[j]){
                iMax--;
                continue;
            }else if(i<iMax&&nums1[i]<nums2[j-1]){
                iMin++;
                continue;
            }else{
                double maxLeft;
                if(i==0)
                    maxLeft = nums2[j-1];
                else if(j==0)
                    maxLeft=nums1[i-1];
                else
                    maxLeft=Math.max(nums1[i-1],nums2[j-1]);
                if((nums1.length+nums2.length)%2!=0)
                    return maxLeft;

                double minRight;
                if(i==nums1.length)
                    minRight = nums2[j];
                else if(j==nums2.length)
                    minRight = nums1[i];
                else
                    minRight = Math.min(nums1[i],nums2[j]);
                return (maxLeft+minRight)/2;
            }
        }
        return -1;
        /**int length = nums1.length + nums2.length;
        int a = length%2;
        int mid = (length+1)/2;
        int i = 0;
        int j = 0;
        double result = -1;
        while(i < nums1.length && j < nums2.length && i+j < mid){
            if(nums1[i]<nums2[j]){
                result = nums1[i];
                i ++;
            }
            else {
                result = nums2[j];
                j++;
            }
        }

        if (i+j<mid){
            if (i<nums1.length){
                result = nums1[mid-j-1];
                i = mid-j;
            }else{
                result = nums2[mid-i-1];
                j = mid-i;
            }
        }
        if( a == 0){
            if(i<nums1.length&&j<nums2.length){
                if(nums1[i]<nums2[j])
                    result = (result + nums1[i])/2;
                else
                    result = (result + nums2[j])/2;
            }else if(i<nums1.length){
                result = (result + nums1[i])/2;
            }else
                result = (result + nums2[j])/2;
        }
        return result;**/
    }
}
