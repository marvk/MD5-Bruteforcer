import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by Marvin on 28.11.2014.
 */
public class Main {
    public static void main (String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int i = 0;
        String s = null;
        byte[] hash = null;
        StringBuilder sb = null;
        ArrayList<String> dictionary = new ArrayList<String>();
        MessageDigest md = MessageDigest.getInstance("MD5");

        //Import words into dictionary
        for (char c = 'A'; c <= 'Z'; c++) {
            String path = "words/" + c +  " Words.csv";
            BufferedReader br = null;
            String line = null;

            try {
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null)
                    dictionary.add(line);
            } catch (FileNotFoundException ignored) {
            } catch (IOException ignored) {}
        }

        for (String s1 : dictionary) {
            for (String s2 : dictionary) {
                s = s1 + "+" + s2;
                hash = md.digest(s.getBytes("UTF-8"));

                sb = new StringBuilder(2*hash.length);
                for (byte b : hash) {
                    sb.append(String.format("%02x", b&0xff));
                }

                if (sb.toString().equals("68059415b3b24844f9d93d9653a40e29")) {
                    System.out.println("Puzzle Solution: \"" + s + "\"");
                    return;
                }
            }
            System.out.println(i++ + "/" + dictionary.size());
        }
    }
}