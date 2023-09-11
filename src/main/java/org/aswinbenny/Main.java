package org.aswinbenny;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try{
            // connects to the mysql database and conn is the connection object returned
            Connection conn=connectDb();

            //user defined function that displays the list of marksheet in the school
            showClasses(conn);

            Scanner sc_obj6=new Scanner(System.in);
            int choice; // variable to saving users choice
            String marksheet_name; // variable to store the name of marksheet on which CRUD functions are to be done
            System.out.println("\nSelect a choice:\n1. Choose an existing marksheet\n2. Create a new marksheet\n3. Quit program");
            choice=sc_obj6.nextInt();
            if(choice==1){
                System.out.println("Enter the name of marksheet or class: ");
                marksheet_name=sc_obj6.next();
                dashboard(conn,marksheet_name); // goes to dashboard function
            }
            else if(choice==2){
                createNewClass(conn); // goes to create a new marksheet/class
            }
            else{
                System.out.println("Wrong input. Program will now exit");
                conn.close();
            }


        }catch (Exception e){
            System.out.println("Error in main() :"+e);
        }
    }

    /*
    dashboard function is the dashboard where users get to choose to do show, delete, create marksheet or do CRUD with student details on it
     */
    public static void dashboard(Connection conn, String marksheet_name){


        Scanner sc_obj5=new Scanner(System.in);
        int choice; //variable to store choice of user


        System.out.println("\n\nEnter the choice");
        System.out.println("1. Show available Marksheets\n2. Create New Marksheet\n3. Delete marksheet\n\n4. Add Student Details\n5. Display Marksheet\n6. Edit Student Details \n7. Delete student Details\n\n8. Quit program\n");
        choice=sc_obj5.nextInt();



        if(conn!=null){
            if(choice==1){
                showClasses(conn);
                dashboard(conn,marksheet_name);
            }
            else if(choice==2) {
                createNewClass(conn);
                dashboard(conn,marksheet_name);

            }
            else if(choice==3) {
                deleteClass(conn);
            }
            else if(choice==4) {
                addStudentDetails(conn,marksheet_name);
                dashboard(conn,marksheet_name);

            }
            else if(choice==5) {
                displayTable(conn,marksheet_name);
                dashboard(conn,marksheet_name);

            }
            else if(choice==6) {
                editMarkSheet(conn,marksheet_name);
                dashboard(conn,marksheet_name);

            }
            else if(choice==7){
                deleteStudent(conn, marksheet_name);
                dashboard(conn,marksheet_name);
            }
            else if(choice==8){
                try {
                    conn.close();
                }catch (Exception e){
                    System.out.println("Error occured while closing connection: "+e);
                }
            }
            else {
                System.out.println(" Wrong selection; Try again\n\n\n");
                dashboard(conn,marksheet_name);
            }
        }
    }

    /*
    deleteStudent function is used to delete a student details on basis of their rollno
     */
    public static void deleteStudent(Connection conn,String marksheet_name){
        try{
            Scanner sc_obj6=new Scanner(System.in);
            int rollno; //variable to store roll no
            System.out.println("Enter the roll no of student to delete: ");
            rollno=sc_obj6.nextInt();

            String query="DELETE FROM "+marksheet_name+" WHERE Rollno=?"; //sql query to delete a student row of particular rollno
            PreparedStatement pstmt6= conn.prepareStatement(query);
            pstmt6.setInt(1,rollno);

            pstmt6.executeUpdate();
            System.out.println("Student details deleted successfully");


        }catch (Exception e){
            System.out.println("Error in deleteStudent(): "+e);
        }
    }

    /*
    editMarkSheet function is used to edit the marks and name of the student by entering their rollno
     */
    public static void editMarkSheet(Connection conn, String marksheet_name){
        try{
            String query="UPDATE "+marksheet_name+" SET Name=?, Physics=?, Maths=?, Chemistry=?, Biology=? WHERE Rollno=?";

            PreparedStatement pstmt3=conn.prepareStatement(query);

            Scanner sc_obj3=new Scanner(System.in);
            int rollno,physics,maths,chemistry,biology;
            String name;
            System.out.println("Enter the Rollno whose details has to be changed: ");
            rollno=sc_obj3.nextInt();
            sc_obj3.nextLine(); //eliminating newline
            System.out.println("Enter new name: ");
            name=sc_obj3.nextLine();
            System.out.println("Enter new physics marks: ");
            physics=sc_obj3.nextInt();
            System.out.println("Enter new maths marks: ");
            maths=sc_obj3.nextInt();
            System.out.println("Enter new chemistry marks: ");
            chemistry=sc_obj3.nextInt();
            System.out.println("Enter new biology marks: ");
            biology=sc_obj3.nextInt();

            pstmt3.setString(1,name); // 1, 2 etc are parameter index ie index of '?' in the query index starts from 1
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


    /*
    deleteClass function is used to delete a marksheet or a class in the school
     */
    public static void deleteClass(Connection conn){
        try {
            Scanner sc_ob4=new Scanner(System.in);
            String name_of_marksheet;
            System.out.println("Enter the name of marksheet to delete: ");
            name_of_marksheet=sc_ob4.next();

            String query="DROP TABLE "+name_of_marksheet;

            PreparedStatement pstmt4= conn.prepareStatement(query);
            pstmt4.executeUpdate();

            System.out.println("Marksheet deleted successfully ");
        }catch (Exception e){
            System.out.println("Error in deleteClass: "+e);
        }
    }

    /*
    showClasses function displays all the marksheets that are available in the school
     */
    public static void showClasses(Connection conn){
        try{
            String query="SHOW TABLES";
            PreparedStatement pstmt3= conn.prepareStatement(query);
            ResultSet set=pstmt3.executeQuery(); //return value of excecuteQuery gets saved in a Resultset called set

            String table_names;
            System.out.println("Here are the list of Marksheets in our school: ");
            while(set.next()){
                table_names=set.getString(1);
                System.out.println(table_names);
            }
        }catch (Exception e){
            System.out.println("Error in showClasses(): "+e);
        }

    }

    /*
    createNewClass is used to create a new class or a new marksheet for the school
     */
    public static void createNewClass(Connection conn){
        try {
            String name_of_class;

            Scanner sc_ob2=new Scanner(System.in);
            System.out.println("Enter the name of new Class: ");
            name_of_class=sc_ob2.next();

            String query="CREATE TABLE "+name_of_class+" (Rollno int primary key auto_increment, Name varchar(35), Physics int, Maths int, Chemistry int, Biology int)";

            PreparedStatement pstmt2=conn.prepareStatement(query);
            pstmt2.executeUpdate();
            System.out.println("Class "+name_of_class+" added successfully");
            dashboard(conn,name_of_class);

        }catch (Exception e){
            System.out.println("Error in createNewClass(): "+e);
        }

    }


    /*
    addStudentDetails is used to add the new student details to the marksheet
     */
    public static void addStudentDetails(Connection conn, String marksheet_name){
        try{
            String query=" INSERT INTO "+marksheet_name+" (Name,Physics,Maths,Chemistry,Biology) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt=conn.prepareStatement(query);

            Scanner sc_ob1=new Scanner(System.in);
            //initialising variables
            String name;
            int rollno,physics,maths,chemistry,biology,no_of_students;

            System.out.println("Enter the number of students to add: ");
            no_of_students=sc_ob1.nextInt();

            while (no_of_students > 0) {
                //taking input from user
//
                sc_ob1.nextLine(); // consumes newline character
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
                pstmt.setString(1, name);
                pstmt.setInt(2, physics);
                pstmt.setInt(3, maths);
                pstmt.setInt(4, chemistry);
                pstmt.setInt(5, biology);

                pstmt.executeUpdate();

                no_of_students--;
            }
            System.out.println("Student details added successfully. ");

        }catch(Exception e){
            System.out.println("Error occured at addStudentDetails: "+e);
        }

    }


    /*
    displayTable will display the marksheet containing rollno, name and subject marks
     */
    public static void displayTable(Connection conn, String marksheet_name){
        try{
            String query="SELECT * FROM "+marksheet_name; //query to select all from table
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

                String formattedId = String.format("%-9d", id);
                String formattedName = String.format("%-15s", name);
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


    /*
    connectDB is used to establish connection with mysql database
     */
    public static Connection connectDb(){
        try{

            String dbUrl="jdbc:mysql://localhost:3306/school"; //db selected is school
            String driver="com.mysql.cj.jdbc.Driver";
            String userName="root"; //username of db
            String password = System.getenv("MYSQL_PASSKEY"); //password of db taken from environment variable MYSQL_PASSKEY, replace string with your mysql password
            //export MYSQL_PASSKEY="<mysql password here>" insert this line in .bashrc file in home of linux distro to save environment variable

            //load the mysql jbdc driver to memory
            Class.forName(driver);

            Connection conn=DriverManager.getConnection(dbUrl,userName,password);

            if(conn.isClosed()){
                System.out.println("Connection is closed");
            }else{
                //System.out.println("Database connected");
                return conn;
            }


        }catch (Exception e){
            System.out.println("Error occured: "+e);
        }
        return null;
    }
}
