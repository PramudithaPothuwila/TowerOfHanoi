package com.company;

public class AutoSolveAlgo
{

    public static void solve(int n, char fromRod, char toRod, char auxRod)
    {
        if(n == 1)
        {
            System.out.printf("\nDisk 1 from %c to %c",fromRod, toRod);
            return;
        }
        solve(n-1, fromRod, auxRod, toRod);
        System.out.printf("\nDisk %d from %c to %c", n, fromRod, toRod);
        solve(n-1, auxRod, toRod, fromRod);
    }
}
