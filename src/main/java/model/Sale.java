package model;

import exceptions.InsufficientSizeMatrix;

import java.util.ArrayList;
import java.util.Arrays;

public class Sale {

    private int[][] A;
    private double[][] B;

    public Sale(int countSellers, int countProducts) {
        this.A = new int[countSellers][countProducts];
        this.B = new double[countProducts][2];
    }

    public void putA(int[][] arr1) throws InsufficientSizeMatrix {
        if (arr1.length < A.length)
            throw new InsufficientSizeMatrix("первый переданный массив меньше требуемого");
        for (int i = 0; i < A.length; i++) {
            A[i] = Arrays.copyOf(arr1[i], A[i].length);
        }
    }

    public void putB(double[][] arr2) throws InsufficientSizeMatrix {
        if (arr2.length < B.length)
            throw new InsufficientSizeMatrix("второй переданный массив меньше требуемого");
        for (int i = 0; i < B.length; i++) {
            B[i] = Arrays.copyOf(arr2[i], B[i].length);
        }
    }

    public double[][] composition() {
        double[][] res = new double[A.length][2];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }

    public int[] cashMaxMin() {
        double[][] com = composition();
        int[] res = new int[2];
        double min = Double.MAX_VALUE;
        int indexMin = 0;
        double max = Double.MIN_VALUE;
        int indexMax = 0;
        for (int i = 0; i < com.length; i++) {
            if (com[i][0] < min){
                min = com[i][0];
                indexMin = i;
            }
            if (com[i][0] > max){
                max = com[i][0];
                indexMax = i;
            }
        }
        res[0] = indexMax + 1;
        res[1] = indexMin + 1;
        return res;
    }

    public int[] commissionMaxMin() {
        double[][] com = composition();
        int[] res = new int[2];
        double min = Double.MAX_VALUE;
        int indexMin = 0;
        double max = Double.MIN_VALUE;
        int indexMax = 0;
        for (int i = 0; i < com.length; i++) {
            if (com[i][1] < min){
                min = com[i][1];
                indexMin = i;
            }
            if (com[i][1] > max){
                max = com[i][1];
                indexMax = i;
            }
        }
        res[0] = indexMax + 1;
        res[1] = indexMin + 1;
        return res;
    }

    public double totalCash() {
        double[][] com = composition();
        double res = 0;
        for (int i = 0; i < com.length; i++) {
            res += com[i][0];
        }
        return res;
    }

    public double totalProfit() {
        double[][] com = composition();
        double res = 0;
        for (int i = 0; i < com.length; i++) {
            res += com[i][1];
        }
        return res;
    }

    public double totalCashProfit() {
        return totalCash() + totalProfit();
    }

    public Integer[] productNull(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int j = 0; j < A[0].length; j++) {
            boolean hasAll = true;
            for (int i = 0; i < A.length; i++) {
                if (A[i][j]!=0){
                    hasAll = false;
                }
            }
            if (hasAll)
                res.add(j+1);
        }
        return res.toArray(new Integer[0]);
    }

    public Integer[] minPrice(){
        double minPrice = Double.MAX_VALUE;
        for (int i = 0; i < B.length; i++) {
            if (B[i][0]<minPrice){
                minPrice = B[i][0];
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < B.length; i++) {
            if (minPrice == B[i][0]){
                res.add(i+1);
            }
        }
        return res.toArray(new Integer[0]);
    }

   public Integer[] maxProduct(){
        int maxProduct = 0;
        for (int i = 0; i < A.length; i++) {
            int count = 0;
            for (int j = 0; j < A[0].length; j++) {
                count += A[i][j];
            }
            if (maxProduct < count) {
                maxProduct = count;
            }
        }
       ArrayList<Integer> res = new ArrayList<Integer>();
       for (int i = 0; i < A.length; i++) {
           int count = 0;
           for (int j = 0; j < A[0].length; j++) {
               count += A[i][j];
           }
           if (count == maxProduct)
               res.add(i+1);
       }
       return res.toArray(new Integer[0]);
   }

    //Продавец 1 продает товар 1 в количестве 5 ед по стоимости 1.20 с комиссионными 0.50
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0){
                    res += String.format("Продавец %d продает товар %d в количестве %d ед по стоимости %.2f с " +
                            "комиссионными %.2f\n", i+1, j+1, A[i][j], B[j][0], B[j][1]);
                }
            }
        }
        return res;
    }
}
