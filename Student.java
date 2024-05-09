import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L; // Added for serialization
    private int id;
    String name ;
    private long phone;
    private String major;

    public Student( int id,String name, long phone, String major) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.major = major;

    }
 
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getphone() {
        return phone;
    }

    public void setphone(long phone) {
        this.phone = phone;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student [id= " + id + "  ,  name= " + name + ",  phone=  " + phone + "  ,  major=  " + major + "  ]";
    }


}
