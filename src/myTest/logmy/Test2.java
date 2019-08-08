package myTest.logmy;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Test2 {

    public static void main(String[] args) throws IOException {
        byte[] th1 = "�߳�1".getBytes();
        byte[] th2 = "�߳�2".getBytes();
        byte[] th3 = "�߳�3".getBytes();
        final int len = th1.length;

        final RandomAccessFile raf = new RandomAccessFile(
                "F:/tianchi/index.data", "rw");
        raf.seek(1);
        raf.write(th1);
        raf.seek(200);
        raf.write(th2);
        raf.seek(300);
        raf.write(th3);

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    byte[] b = new byte[len];
                    try {
                        synchronized (raf) {
                            raf.seek(1);
                            raf.read(b);
                        }

                        if ("�߳�1".equals(new String(b))) {
                            System.out.println("ok");
                        } else {

                            System.out.println("�߳�1-" + new String(b));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        byte[] b = new byte[len];
                        synchronized (raf) {
                            raf.seek(200);
                            raf.read(b);
                        }
                        if ("�߳�2".equals(new String(b))) {
                            System.out.println("ok");
                        } else {

                            System.out.println("�߳�2-" + new String(b));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        byte[] b = new byte[len];
                        synchronized (raf) {
                            raf.seek(300);
                            raf.read(b);
                        }
                        if ("�߳�3".equals(new String(b))) {
                            System.out.println("ok");
                        } else {

                            System.out.println("�߳�3-" + new String(b));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(runnable1).start();
            new Thread(runnable2).start();
            new Thread(runnable3).start();
        }
        System.out.println("���߳�����");

    }

}
