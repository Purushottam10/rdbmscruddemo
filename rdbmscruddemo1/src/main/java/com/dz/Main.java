package com.dz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * com.dz.Main class used for calling
 * contains main Method
 */
public class Main {

    public static  void main(String []args ) throws IOException {

        StudentServiceR operations=new StudentServiceR();

        int choice = 0;
        int roll_no=1;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        do {
            System.out.print("Enter your choice");
            System.out.println("\n1 for Insert \n2 for Display \n3 for Display By Id "
                    + "\n4 for Remove data  \n5 for Update data  \n6.Exit");
            try {
                choice=Integer.parseInt(bufferedReader.readLine());
            }catch (NumberFormatException ex ){
                ex.printStackTrace();
            }
            if(choice==1){

              if(  operations.addStudent())
              {
                  System.out.println("Student added in table ");
              }
              else {
                  System.out.println("not add in table ");
              }
            }
            else if(choice==2){
                operations.display();
            }
            else if(choice==3){
                System.out.println("enter the student Roll No");
                try{
                    roll_no=Integer.parseInt(bufferedReader.readLine());
                }catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
                operations.displayById(roll_no);
            }

            else if(choice==4){
              operations.removeById();
            }
            else if(choice==5){
                System.out.println("enter the student Roll No");

               operations.updateById();
            }
               else if(choice==6){
                System.out.println("");
                System.exit(0);

            }
            else {
                System.out.println("Bad Choice ");
            }
        }
        while (true);

    }//method

}//class end
