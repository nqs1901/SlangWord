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
        readFile("slang.txt");
        findByDefinition();
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
    
    private static void findByDefinition() {
        System.out.println("");
        Scanner kb = new Scanner(System.in);
        System.out.print("\nEnter the word you would like to find: ");
        String find = kb.nextLine();
        SlangWord.find(find);
    }
      private static void findBySlangWord() {
        System.out.println("");
        Scanner kb = new Scanner(System.in);
        System.out.print("\nEnter the slang word you would like to find: ");
        Entry find = new Entry(kb.nextLine());
        Node found = SlangWord.find(find);
        if (found == null) {
            System.out.println("Word not found.");
        } else {
            System.out.println(found);
        }
    }
    public static void menu(){
        System.out.println("----------MENU----------");
        System.out.println("1. Find by slang word");
        System.out.println("2. Find by definition");
        System.out.println("3. Search history)");
        System.out.println("4. Add 1 slang word");
        System.out.println("5. Edit 1 slang word");
        System.out.println("6. Delete 1 slang word");
        System.out.println("7. Reset original list");
        System.out.println("8. Random 1 slang ");
        System.out.println("9. Quiz by slang word");
        System.out.println("10. Quiz by definition");
        System.out.println("11. Exitt");
        System.out.println("-------------------------------------");
        System.out.print("Input number to choose: ");
    }
    
}
