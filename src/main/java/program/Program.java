package program;

import exceptions.InsufficientSizeMatrix;
import model.Sale;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {

        Sale market = new Sale(3, 4);

        int[][] arr1 = {{5, 2, 0, 10}, {3, 5, 2, 5}, {20, 0, 0, 0}};
        double[][] arr2 = {{1.2, 0.5}, {2.8, 0.4}, {5, 1}, {1, 1.5}};


        try {
            market.putA(arr1);
            market.putB(arr2);

            System.out.println(market.toString());

            double[][] composition = market.composition();
            System.out.println(Arrays.deepToString(composition));

            int[] cash = market.cashMaxMin();
            System.out.println(Arrays.toString(cash));

            int[] profit = market.commissionMaxMin();
            System.out.println(Arrays.toString(profit));

            double totalCash = market.totalCash();
            System.out.printf("%.2f\n",totalCash);

            double totalProfit = market.totalProfit();
            System.out.printf("%.2f\n",totalProfit);

            double totalCashProfit = market.totalCashProfit();
            System.out.printf("%.2f\n",totalCashProfit);

            Integer[] productNull = market.productNull();
            System.out.println(Arrays.toString(productNull));

            Integer[] minPrice = market.minPrice();
            System.out.println(Arrays.toString(minPrice));

            Integer[] maxProduct = market.maxProduct();
            System.out.println(Arrays.toString(maxProduct));

        } catch (InsufficientSizeMatrix ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
        }


    }
}
