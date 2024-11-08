package org.waldy;

import java.io.*;
import java.net.Socket;

public class ImplementServerB2 implements Runnable {
    private Socket m_soc;

    public ImplementServerB2(Socket soc) {
        m_soc = soc;
    }

    @Override
    public void run() {
        RunDataStream();
    }

    public void RunDataStream() {
        try {
            InputStream in = m_soc.getInputStream();
            DataInputStream datain = new DataInputStream(in);
            String strkq = GiaiPhuongTrinhBacHai(datain.readDouble(), datain.readDouble(), datain.readDouble());

            OutputStream out = m_soc.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeUTF(strkq);
            m_soc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String GiaiPhuongTrinhBacHai(double a, double b, double c) {
        String kq = null;

        double delta = (b * b) - (4 * a * c);

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    kq = "Phương trình có vô số nghiệm";
                } else {
                    kq = "Phương trình vô nghiệm";
                }
            } else {
                double x = (-c) / b;
                String sFormat = String.format("%10.2f", x);
                kq = "Phương trình có nghiệm duy nhất: " + sFormat;
            }
        } else if (delta < 0) {
            kq = "Phương trình vô nghiệm";
        } else if (delta == 0) {
            kq = "Phương trình có nghiệm kép x1 = x2 = " + ((-b)/(2*a));
        }
        else if(delta > 0){
            double x1 = ((-b)+(Math.sqrt(delta)))/2*a;
            double x2 = ((-b)-(Math.sqrt(delta)))/2*a;
            kq = "Phương trình có 2 nghiệm, x1 = " + x1 + " x2 = " + x2;
        }

        return kq;
    }
}