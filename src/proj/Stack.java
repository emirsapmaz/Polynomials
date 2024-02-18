/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj;

import java.util.Scanner;

/**
 *
 * @author Emir Sapmaz
 */
public class Stack {

    String data[]; // array to hold items
    int top; // the top most item index

    public Stack(int N) {
        data = new String[N];
        top = -1;
    }

    public void push(String element) {
        if (!isFull()) {
            top++;
            data[top] = element;
        }
    }

     public String pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } else {
            String tmp=data[top];
            data[top]=null;
            top--;
            return tmp;
        }
    }

    public boolean isFull() {
        if (top == data.length - 1) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (top == - 1) {
            return true;
        }
        return false;
    }
    public void print(){
        String s ="";
        for (int i = 0; i < data.length; i++) {
            if(data[i]!=null)
                s+=data[i]+" ";
        }
        System.out.println(s);
        
    }
    
    public void deleteMiddle(){
        int size = top+1;
        int mid=size/2;
        String a=data[mid];
        if(size%2==0)
            System.out.println("Cannot remove anything, the stack is even.");
        else{
            Stack temp = new Stack(data.length);
            for (int i = 0; i < (mid+1); i++) {//ortaya kadar olan elementler temp stack'ine ekleniyo ve this ten çıkıyo
                temp.push(pop());
                data[top+1]=null;
                }
            for (int i = (temp.top-1);i>=0; i--) {//ortadaki eleman haricindeki çıkan elemanlar geri stack'e ekleniyo
                push(temp.data[(i)]);
                }
            System.out.println(a+" has been removed from the stack.");
            }
    }
    
    public boolean isPalindrome(){
        int size =top+1;
        int mid =size/2;
        if(size%2!=0){//tek olma durumu
            for (int i = 0; i < mid; i++) {//aşağıdan yukarı
                for (int j = top; j > mid; j--) {//yukarıdan aşağı kontrol
                    if(data[i].equals(data[j]))
                        return true;
                    else
                        return false;
                }
            }
        }else{//çift olma durumu
            for (int i = 0; i <= (top/2); i++) {//aşağıdan yukarı
                for (int j = top; j>=mid; j--) {//yukarıdan aşağı kontrol
                    if(data[i].equals(data[j]))
                        return true;
                    else
                        return false;
                }
            }
        }
        return false;
    } 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which task you want to do?\n" +
                            "1- push\n" +
                            "2- pop\n" +
                            "3- Print\n" +
                            "4- Delete middle\n" +
                            "5- isPalindrome\n" +
                            "6- exit");

        int menu;
        Stack user=new Stack(5) ;//null pointer exemption vermemesi için bir değere atıyoruz
        do{
            System.out.println("Please enter a digit(1-5):");
            menu=input.nextInt();
            switch(menu){
            case 1:
                System.out.println("How many pushes you want to do ?");
                int u = input.nextInt();
                System.out.println("Enter "+u+" strings.");
                for (int i = 0; i < u; i++) {
                    user.push(input.next());
                }
                break;
            case 2:
                if(user.isEmpty())
                    System.out.println("Stack is empty");
                else
                    System.out.println(user.pop()+" popped!");
                break;
            case 3:
                if(user.isEmpty())
                    System.out.println("The stack is empty.");
                else{
                System.out.println("The content of the stack is:");
                user.print();
                }
                break;
            case 4:
                if(user.isEmpty())
                    System.out.println("Stack is empty");
                else
                    user.deleteMiddle();
                break;
            case 5:
                if(user.isEmpty())
                    System.out.println("Stack is empty");
                else{
                    if(user.isPalindrome())
                        System.out.println("The stack is palindrome");
                    else
                        System.out.println("The stack is NOT palindrome");
                }
                break;
            case 6:
                System.out.println("Exiting...");
                break;
            }
            
        }while(menu!=6);
        
        
    }
    

}
