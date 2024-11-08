package org.waldy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClientB2 extends JFrame {
    private static final long serialVersionUID = 1;
    private JButton btnProc, btnClear, btnExit;
    private JTextField txta, txtb, txtc, txtkq;
    public ClientB2(){CreateUI();}

    private void CreateUI() {
        setLayout(new BorderLayout());
        JPanel pnNorth = new JPanel();
        pnNorth.setBackground(Color.cyan);

        JLabel lblNorth = new JLabel("Giải phương trình bậc hai ax^2+bx+c=0");
        lblNorth.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
        pnNorth.add(lblNorth);

        JPanel pnCenter = new JPanel();

        JPanel pnInfo = new JPanel();
        pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.X_AXIS));
        pnCenter.add(pnInfo);

        JLabel lbla = new JLabel("Hệ số A: ");
        pnInfo.add(lbla);
        txta = new JTextField(15);
        pnInfo.add(txta);

        JLabel lblb = new JLabel("Hệ số B: ");
        pnInfo.add(lblb);
        txtb = new JTextField(15);
        pnInfo.add(txtb);

        JLabel lblc = new JLabel("Hệ số C: ");
        pnInfo.add(lblc);
        txtc = new JTextField(15);
        pnInfo.add(txtc);

        btnProc = new JButton("Process");
        btnProc.addActionListener(new CMyEvent());

        btnClear = new JButton("Clear");
        btnClear.addActionListener(new CMyEvent());

        btnExit = new JButton("Exit");
        btnExit.addActionListener(new CMyEvent());

        JPanel pnSouth = new JPanel();
        JLabel lblkq = new JLabel("Kết quả: ");
        pnCenter.add(lblkq);
        txtkq = new JTextField(50);
        pnCenter.add(txtkq);
        txtkq.setEditable(false);
        pnCenter.add(btnProc);
        pnCenter.add(btnClear);
        pnCenter.add(btnExit);

        Container con = getContentPane();
        con.add(pnNorth, BorderLayout.NORTH);
        con.add(pnCenter, BorderLayout.CENTER);
        con.add(pnSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args){
        ClientB2 clientB2 = new ClientB2();
        clientB2.setTitle("Chương trình giải phương trình bậc hai");
        clientB2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientB2.setSize(700,500);
        clientB2.setVisible(true);
    }

    private class CMyEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0){
            Object o = arg0.getSource();
            if(o.equals(btnProc)){
                processInformation();
            }
            else if(o.equals(btnClear)){
                ClearText();
            }
            else if(o.equals(btnExit)){
                Exit();
            }
        }

        private void Exit(){System.exit(0);}

        private void ClearText(){
            txta.setText("");
            txtb.setText("");
            txtc.setText("");
        }

        private void processInformation(){DataStream();}

        private void DataStream(){
            try {
                Socket soc = new Socket("localhost", 8081);
                OutputStream out = soc.getOutputStream();
                DataOutputStream dataout = new DataOutputStream(out);
                dataout.writeDouble(Double.parseDouble(txta.getText()));
                dataout.writeDouble(Double.parseDouble(txtb.getText()));
                dataout.writeDouble(Double.parseDouble(txtc.getText()));

                InputStream in = soc.getInputStream();
                DataInputStream datain = new DataInputStream(in);
                txtkq.setText(datain.readUTF());
                soc.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}