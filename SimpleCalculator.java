import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public SimpleCalculator() {
        // Frame setup
        setTitle("Simple Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display setup
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Buttons panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            display.setText(display.getText() + command);
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    display.setText(String.valueOf(firstNumber + secondNumber));
                    break;
                case "-":
                    display.setText(String.valueOf(firstNumber - secondNumber));
                    break;
                case "*":
                    display.setText(String.valueOf(firstNumber * secondNumber));
                    break;
                case "/":
                    if (secondNumber == 0) {
                        display.setText("Error");
                    } else {
                        display.setText(String.valueOf(firstNumber / secondNumber));
                    }
                    break;
            }
        } else {
            if (!display.getText().isEmpty()) {
                firstNumber = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}
