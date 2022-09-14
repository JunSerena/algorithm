package com.company.src.dynamicProgramming;

/**
 * 斐波那契数列数列
 * f0=0,f1=1, fn=f(n-1)+f(n-2)
 */
public class FibonacciSequence {

    // 普通递归
    public int fib(int n){
        if (n==0)
            return 0;
        if ( n==1)
            return 1;
        return fib(n-1)+fib(n-2);
    }

    //记忆化搜索-自上而下的解决问题
    int[] memory; //保存fn的值, 大小为n+1个
    public int fib2(int n){
        if (n==0)
            return 0;
        if ( n==1)
            return 1;

        if (memory[n]==0)
            memory[n] = fib2(n-1)+fib2(n-2);
        return memory[n];
    }

    //动态规划-自下而上的解决问题
    // 先解决小数据量的问题，再解决大数据量
    public int fib3(int n){
        int[] memo = new int[n+1];
        memo[0]=0;
        memo[1]=1;
        for (int i = 2; i <=n; i++) {
            memo[i]=memo[i-1]+memo[i-2];
        }
        return memo[n];
    }


}
