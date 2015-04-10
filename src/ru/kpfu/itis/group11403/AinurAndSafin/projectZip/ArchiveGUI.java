package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ArchiveGUI {

    private static void createGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);

        final JFrame frame = new JFrame("Zipper");

        JPanel panel = new JPanel();

        panel.setLayout(null);


        final JLabel label_1 = new JLabel("Выберите файл");
        label_1.setBounds(125, 5, 100, 30);
        panel.add(label_1);


        final JLabel form = new JLabel("Формат сжатого файла:");
        form.setBounds(10, 45, 300, 30);
        panel.add(form);

        final JRadioButton radioHuf = new JRadioButton(".HUF");
        radioHuf.setBounds(10, 80, 100, 30);
        panel.add(radioHuf);

        final JRadioButton radioItis = new JRadioButton(".ITIS");
        radioItis.setBounds(10, 130, 100, 30);
        radioItis.setSelected(true);
        panel.add(radioItis);

        final JButton InputButton = new JButton("Input File");
        InputButton.setVerticalTextPosition(AbstractButton.CENTER);
        InputButton.setHorizontalTextPosition(AbstractButton.LEADING);
        InputButton.setBounds(10, 10, 100, 30);
        panel.add(InputButton);


        final JButton archive = new JButton("compress");
        archive.setVerticalTextPosition(AbstractButton.CENTER);
        archive.setHorizontalTextPosition(AbstractButton.LEADING);
        archive.setBounds(10, 170, 220, 30);
        archive.setEnabled(false);
        panel.add(archive);

        final JLabel progress = new JLabel("...");
        progress.setBounds(10, 230, 200, 30);
        panel.add(progress);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(false);
        progressBar.setBounds(10, 215, 220, 20);
        panel.add(progressBar);

        final String[] archive_format = {".itis"}; //default

        radioHuf.addActionListener(e -> {
            radioItis.setSelected(false);
            archive_format[0] = ".huf";
        });

        radioItis.addActionListener(e -> {
            radioHuf.setSelected(false);
            archive_format[0] = ".itis";
        });

        final File[] file = new File[1];

        InputButton.addActionListener(e -> {

            JFileChooser file_open = new JFileChooser();

            int ret = file_open.showDialog(null, "Выбрать файл");

            if (ret == JFileChooser.APPROVE_OPTION) {

                file[0] = file_open.getSelectedFile();

                label_1.setText(file[0].getName());

                archive.setEnabled(true);

                progress.setText("Готов!");
            }
        });

        Zipper zipper = new Zipper();

        archive.addActionListener(

                e -> {

                    progressBar.setIndeterminate(true);

                    progress.setText("...");

                    String path = file[0].getPath();

                    path = path.substring(0,path.lastIndexOf('.'));

                    File result = new File(path+archive_format[0]); // файл сжатый

                    //расширение
                    String ext =file[0].getPath().substring(file[0].getPath().lastIndexOf('.'),file[0].getPath().length());

                    File output = new File(path + "_dec" + ext); // файл расжатый

                    zipper.compress(file[0], result);

                    progressBar.setIndeterminate(true);

                    zipper.decompress(result, output);

                    progressBar.setIndeterminate(false);

                    progress.setText(" Архивация завершена! ");
                });


        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(250, 300));

        frame.setResizable(false);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public void start() {
        javax.swing.SwingUtilities.invokeLater(ArchiveGUI::createGUI);
    }
}