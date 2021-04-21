/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slang_word;

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
}
