package dvdkruk.calculator.rpn;


public class RpnCalculatorCLI {

    public static void main(String[] args) {
        RpnCalculator calculator = new RpnCalculator();
        boolean exit = false;

        System.out.println("RPN Calculator");
        do {
            String input = System.console().readLine().trim();
            if (input.length() == 0) {
                System.out.println("Supported operators: +, -, *, /, sqrt, undo, clear");
            } else if (input.equals("exit")) {
                exit = true;
            } else {
                System.out.println(calculator.parse(input.split(" ")));
            }
        } while (!exit);
    }
}
