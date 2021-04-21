/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slang_word;

import java.io.*;
import java.util.*;

/**
 *
 * @author nqs
 */
public class main {

    /**
     * @param args the command line arguments
     */
    private static AVLTree SlangWord = new AVLTree();
    public static void main(String[] args) {
        // TODO code application logic here
//        SlangWord slw = new SlangWord();
        readFile("slang.txt");
        SlangWord.printTree();
    }
    
    public static void readFile(String fileName) {
        System.out.println("");
        try (BufferedReader saved = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = saved.readLine()) != null) {

                if (line.contains("`")) {

                    List<String> tar = new ArrayList<>();
                    String[] s = line.split("`");
                    if (s[1].contains("|")) {
                        String[] tmp = s[1].split("\\|");
                        for (int i = 0; i < tmp.length; i++) {
                            tmp[i] = tmp[i].trim();
                        }
                        tar = Arrays.asList(tmp);
                    } else {
                        tar.add(s[1]);
                    }

                    Entry newEntry = new Entry(s[0], tar);
                    SlangWord.insert(newEntry);
                }
            }
            System.out.println("Slang Word successfully loaded.");
        } catch (IOException e) {
            System.out.println("Invalid File name.");
        }
    }
}
