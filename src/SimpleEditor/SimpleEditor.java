package SimpleEditor;


import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SimpleEditor extends JFrame {
    SimpleEditorFileManager fileEdit;
    private JLabel label;
    private JTextArea text;
    private JMenuBar bar;
    private JMenu menu;
    private JMenuItem Open, Save, Cancel, Exit;



    public SimpleEditor () {
        setTitle("Текстовый редактор");
        setSize(480,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container root = getContentPane();
        root.setLayout(new GridLayout(1,1));


        label = new JLabel();
        text = new JTextArea();
        bar = new JMenuBar();
        setJMenuBar(bar);
        menu = new  JMenu("File");
        bar.add(menu);
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        Cancel = new JMenuItem("Cancel");
        Exit = new JMenuItem("Exit");

        root.add(text, BorderLayout.CENTER);

        menu.add(Open);
        menu.add(Save);
        menu.add(Cancel);
        menu.add(Exit);

        Open.addActionListener(e -> ActionOpen());
        Save.addActionListener(e -> ActionSave());
        Cancel.addActionListener(e -> ActionCancel());
        Exit.addActionListener(e -> System.exit(0));

        setVisible(true);

    }

    public void ActionOpen() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = chooser.showOpenDialog(this);
        if(res == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            SimpleEditorFileManager manager = new SimpleEditorFileManager(file.getAbsolutePath());
            try {
                if( manager.validateFile() == true){
                    this.fileEdit = manager;
                    text.setText(fileEdit.readFile());
                }
            } catch (Exception ex) {
                System.out.println("Выберите файл с расширением txt");
            }
        }
    }

    public void ActionSave() {
        fileEdit.saveFile(text.getText());
    }
    public void ActionCancel() {
        fileEdit.file = null;
        text.setText(null);
    }

}
