/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj;

/**
 *
 * @author Windows 10
 */
public class Node {
    int coeff ;
    int degree ;
    Node next ;

    public Node(int coeff , int degree) {
    this.coeff = coeff ;
    this.degree = degree;
    next = null ;
    }
    public String toString(){
        return coeff + degree + "" ;
    }
    
}
