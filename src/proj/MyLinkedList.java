/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj;

import java.util.Scanner;

/**
 *
 * @author Windows 10
 */
public class MyLinkedList {

    Node first, last;
    int size;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void insertFirst(int a, int b) {
        Node newNode = new Node(a, b);
        if (first == null) {
            last = newNode;
        } else {
            newNode.next = first;
        }
        first = newNode;
    }

    public void sortList() {
        Node temp = first, temp2 = null;
        int degree;
        int coeff;
        if (first == null) {
            System.out.println("There are no element in the list");
        } else {
            while (temp != null) {
                temp2 = temp.next;
                while (temp2 != null) {
                    if (temp.degree < temp2.degree) {
                        degree = temp.degree;
                        coeff = temp.coeff;
                        temp.degree = temp2.degree;
                        temp.coeff = temp2.coeff;
                        temp2.degree = degree;
                        temp2.coeff = coeff;
                    }
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
        }
    }

    public MyLinkedList addPolynomial(MyLinkedList m1){
        MyLinkedList result = new MyLinkedList();
        int coeff,degree;
        Node k;
        Node i = m1.first;
        Node j = first ;
        while(i!=null && j!=null){
            if(i.degree == j.degree){
                coeff = i.coeff + j.coeff;
                degree = i.degree;
                i = i.next;
                j = j.next;
            }else if(i.degree > j.degree){
                    coeff = i.coeff;
                    degree = i.degree;
                    i = i.next;
            }else{
                coeff = j.coeff;
                degree = j.degree;
                j = j.next;
            }

            if(coeff != 0){
                result.insertFirst(coeff, degree);
            }
        }
        if(i == null)
            k = j;
        else
            k = i;
        while(k != null){
            result.insertFirst(k.coeff, k.degree);
            k = k.next;
        }
        result.sortList();
        result.printAPoly();
        return result;
    }

    public MyLinkedList multiplyPolynomials(MyLinkedList list) {
        MyLinkedList result = new MyLinkedList();
        Node i = list.first;
        Node j = first;
        int coeff, degree;
        while (i != null) {
            while (j != null) {
                coeff = i.coeff * j.coeff;
                degree = i.degree + j.degree;
                result.insertFirst(coeff, degree);
                result.addSameDegree();
                result.sortList();
                j = j.next;
            }
            j = first;
            i = i.next;
        }
        result.printAPoly();
        return result;
    }

    public void addSameDegree() {
        Node delete;
        Node temp1 = first;
        Node temp2 = first.next;
        while (temp1 != null) {
            while (temp2 != null) {
                if (temp2.degree == temp1.degree) {
                    temp1.coeff += temp2.coeff;
                    delete = temp2;
                    removeSameDegree(delete);
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
    }

    public void removeSameDegree(Node n) {
        Node tmp = first;
        while (tmp != null) {
            if (tmp.next == n) {
                tmp.next = tmp.next.next;
            }
            tmp = tmp.next;
        }
    }

    public void removeGivenPowerFromPoly(int x) {
        Node temp = first;
        Node temp2 = first;       
        if (first.degree == x) {
            first = first.next;
            System.out.println(x+" is found and removed from the list");
        } else {
            while (temp.next != null && temp.degree != x) {
                temp2 = temp;
                temp = temp.next;
            }
            if(temp.degree==x){
                temp2.next = temp2.next.next;
                System.out.println(x+" is found and removed from the list");
        }else
                System.out.println(x+" is NOT found in the list");
        }
    }

    public void printAPoly() {
        Node temp = first;
            while (temp != null) {
                if(temp.next!=null)
                    System.out.print(temp.coeff + "x^" + temp.degree + " + ");
                else{
                    if(temp.degree!=0)
                        System.out.print(temp.coeff + "x^" + temp.degree);
                    else
                        System.out.print(temp.coeff);
                }
            
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList list1 = new MyLinkedList();
        MyLinkedList list2 = new MyLinkedList();
        int coeff1 = -1;
        int coeff2 = -1;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter degree and coefficiant for the first polynomial, type 0 to end.");
        System.out.println("Press enter after writing the number.");
        while (coeff1 != 0) {
            coeff1 = scan.nextInt();
            if (coeff1 == 0) {
                break;
            }
            int degree1 = scan.nextInt();
            list1.insertFirst(coeff1, degree1);
        }
        System.out.println("Please enter degree and coefficiant for the second polynomial, type 0 to end");
        while (coeff2 != 0) {
            coeff2 = scan.nextInt();
            if (coeff2 == 0) {
                break;
            }
            int degree2 = scan.nextInt();
            list2.insertFirst(coeff2, degree2);
        }
        System.out.println("The entered polynomials are:");
        System.out.print("List 1: ");
        list1.sortList();
        list1.printAPoly();
        System.out.print("List 2: ");
        list2.sortList();
        list2.printAPoly();
        int usernumber = 0;
        while (usernumber != 5) {
            System.out.println("****************************************************************");
            System.out.println("Press a number between 1 and 5 to perform the desired action" + "\n" + "1-Add two polynomials " + "\n" + "2-Multiply two polynomials" + "\n"
                    + "3-Print a polynomial" + "\n" + "4-Remove a node with a given power from polynomial" + "\n" + "5-Exit"+"\n"+"****************************************************************");
            usernumber = scan.nextInt();
            switch (usernumber) {
                case 1:
                    System.out.println("Adding two polynomials:");
                    list1.addPolynomial(list2);
                    break;
                case 2:
                    System.out.println("Multiplying two polynomials:");
                    list1.multiplyPolynomials(list2);
                    break;
                case 3:
                    System.out.println("The polynomials are:");
                    System.out.print("List 1: ");
                    list1.sortList();
                    list1.printAPoly();
                    System.out.print("List 2: ");
                    list2.sortList();
                    list2.printAPoly();
                    break;
                case 4:
                    System.out.println("Which power you want to be deleted from both polynomials:");
                    int power = scan.nextInt();
                    if(list1.first!=null)
                        list1.removeGivenPowerFromPoly(power);
                    else
                        System.out.println("List is empty");
                    if(list2.first!=null)
                        list2.removeGivenPowerFromPoly(power);
                    else
                        System.out.println("List is empty");
                    break;
                case 5:
                    System.out.println("See you");
                    break;
                default:
                    System.out.println("You can enter only 1-5 numbers");
            }
        }

    }

}
