import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATM extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JLabel balanceLabel;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;

    public ATM(BankAccount account) {
        super("ATM Machine");
        this.account = account;
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(5, 1));

        balanceLabel = new JLabel("Balance: $0.00");
        updateBalanceLabel();
        add(balanceLabel);

        amountField = new JTextField();
        add(amountField);

        withdrawButton = new JButton("Withdraw");
        add(withdrawButton);

        depositButton = new JButton("Deposit");
        add(depositButton);

        checkBalanceButton = new JButton("Check Balance");
        add(checkBalanceButton);

        withdrawButton.addActionListener(new WithdrawButtonListener());
        depositButton.addActionListener(new DepositButtonListener());
        checkBalanceButton.addActionListener(new CheckBalanceButtonListener());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
    }

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (account.withdraw(amount)) {
                    JOptionPane.showMessageDialog(null, "Withdrawal successful.");
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance or invalid amount.");
                }
                updateBalanceLabel();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
            }
        }
    }

    private class DepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                JOptionPane.showMessageDialog(null, "Deposit successful.");
                updateBalanceLabel();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
            }
        }
    }

    private class CheckBalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateBalanceLabel();
            JOptionPane.showMessageDialog(null, "Your balance is $" + String.format("%.2f", account.getBalance()));
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.00); 
        new ATM(account);
    }
}
