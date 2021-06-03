import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class WindowUI {
    private JFrame frame;



    public void go(MyCurrency maxc, MyCurrency minc, Set<MyCurrency> myCurrencyTreeSet){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField max = new JTextField();
        max.setText("Max currency / Максимальный курс валюты: " + maxc.toString());
        max.setEditable(false);
        JTextField min = new JTextField();
        min.setText("Min currency / Минимальный курс валюты: " + minc.toString());
        min.setEditable(false);

        JTextArea average = new JTextArea();
        StringBuilder builder = new StringBuilder("Cреднее значение курса рубля за весь период по всем валютам:\n\n");

        for (MyCurrency m : myCurrencyTreeSet){
            StringBuilder name = new StringBuilder(m.getName());

            while (name.length() < 100) {
                name.append(" ");
            }

            builder.append("name " +  name.toString() + "value  " + m.getValue() + "\n");
        }
        average.append(builder.toString());
        average.setEditable(false);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(average);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.add(max);
        content.add(min);
        content.add(average);
        content.add(scrollPane);

        frame.getContentPane().add(content);
        frame.setSize(720,400);
        frame.setVisible(true);
    }
}
