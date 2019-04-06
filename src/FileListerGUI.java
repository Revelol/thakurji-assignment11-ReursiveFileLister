import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;


public class FileListerGUI extends JFrame{
    JPanel main, top, middle, bottom;
    JLabel label;
    JButton startButton, quitBtn;
    JTextArea fileList;
    JScrollPane scrollPane;
    StringBuffer sb;
    public FileListerGUI()
    {
        super("File Lister");
        // configure the GUI
        main = new JPanel();

        createTopPanel();
        createMiddlePanel();
        createBottomPanel();


        main.setLayout(new BorderLayout());
        main.add(top,BorderLayout.NORTH);
        main.add(scrollPane,BorderLayout.CENTER);
        main.add(bottom,BorderLayout.SOUTH);

        // And add Main to the JFrame
        add(main);

        setSize(600, 600);
        //frame.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private void createTopPanel()
    {
        // Top panel

        top = new JPanel();
        top.setLayout(new BorderLayout());
        JPanel p1 = new JPanel();



        label = new JLabel();
        label.setText("File Lister");
        label.setFont(new Font("Helvetica", Font.PLAIN, 36));
        label.setForeground(Color.orange);
        p1.add(label);
        top.add(p1, BorderLayout.NORTH);
    }
    private void createMiddlePanel()
    {
        fileList = new JTextArea();
        scrollPane = new JScrollPane(fileList);
    }
    private void createBottomPanel()
    {
        //Control Panel

        bottom = new JPanel();
        startButton = new JButton("Choose Directory");
        // clicker = new ClickListener();

        startButton.addActionListener((ActionEvent ae) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("chooseDirectory");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File directory = chooser.getSelectedFile();
                sb = new StringBuffer();
                listFiles(directory);
                fileList.setText(sb.toString());
            } else {
                System.out.println("No Selection ");
            }
        });
        startButton.setFont(new Font("Arial", Font.PLAIN, 14));
        startButton.setForeground(Color.red);
        bottom.add(startButton);

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        quitBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        quitBtn.setForeground(Color.red);
        bottom.add(quitBtn);
    }


    private void listFiles(File aFile) {


        if (aFile.isDirectory()) {
            for (File child : aFile.listFiles()) {
                listFiles(child);
            }
        } else {
            String fileName = aFile.toString();
            sb.append(fileName+"\n");
        }
    }

}
