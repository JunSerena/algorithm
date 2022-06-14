package com.company.src.array;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidPalindrome125 {

    public static void main(String[] args) {
        System.out.println(isPalindrome("0P"));

    }

    public static boolean isPalindrome(String s) {

        int left =0, right =s.length()-1;

        while (left<right){

            int value1 = Character.toLowerCase(s.charAt(left));
            int value2 = Character.toLowerCase(s.charAt(right));

            if (check(value1) && check(value2)){ // 如果都是字母/数字
                if (value1==value2 ){
                    left++;
                    right--;
                }else
                    return false;
            }else if ( !check(value1)){
                left++;
            }else if( !check(value2)){
                right--;
            }
        }
        return true;

    }

    // 用ASCII码校验是否是小写字母 a-z 或者数字
    public static boolean check(int val){
        return (val >= 48 && val <= 57) || (val >= 97 && val <= 122);
    }

    // 或者直接用char
    public static boolean isStringOrDigtal(char c){
        return (c>='a'&& c<='z')||(c>='0'&& c<='9');
    }

}
