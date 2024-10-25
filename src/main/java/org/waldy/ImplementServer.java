package org.waldy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ImplementServer implements Runnable{
    private Socket m_soc;
    public ImplementServer(Socket soc){
        m_soc = soc;
    }

    @Override
    public void run(){
        RunDataStream();
    }

    public void RunDataStream(){
        try{
            InputStream in = m_soc.getInputStream();
            DataInputStream datain = new DataInputStream(in);
            String strkq = GiaiPhuongTrinhBacNhat(datain.readDouble(), datain.readDouble());

            OutputStream out = m_soc.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeUTF(strkq);
            m_soc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String GiaiPhuongTrinhBacNhat(double a, double b){
        String kq = null;

        if(a == 0) {
            if(b == 0){
                kq = "Phuong trinh co vo so nghiem";
            }
            else{
                kq = "Phuong trinh vo nghiem";
            }
        }else {
            double x = (-b) / a;
            String sFormat = String.format("%10.2f", x);
            kq = "Phuong trinh co nghiem: " + sFormat;
        }
        return kq;
    }
}