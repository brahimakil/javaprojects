import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Enrolling   {
   
    Scanner scan = new Scanner(System.in);
    int choice;

    File f = new File("students.txt");

    ArrayList<Student> studentslist = new ArrayList<>();
    

       public void mainmenu() {
        int choice;
        do {
            System.out.println("Enter 1 to Enroll\nEnter 2 to Display Information\nEnter 3 to edit\nEnter 4 to Delete\nEnter 5 to Exit\nInput: ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    enroll();
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    edit_student();
                    break;
                case 4:
                    delete_student();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }

    public void enroll() {
        System.out.println("Enter Name: ");
        String name = scan.next();
        System.out.println("Enter Phone Number: ");
        long phone = scan.nextInt();
        System.out.println("Enter Major: ");
        String major = scan.next();
        int id = getlastid() + 1;
        Student student = new Student(id, name, phone, major);
        studentslist.add(student);
        saveStudents();
    }

    @SuppressWarnings("unchecked")
    public void display() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
             studentslist = (ArrayList<Student>) ois.readObject();
            if (studentslist != null) {
                for (Student student : studentslist) {
                    System.out.println("");
                    System.out.println(student);
                    System.out.println("");
                }
            }
           
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public int getlastid() {
        int lastId = 0; // Default value if no students are found
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            studentslist = (ArrayList<Student>) ois.readObject();
            if (studentslist != null && !studentslist.isEmpty()) {
                // Iterate through the students and update lastId with the ID of the last student
                for (Student student : studentslist) {
                    lastId = student.getId();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
        return lastId;
    }
    

    @SuppressWarnings("unchecked")
    public void delete_student() {
        System.out.println("Enter the id of the student you want to delete: ");
        int id = scan.nextInt();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            studentslist = (ArrayList<Student>) ois.readObject();
            if (studentslist != null) {
                for (Student student : studentslist) {
                    if (student.getId() == id) {
                        studentslist.remove(student);
                        System.out.println("Student deleted successfully!");
                        break;
                    }
                }
            }
            saveStudents();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading students: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            studentslist = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }


    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(studentslist);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public void edit_student(){
        System.out.println("Enter the id of the student you want to edit: ");
        int id = scan.nextInt();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) { //input is for reading 
             studentslist = (ArrayList<Student>) ois.readObject();//takes the list of students and puts the value in the txt file in it
            if (studentslist != null) {
                for (Student student : studentslist) {
                    if (student.getId() == id) {
                        System.out.println("Enter new name: ");
                        String name = scan.next();
                        System.out.println("Enter new phone number: ");
                        long phone = scan.nextLong();
                        System.out.println("Enter new major: ");
                        String major = scan.next();
                        student.setName(name);
                        student.setphone(phone);
                        student.setMajor(major);
                        System.out.println("Student edited successfully!");
                        break;
                    }
                }
            }
            saveStudents();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading students: " + e.getMessage());    
            }
    }
}

