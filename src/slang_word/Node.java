/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slang_word;

/**
 *
 * @author nqs
 * @param <AnyType>
 */
public class Node<AnyType extends Comparable> implements Comparable<Node>{
    AnyType item;
    Node Left = null;
    Node Right = null;
    private int height = 0;
    
    public AnyType getItem() {
        return item;
    }

    public void setItem(AnyType item) {
        this.item = item;
    }

    public Node getLeft() {
        return Left;
    }

    public void setLeft(Node Left) {
        this.Left = Left;
    }

    public Node getRight() {
        return Right;
    }

    public void setRight(Node Right) {
        this.Right = Right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
   

    public Node(){
        this.item =null;
    }
    public Node(AnyType item) {
        this.item = item;
    }

    @Override
    public int compareTo(Node t) {
        return this.item.compareTo(t.item);
    }
    
    @Override
    public String toString() {
        return this.item.toString();
    }
}
