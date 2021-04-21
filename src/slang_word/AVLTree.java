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
public class AVLTree<AnyType extends Comparable> {
    private Node root;
    
    public AVLTree(Node root) {
        this.root = root;
    }
    
    public AVLTree(){
        this.root = null;
    }
    
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    
    private int getHeight(Node x) {
        if(x == null) {
            return -1;
        }
        return x.getHeight();
    }
    
    private Node<AnyType> rotateWithLeft(Node<AnyType> n2) {
        Node n1 = n2.Left;
        n2.Left = n1.Right;
        n1.Right = n2;
        n2.setHeight(Integer.max(getHeight(n2.Left), getHeight(n2.Right)) + 1);
        n1.setHeight(Integer.max(getHeight(n1.Left), getHeight(n2)) + 1);
        return n1;
    }

    private Node<AnyType> rotateWithRight(Node<AnyType> n1) {
        Node n2 = n1.Right;
        n1.Right = n2.Left;
        n2.Left = n1;
        n1.setHeight(Integer.max(getHeight(n1.Left), getHeight(n1.Right)) + 1);
        n2.setHeight(Integer.max(getHeight(n2.Right), getHeight(n1)) + 1);
        return n2;
    }

    private Node<AnyType> doubleWithLeft(Node<AnyType> n3) {
        n3.Left = rotateWithRight(n3.Left);
        return rotateWithLeft(n3);
    }

    private Node<AnyType> doubleWithRight(Node<AnyType> n1) {
        n1.Right = rotateWithLeft(n1.Right);
        return rotateWithRight(n1);
    }
    private Node<AnyType> insert(AnyType element, Node<AnyType> check){
        if(check == null){
            check = new Node(element);
        }
        else if (element.compareTo(check.getItem()) < 0) {
            check.Left = insert(element, check.Left);
            if (getHeight(check.Left) - getHeight(check.Right) == 2) {
                if (element.compareTo(check.Left.getItem()) < 0) {
                    check = rotateWithLeft(check);
                } else {
                    check = doubleWithLeft(check);
                }
            }
        } else if (element.compareTo(check.getItem()) > 0) {
            check.Right = insert(element, check.Right);
            if (getHeight(check.Right) - getHeight(check.Left) == 2) {
                if (element.compareTo(check.Right.getItem()) > 0) {
                    check = rotateWithRight(check);
                } else {
                    check = doubleWithRight(check);
                }
            }
        } else {
            System.out.println("The key you entered already exists in Slang word.");
        }

        check.setHeight(Integer.max(getHeight(check.Left), getHeight(check.Right)) + 1);
        return check;
    }
    
    public void insert(AnyType element) {
        root=insert(element, root);
    }
    
    public void printTree() {
        printTree(root);
    }

    private void printTree(Node check) {
        if (check.Left != null) {
            printTree(check.Left);
        }
        System.out.print(check);
        if (check.Right != null) {
            printTree(check.Right);
        }
    }
    
}
