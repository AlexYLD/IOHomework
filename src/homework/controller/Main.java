package homework.controller;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        while (true) {
            str = scan.nextLine();
            if (str.equals("-1")) {
                break;
            }
            new Write(str).start();
        }
    }
}

class Write extends Thread {

    private String text;

    public Write(String str) {
        text = str;
    }

    @Override
    public void run() {
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        File target = new File("C:/Users/ayarygix/Desktop/tests/myFile" + Main.i.getAndIncrement() + ".txt");
        target.getParentFile().mkdirs();
        try {
            target.createNewFile();
            OutputStream outputStream = new FileOutputStream(target);
            copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buff = new byte[64 * 1024];
        while (inputStream.available() > 0) {
            int count = inputStream.read(buff);
            outputStream.write(buff, 0, count);
        }
        inputStream.close();
        outputStream.close();
    }

}
