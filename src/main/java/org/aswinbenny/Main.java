package org.aswinbenny;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Program loaded");


        Connection conn=connectDb(); //function to connect to db
        if(conn!=null){
//            displayTable(conn);
//            addStudentDetails(conn);
//            createNewClass(conn);
//            showClasses(conn);
            editMarkSheet(conn);
        }


    }

    public static void editMarkSheet(Connection conn){
        try{
            String query="UPDATE class2 SET Name=?, Physics=?, Maths=?, Chemistry=?, Biology=? WHERE Rollno=?";

            PreparedStatement pstmt3=conn.prepareStatement(query);

            Scanner sc_obj3=new Scanner(System.in);
            int rollno,physics,maths,chemistry,biology;
            String name;
            System.out.println("Enter the Rollno whose details has to be changed; ");
            rollno=sc_obj3.nextInt();
            sc_obj3.nextLine(); //eliminating newline
            System.out.println("Enter new name; ");
            name=sc_obj3.nextLine();
            System.out.println("Enter new physics marks: ");
            physics=sc_obj3.nextInt();
            System.out.println("Enter new maths marks: ");
            maths=sc_obj3.nextInt();
            System.out.println("Enter new chemistry marks: ");
            chemistry=sc_obj3.nextInt();
            System.out.println("Enter new biology marks: ");
            biology=sc_obj3.nextInt();

            pstmt3.setString(1,name);
            pstmt3.setInt(2,physics);
            pstmt3.setInt(3,maths);
            pstmt3.setInt(4,chemistry);
            pstmt3.setInt(5,biology);
            pstmt3.setInt(6,rollno);

            pstmt3.executeUpdate();
            System.out.println("Student details updated.");

        }catch (Exception e){
            System.out.println("Error in editMarkSheet(): "+e);
        }
    }
    public static void showClasses(Connection conn){
        try{
            String query="SHOW TABLES";
            PreparedStatement pstmt3= conn.prepareStatement(query);
            ResultSet set=pstmt3.executeQuery();

            String table_names;
            System.out.println("Here are the list of classes in our school: ");
            while(set.next()){
                table_names=set.getString(1);
                System.out.println(table_names);
            }
        }catch (Exception e){
            System.out.println("Error in showClasses(): "+e);
        }

    }
    public static void createNewClass(Connection conn){
        try {
            String name_of_class;

            Scanner sc_ob2=new Scanner(System.in);
            System.out.println("Enter the name of new Class: ");
            name_of_class=sc_ob2.next();

            String query="CREATE TABLE "+name_of_class+" (Rollno int primary key auto_increment, Name varchar(35), Physics int, Maths int, Chemistry int, Biology int)";

            PreparedStatement pstmt2=conn.prepareStatement(query);
            pstmt2.executeUpdate();
            System.out.println("Class "+name_of_class+" added successdully");

        }catch (Exception e){
            System.out.println("Error in createNewClass(): "+e);
        }

    }
    public static void addStudentDetails(Connection conn){
        try{
            String query=" INSERT INTO class2 (Rollno,Name,Physics,Maths,Chemistry,Biology) VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt=conn.prepareStatement(query);

            Scanner sc_ob1=new Scanner(System.in);
            //initialising variables
            String name;
            int rollno,physics,maths,chemistry,biology,no_of_students;

            System.out.println("Enter the number of students to add: ");
            no_of_students=sc_ob1.nextInt();

            while (no_of_students > 0) {
                //taking input from user
//                System.out.println("Enter the roll no: ");
//                rollno = sc_ob1.nextInt();
//                sc_ob1.nextLine(); // consumes newline character
                System.out.println("Enter student name: ");
                name = sc_ob1.nextLine();
                System.out.println("Enter marks of Physics: ");
                physics = sc_ob1.nextInt();
                System.out.println("Enter marks of Maths: ");
                maths = sc_ob1.nextInt();
                System.out.println("Enter marks of Chemisitry: ");
                chemistry = sc_ob1.nextInt();
                System.out.println("Enter marks of Biology: ");
                biology = sc_ob1.nextInt();
                System.out.println();//adding an extra line for looks

               // pstmt.setInt(1, rollno);
                pstmt.setString(2, name);
                pstmt.setInt(3, physics);
                pstmt.setInt(4, maths);
                pstmt.setInt(5, chemistry);
                pstmt.setInt(6, biology);

                pstmt.executeUpdate();

                no_of_students--;
            }
            System.out.println("Student details added successfully. ");

        }catch(Exception e){
            System.out.println("Error occured at addStudentDetails: "+e);
        }

    }
    public static void displayTable(Connection conn){
        try{
            String query="SELECT * FROM class2"; //query to select all from table
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet set= pstmt.executeQuery(); //stores the out in a Resultset called set


            System.out.println("RollNo   Name           Physics   Chemistry   Maths   Biology");
            while (set.next()) {
                int id = set.getInt(1); // 1 is column index
                String name = set.getString(2);
                int physics = set.getInt(3);
                int maths = set.getInt(4);
                int chemistry = set.getInt(5);
                int biology = set.getInt(6);

                String formattedId = String.format("%-9d", id);         // Adjust the width as needed
                String formattedName = String.format("%-15s", name);    // Adjust the width as needed
                String formattedPhysics = String.format("%-10d", physics);
                String formattedChemistry = String.format("%-12d", chemistry);
                String formattedMaths = String.format("%-8d", maths);
                String formattedBiology = String.format("%-8d", biology);

                System.out.println(formattedId + formattedName + formattedPhysics + formattedChemistry + formattedMaths + formattedBiology);
            }


        }catch (Exception e){
            System.out.println("Error occured in displayTable(): "+e);
        }


    }
    public static Connection connectDb(){
        try{

            String dbUrl="jdbc:mysql://localhost:3306/school"; //db selected is school
            String driver="com.mysql.cj.jdbc.Driver";
            String userName="root"; //username of db
            String password = System.getenv("MYSQL_PASSKEY"); //password of db taken from environment variable MYSQL_PASSKEY

            //load the mysql jbdc driver to memory
            Class.forName(driver);

            Connection conn=DriverManager.getConnection(dbUrl,userName,password);

            if(conn.isClosed()){
                System.out.println("Connection is closed");
            }else{
                System.out.println("Database connected");
                return conn;
            }


        }catch (Exception e){
            System.out.println("Error occured: "+e);
        }
        return null;
    }
}