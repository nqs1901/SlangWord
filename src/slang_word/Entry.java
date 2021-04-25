/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slang_word;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author nqs
 */
public class Entry implements Comparable<Entry>{
    private String key;
    private List<String> definition;
    
    public Entry(){
        
    }
    public Entry(String key, List<String> definition){
        this.key = key;
        this.definition = definition;
    }
    
    public Entry(String key){
        this.key = key;
        this.definition = null;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getDefinition() {
        return definition;
    }

    public void setDefinition(List<String> definition) {
        this.definition = definition;
    }

    @Override
    public int compareTo(Entry t) {
        return key.compareToIgnoreCase(t.key);
    }
    
    @Override
    public String toString() {
            return key + " " + definition + "\n";
    }
    
    public void writeLine(String file){
        try (BufferedWriter saved = new BufferedWriter(new FileWriter(file,true))) {
                saved.write(key + "`");
                for(int i=0;i<definition.size();i++){
                    saved.write(definition.get(i));
                    if(i+1<definition.size()){
                        saved.write("|");
                    }
                }
                saved.newLine();
                
                System.out.println("Slang Word successfully loaded.");
            } catch (IOException e) {
                System.out.println("Invalid File name.");
            }
    }
}
