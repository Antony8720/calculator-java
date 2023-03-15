import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String strIn = scan.nextLine().trim();
        scan.close();
        String[] inputArr = strIn.split(" ");
        if (inputArr.length != 3) {
            throw new IOException("Incorrect input data");
        }

        String symbol = inputArr[1];
        HashMap <String, Boolean> symbols = new HashMap<>();
        symbols.put("+", true);
        symbols.put("-", true);
        symbols.put("*", true);
        symbols.put("/", true);
        boolean ok = symbols.get(symbol);
        if (!ok){
            throw new IOException("Incorrect input data");
        }
        int num1 = 0;
        int num2 = 0;
        try {
            num1 = Integer.parseInt(inputArr[0]);
            num2 = Integer.parseInt(inputArr[2]);
        }
        catch (NumberFormatException e) {
            num1 = romToInt(inputArr[0]);
            num2 = romToInt(inputArr[2]);
            int res = calculation(num1, num2, symbol);
            if (res < 1) {
                throw new Exception("The result can only be positive Roman numbers");
            }
            String resRom = intToRom(res);
            System.out.println(resRom);
            System.exit(0);
        }
        if (num1 >= 0 && num1 < 10 && num2 >= 0 && num2 <= 10) {
            int res = calculation(num1, num2, symbol);
            System.out.println(res);
            System.exit(0);
        }
        throw new Exception("Incorrect input data");

    }
    public static int calculation(int num1, int num2, String symbol) throws Exception {
        int res = 0;
        switch (symbol){
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                try {
                    res = num1 / num2;
                }
                catch (ArithmeticException e){
                    System.out.println("Zero Division Error: " + e.getMessage());
                }
            default:
                throw new Exception("Incorrect input data");
        }
        return res;
    }

    public static int romToInt(String num) throws Exception {
        HashMap<String, Integer> mapRom = new HashMap<>();
        mapRom.put("I", 1);
        mapRom.put("II", 2);
        mapRom.put("III", 3);
        mapRom.put("IV", 4);
        mapRom.put("V", 5);
        mapRom.put("VI", 6);
        mapRom.put("VII", 7);
        mapRom.put("VIII", 8);
        mapRom.put("IX", 9);
        mapRom.put("X", 10);
        if (mapRom.get(num) != null){
            return mapRom.get(num);
        }
        throw new Exception("Incorrect input data");
    }
    public static String intToRom(int num) {
        String res = "";
        String[] arrRom = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arrInt = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        for(int i = 0; num > 0; i++){
            while (arrInt[i] <= num){
                res += arrRom[i];
                num -= arrInt[i];
            }
        }
        return res;
    }
}