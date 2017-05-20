package dvdkruk.calculator.rpn.cli;


import dvdkruk.calculator.rpn.RpnCalculator;

import java.io.Console;
import java.io.PrintWriter;
import java.util.logging.Logger;

class RpnCalculatorCLI {

    private static final Logger LOG = Logger.getLogger(RpnCalculatorCLI.class.getName());

    private RpnCalculatorCLI() {
    }

    public static void main(String[] args) {
        RpnCalculator calculator = new RpnCalculator();
        Console console = System.console();
        if (console == null) {
            LOG.severe("no console");
            System.exit(1);
        }

        boolean exit = false;
        PrintWriter pWriter = console.writer();
        pWriter.println("RPN Calculator");
        pWriter.println("Supported actions: " + calculator.getSupportedOperators());

        do {
            String input = readNextInput();
            if (input == null) {
                pWriter.println("empty input");
            } else if ("exit".equals(input)) {
                exit = true;
            } else {
                String warningMsg = calculator.parse(input.split(" "));
                if (!warningMsg.isEmpty()) {
                    pWriter.println(warningMsg);
                }
                pWriter.println("stack: " + calculator.getStackAsString());
            }
        } while (!exit);
    }

    private static String readNextInput() {
        String input = System.console().readLine();
        if (input != null) {
            String trimmedInput = input.trim();
            if (trimmedInput.length() == 0) {
                return trimmedInput;
            }
        }
        return null;
    }
}
