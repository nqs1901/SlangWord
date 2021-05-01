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


//       Entry t = SlangWord.randomNode(SlangWord.root);
//       System.out.print(t.toString());
       editSlangWord();

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
                    SlangWord.insert(s[0],tar);
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
        System.out.print("\nEnter the definition you would like to find: ");
        String find = kb.nextLine();
        List<Entry> found = new ArrayList<Entry>();
        found =  SlangWord.findByDefinition(find);
        if(found == null) {
            System.out.println("Slang word not found.");
        }else {
            for (int i=0; i< found.size(); i++){
                System.out.print(found.get(i).toString());
                found.get(i).writeLine("history.txt");
            }  
        }
    }
      private static void findByKey() {
        System.out.println("");
        Scanner kb = new Scanner(System.in);
        System.out.print("\nEnter the slang word you would like to find: ");
        String find = kb.nextLine();
        Entry found = SlangWord.findByKey(find);
        
        if (found == null) {
            System.out.println("Slang word not found.");
        } else {
            System.out.println(found.toString());
            found.writeLine("history.txt");
        }
    }
      
    private static void addNewSlangWord() {
        System.out.println("");
        Scanner kb = new Scanner(System.in);
        System.out.print("\nEnter the key you would like to add to the slang word: ");
        String key = kb.nextLine();
        if (SlangWord.findByKey(key) == null) {
            List<String> definition = new ArrayList<>();
            String str;
            System.out.print("\nPlease enter the definition of the key: ");
            str = kb.nextLine();
            System.out.print("");
            definition.add(str);
            do {
                System.out.print("Have another definition?? if not enter to end : ");
                str = kb.nextLine();
                if (!str.isEmpty()) {
                    definition.add(str);
                }
            } while (!str.isEmpty());
            SlangWord.insert(key,definition);
            Entry newEntry = new Entry(key,definition);
            newEntry.writeLine("slang.txt");
            
        } else {
            System.out.println("\nThe word you entered already exists in the slang word.");
        }

    }
    
    private static void editSlangWord() {
        System.out.println("");
        Scanner kb = new Scanner(System.in);
        System.out.print("\nEnter the key you would like to edit to the slang word: ");
        String key = kb.nextLine();
        Entry found = SlangWord.findByKey(key);
        if ( found == null) {
            System.out.println("\nSlang word not found.");

        } else {
            List<String> definition = new ArrayList<>();
            String str;
            System.out.print("\nPlease enter the definition of the key: ");
            str = kb.nextLine();
            System.out.print("");
            definition.add(str);
            do {
                System.out.print("Have another definition?? if not enter to end : ");
                str = kb.nextLine();
                if (!str.isEmpty()) {
                    definition.add(str);
                }
            } while (!str.isEmpty());
            SlangWord.remove(found.key);
            SlangWord.insert(found.key,definition);
            truncateFile("slang.txt");
//            Entry newEntry = new Entry(key,definition);
//            newEntry.writeLine("slang.txt");
        }

    }
    

    private static void deleteSlangWord() {
        System.out.println("");
        Scanner kb = new Scanner(System.in);
        System.out.print("Please enter the key of the slang word you want to delete: ");
        String key = kb.nextLine();
        if (SlangWord.findByKey(key) == null) {
            System.out.println("SLang word not found.");
        } else {
            System.out.print("Are you sure? (y/n): ");
            String choose = kb.nextLine();
            if (choose.endsWith("y")){
                SlangWord.remove(key);
                truncateFile("slang.txt");
                System.out.println("Succcessfully removed " + key);
            }
            
        }
    }
    
    

    public static void truncateFile(String url) {
        File file = new File(url);
        try (PrintWriter pw = new PrintWriter(file)) {
            try {
                    pw.print(SlangWord.saveTree());           
            } catch (Exception e) {
                System.out.println("Can't truncate to file");
            } finally {
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void resetOriginList() {
        SlangWord.removeAll(SlangWord.root);
        readFile("origin.txt");
        truncateFile("slang.txt");
        System.out.println("!!! Set this as origin successfully !!!");

    }
    
    public static Entry randomSlangWord(){
        
        return SlangWord.randomNode(SlangWord.root);
    }
    
    public static void quizByKey(){
        Random rd = new Random();
        List<Entry> answer = new ArrayList<Entry>();
        Entry temp = randomSlangWord();
        for (int i=0; i<4;i++){
            while(answer .contains(temp)){
                temp = randomSlangWord();
            }
            answer.add(temp);
        }
         int correct = rd.nextInt(3);
         Scanner kb = new Scanner(System.in);
        System.out.println("\n^^ The mysterious slang word is: " + answer.get(correct).key);
        System.out.println("Answer: ");
        System.out.println("1. " + answer.get(0).definition);
        System.out.println("2. " + answer.get(1).definition);
        System.out.println("3. " + answer.get(2).definition);
        System.out.println("4. " + answer.get(3).definition);
        System.out.print("\nYour choose is: ");
        int choose = kb.nextInt(); 
        if (choose - 1 == correct){
            System.out.println("Congrat!!! correct answer !!!");
        }else{
            System.out.println("Oops!!! Wrong answer");
        }
    }
    
    public static void quizByDefinition(){
        Random rd = new Random();
        List<Entry> answer = new ArrayList<Entry>();
        Entry temp = randomSlangWord();
        for (int i=0; i<4;i++){
            while(answer .contains(temp)){
                temp = randomSlangWord();
            }
            answer.add(temp);
        }
        int correct = rd.nextInt(3);
        Scanner kb = new Scanner(System.in);
        System.out.println("\n^^ The mysterious slang word is: " + answer.get(correct).definition);
        System.out.println("Answer: ");
        System.out.println("1. " + answer.get(0).key);
        System.out.println("2. " + answer.get(1).key);
        System.out.println("3. " + answer.get(2).key);
        System.out.println("4. " + answer.get(3).key);
        System.out.print("\nYour choose is: ");
        int choose = kb.nextInt(); 
        if (choose - 1 == correct){
            System.out.println("Congrat!!! correct answer !!!");
        }else{
            System.out.println("Oops!!! Wrong answer");
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
