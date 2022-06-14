package com.company.src.array;

/**
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 *
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 *
 * 示例 1：
 *
 * 输入：s = "hello"
 * 输出："holle"
 * 示例 2：
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-vowels-of-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseVowels345 {

    // 对撞指针
    public String reverseVowels(String s) {
        int left =0, right = s.length()-1;

        //将字符串转为自符数组
        char[] arr = s.toCharArray();

        while (left<right){
            char l = arr[left];
            char r = arr[right];

            if (isVowel( l ) && isVowel( r )){
                // 交换
                arr[left] = r;
                arr[right] = l;
                left++;
                right--;
            }else if (!isVowel( l )){
                left++;
            }else if (!isVowel( r )){
                right--;
            }
        }

        return new String(arr);
    }

    public static boolean isVowel(char c){
        char val = Character.toLowerCase(c);
        return val == 'a' || val == 'e' || val == 'i' || val == 'o' || val == 'u';
    }
}
