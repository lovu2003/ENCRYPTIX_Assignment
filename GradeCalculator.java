import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends JFrame {
    private JTextField[] marksFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public GradeCalculator(int numberOfSubjects) {
        super("Student Grade Calculator");
        setupUI(numberOfSubjects);
    }

    private void setupUI(int numberOfSubjects) {
        setLayout(new GridLayout(numberOfSubjects + 4, 2));

        marksFields = new JTextField[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            add(new JLabel("Enter marks for Subject " + (i + 1) + ":"));
            marksFields[i] = new JTextField();
            add(marksFields[i]);
        }

        calculateButton = new JButton("Calculate");
        add(calculateButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        add(totalMarksLabel);

        averagePercentageLabel = new JLabel("Average Percentage: ");
        add(averagePercentageLabel);

        gradeLabel = new JLabel("Grade: ");
        add(gradeLabel);

        calculateButton.addActionListener(new CalculateButtonListener());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, numberOfSubjects * 50 + 100);
        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int totalMarks = 0;
                int numberOfSubjects = marksFields.length;

                for (JTextField field : marksFields) {
                    int marks = Integer.parseInt(field.getText());
                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(null, "Please enter valid marks between 0 and 100.");
                        return;
                    }
                    totalMarks += marks;
                }

                double averagePercentage = (double) totalMarks / numberOfSubjects;
                String grade = calculateGrade(averagePercentage);

                totalMarksLabel.setText("Total Marks: " + totalMarks);
                averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
                gradeLabel.setText("Grade: " + grade);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for marks.");
            }
        }

        private String calculateGrade(double averagePercentage) {
            if (averagePercentage >= 90) {
                return "A";
            } else if (averagePercentage >= 80) {
                return "B";
            } else if (averagePercentage >= 70) {
                return "C";
            } else if (averagePercentage >= 60) {
                return "D";
            } else {
                return "F";
            }
        }
    }

    public static void main(String[] args) {
        int numberOfSubjects = 5; 
        new GradeCalculator(numberOfSubjects);
    }
}
