package BookDB.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月22日19时44分
 */
public class SystemFrame extends JFrame {
    static int RTflag = 1;
    static int BKflag = 1;
    static int RDflag = 1;
    static int LDflag = 1;
    /**
     * 分四种情况讨论
     * 1.读者类别管理
     * 2.图书管理
     * 3.读者管理
     * 4.借书还书管理
     * 5.退出
     */
    JButton ReaderTypeButton,BookButton,ReaderButton,BorrowButton;
    JLabel TagInfo;


    public SystemFrame(String TagName){
        /**
         * JFrame
         */
        super(TagName);
        setBounds(100,100,600,400);
        this.setLayout(null);

        /**
         * JLabel
         */
        TagInfo = new JLabel("图书管理系统");
        TagInfo.setFont(new Font("宋体",Font.PLAIN,25));
        TagInfo.setBounds(225,20,150,40);
        this.add(TagInfo);

        /**
         * JButton
         */
        ReaderTypeButton = new JButton("读者类别管理");
        BookButton = new JButton("图书管理");
        ReaderButton = new JButton("读者管理");
        BorrowButton = new JButton("借书还书管理");
        ReaderTypeButton.setBackground(Color.WHITE);
        BookButton.setBackground(Color.WHITE);
        ReaderButton.setBackground(Color.WHITE);
        BorrowButton.setBackground(Color.WHITE);
        ReaderTypeButton.setBounds(new Rectangle(225,90,150,40));
        BookButton.setBounds(new Rectangle(225,150,150,40));
        ReaderButton.setBounds(new Rectangle(225,210,150,40));
        BorrowButton.setBounds(new Rectangle(225,270,150,40));
        ReaderTypeButton.addActionListener(e -> {
            if(e.getSource() == ReaderTypeButton) {
                if (SystemFrame.RTflag == 1) {
                    SystemFrame.RTflag--;
                    new ReaderTypeFrame("读者类型管理").addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            SystemFrame.RTflag++;
                        }
                    });
                }
            }
        });
        BookButton.addActionListener(e -> {
            if(e.getSource() == BookButton){
                if (SystemFrame.BKflag == 1) {
                    SystemFrame.BKflag--;
                    new BookFrame("图书管理").addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            SystemFrame.BKflag++;
                        }
                    });
                }
            }
        });
        ReaderButton.addActionListener(e -> {
            if(e.getSource() == ReaderButton){
                if (SystemFrame.RDflag == 1) {
                    SystemFrame.RDflag--;
                    new ReaderFrame("读者管理").addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            SystemFrame.RDflag++;
                        }
                    });
                }
            }
        });
        BorrowButton.addActionListener(e -> {
            if(e.getSource() == BorrowButton){
                if (SystemFrame.LDflag == 1) {
                    SystemFrame.LDflag--;
                    new BorrowFrame("图书借还管理").addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            SystemFrame.LDflag++;
                        }
                    });
                }
            }
        });
        this.add(ReaderTypeButton);
        this.add(BookButton);
        this.add(ReaderButton);
        this.add(BorrowButton);




        this.setResizable(false);
        this.setVisible(true);
        this.validate();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
