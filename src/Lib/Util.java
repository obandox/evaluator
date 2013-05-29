/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class Util {

    public static String[] listDirs(String foldername) {
        ArrayList<String> arr = new ArrayList<>();
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                arr.add(file.getName());
            }
        }
        String[] strs = new String[arr.size()];
        return (String[]) arr.toArray(strs);
    }

    public static String[] listFiles(String foldername) {
        ArrayList<String> arr = new ArrayList<>();
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                arr.add(file.getName());
            }
        }
        return (String[]) arr.toArray();
    }

    public static LinkedList<String> locals() {
        LinkedList<String> list = new LinkedList<String>();
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    list.add(inetAddress.getHostAddress());
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static String readUrl(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine+"\n");
        }

        in.close();

        return response.toString();
    }

    
    
    public static String read(String pathname) {
        try {
            return readFile(pathname);
        } catch (IOException ex) {
        }
        return "";
    }

    public static String[] list(String pathname) {
        return read(pathname).split("\n");
    }

    public static boolean write(String filename, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));
            out.write(content);
            out.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static String readFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + "\n");
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

    public static void mkdir(String path) {
        new File(path).mkdirs();
    }

    public static String exec(String cmd) throws TimeoutException {
        return exec(cmd, 30000);
    }

    public static String exec(String cmd, long timeout) throws TimeoutException {
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            Worker worker = new Worker(p);
            worker.start();
            try {
                worker.join(timeout);
                if (worker.exit != null) {
                    return worker.out;
                } else {
                    throw new TimeoutException();
                }
            } catch (InterruptedException ex) {
                worker.interrupt();
                Thread.currentThread().interrupt();
            } finally {
                p.destroy();
            }
            return worker.out;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void copy(String src, String dest) {
        try {
            copy(new File(src), new File(dest));
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void copy(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();

            // previous code: destination.transferFrom(source, 0, source.size());
            // to avoid infinite loops, should be:
            long count = 0;
            long size = source.size();
            while ((count += destination.transferFrom(source, count, size - count)) < size);
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    private static class Worker extends Thread {

        private final Process process;
        private String exit = null;
        private String out = "";

        private Worker(Process process) {
            this.process = process;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    out += (line) + "\n";
                }
                exit = out;
            } catch (IOException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
