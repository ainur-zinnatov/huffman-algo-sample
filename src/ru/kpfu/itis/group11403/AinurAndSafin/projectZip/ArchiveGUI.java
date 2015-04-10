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
        label_1.setBounds(40, 30, 200, 30);
        panel.add(label_1);

        final JButton InputButton = new JButton("открыть ...");
        InputButton.setVerticalTextPosition(AbstractButton.CENTER);
        InputButton.setHorizontalTextPosition(AbstractButton.LEADING);
        InputButton.setBounds(170, 20, 150, 50);
        panel.add(InputButton);


        final JLabel form = new JLabel("Выбирете формат файла:");
        form.setBounds(100, 110, 300, 30);
        panel.add(form);


        final JLabel fileName = new JLabel();
        fileName.setBounds(15,90,300,20);
        panel.add(fileName);

        final JRadioButton radioHuf = new JRadioButton(".HUF");
        radioHuf.setBounds(90, 140, 100, 30);
        panel.add(radioHuf);

        final JRadioButton radioItis = new JRadioButton(".ITIS");
        radioItis.setBounds(200, 140, 100, 30);
        radioItis.setSelected(true);
        panel.add(radioItis);

        final JButton archive = new JButton("COMPRESS");
        archive.setVerticalTextPosition(AbstractButton.CENTER);
        archive.setHorizontalTextPosition(AbstractButton.WHEN_FOCUSED);
        archive.setBounds(70, 190, 220, 50); //check
        archive.setEnabled(false);
        panel.add(archive);

        final JLabel progress = new JLabel("Статус : ожидание комманды  ...");
        progress.setBounds(75, 250, 200, 30);
        panel.add(progress);

        final JLabel about = new JLabel("Программа Zipper");
        about.setBounds(110,320,200,30);
        panel.add(about);

        final JLabel rights = new JLabel("All rights reserved.");
        rights.setBounds(110, 340, 200, 30);
        panel.add(rights);


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

                fileName.setText(file[0].getName());


                archive.setEnabled(true);

            }
        });

        Zipper zipper = new Zipper();

        archive.addActionListener(

                e -> {

                    progress.setText("Статус : compressing ...");

                    String path = file[0].getPath();

                    path = path.substring(0,path.lastIndexOf('.'));

                    File result = new File(path+archive_format[0]); // файл сжатый

                    //расширение
                    String ext =file[0].getPath().substring(file[0].getPath().lastIndexOf('.'),file[0].getPath().length());

                    File output = new File(path + "_dec" + ext); // файл расжатый

                    zipper.compress(file[0], result);

                    zipper.decompress(result, output);

                    progress.setText(" Архивация завершена! ");
                });


        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(350, 400));

        frame.setResizable(false);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public void start() {
        javax.swing.SwingUtilities.invokeLater(ArchiveGUI::createGUI);
    }
}
