package dvdkruk.calculator.rpn;


public class RpnCalculatorCLI {

    public static void main(String[] args) {
        RpnCalculator calculator = new RpnCalculator();
        boolean exit = false;

        System.out.println("RPN Calculator");
        System.out.println("Supported actions: " + calculator.getSupportedOperators());

        do {
            String input = System.console().readLine().trim();
            if (input.length() == 0) {
                System.out.println("empty input");
            } else if (input.equals("exit")) {
                exit = true;
            } else {
                String warningMsg = calculator.parse(input.split(" "));
                if (!warningMsg.isEmpty()) {
                    System.out.println(warningMsg);
                }
                System.out.println("stack: " + calculator.getStackAsString());
            }
        } while (!exit);
    }
}
