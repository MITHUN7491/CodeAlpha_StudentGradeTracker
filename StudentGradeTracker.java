//MITHUN M  
//java project on STUDENT GRADE TRACKER
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Student {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class StudentGradeTracker extends JFrame {
    private final ArrayList<Student> students = new ArrayList<>();
    private final JTextArea displayArea = new JTextArea();
    private final JTextField nameField = new JTextField(15);
    private final JTextField scoreField = new JTextField(5);

    public StudentGradeTracker() {
        setTitle("Student Grade Tracker");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 255));

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(200, 220, 255));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Score:"));
        inputPanel.add(scoreField);

        JButton addButton = new JButton("Add Student");
        addButton.setBackground(new Color(100, 150, 255));
        addButton.setForeground(Color.WHITE);

        addButton.addActionListener(e -> addStudent());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton summaryButton = new JButton("Show Summary Report");
        summaryButton.setBackground(new Color(0, 120, 215));
        summaryButton.setForeground(Color.WHITE);

        summaryButton.addActionListener(e -> showSummary());
        add(summaryButton, BorderLayout.SOUTH);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String scoreText = scoreField.getText().trim();

        if (name.isEmpty() || scoreText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and score.");
            return;
        }

        try {
            int score = Integer.parseInt(scoreText);
            if (score < 0 || score > 100) {
                JOptionPane.showMessageDialog(this, "Score must be between 0 and 100.");
                return;
            }

            students.add(new Student(name, score));
            displayArea.append("Added: " + name + " - Score: " + score + "\n");
            nameField.setText("");
            scoreField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for score.");
        }
    }

    private void showSummary() {
        if (students.isEmpty()) {
            displayArea.setText("No student data available.\n");
            return;
        }

        int total = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
        String topStudent = "", lowStudent = "";

        for (Student s : students) {
            total += s.score;
            if (s.score > highest) {
                highest = s.score;
                topStudent = s.name;
            }
            if (s.score < lowest) {
                lowest = s.score;
                lowStudent = s.name;
            }
        }

        double average = (double) total / students.size();

        displayArea.append("\n--- Summary Report ---\n");
        displayArea.append("Total Students: " + students.size() + "\n");
        displayArea.append("Average Score: " + String.format("%.2f", average) + "\n");
        displayArea.append("Highest Score: " + highest + " by " + topStudent + "\n");
        displayArea.append("Lowest Score: " + lowest + " by " + lowStudent + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeTracker tracker = new StudentGradeTracker();
            tracker.setVisible(true);
        });
    }
}
