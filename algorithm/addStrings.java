/*
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/
public class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int sum = 0, carry = 0;
        while(len1 >= 0 || len2 >= 0){
            int first = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int second = len2 >= 0 ? num2.charAt(len2) - '0' : 0;
            sum = carry + first + second;
            sb.append(sum % 10);
            carry = sum / 10;
            len1--;
            len2--;
        }
        //to satisfy no leading zero
        if(carry == 1) sb.append(carry);
        return sb.reverse().toString();
    }
}
