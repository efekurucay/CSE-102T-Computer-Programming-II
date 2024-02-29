
/**---------------------------------------------------

 * Akdeniz University CSE102T Assignments

 * @author: Yahya Efe Kuruçay

 * @since: 29.02.2024

 * Description: Assignment01

 * Score: ?

 * Website: https://efekurucay.com

*---------------------------------------------------*/

public class Assignment01_20220808005 {
    public static void main(String[] args) {
        Course cor= new Course("CSE", 102, null, null, 0);
       
        System.out.println( cor.getDepartmentCode()+cor.getCourseNumber());
    }
}//class Assignment01

//Course class
class Course{

    private String depCode; //Must be 3 or 4 characters
    private int courseNum; //Must be in the range 100-999 or 5000-5999 or 7000-7999 
    private String title;
    private String description;
    private int AKTS; //must be positive

    //Constructor Method
    public Course(String depCode, int courseNumber, String title, String description, int AKTS) {
        this.depCode = depCode;
        this.courseNum = courseNumber;
        this.title = title;
        this.description = description;
        this.AKTS = AKTS;
    }

    String getDepartmentCode(){return depCode;}
    void setDepartmentCode(String departmentCode){this.depCode = departmentCode;}

    int getCourseNumber(){return courseNum;}
    void setCourseNumber(int number){this.courseNum=number;}

    String getTitle(){return title;}
    void setTitle(String title){this.title=title;}

    String getDescription(){return description;}
    void setDescription(String description){this.description=description;}

    int getAKTS(){return AKTS;}
    void setAKTS(int AKTS){this.AKTS=AKTS;}

    String courseCode(){return depCode + courseNum;}

    public String toString() {
        return courseCode() + " - " + title + " (" + AKTS + ")";
}




}//Course class

//Person class
class Person{

    private String name;
    private String email;//Must be of the form {username}@{university name}.{domain} 
    private long ID;
    private String departmentCode;//Must be 3 or 4 characters 

    public Person(String name, String email, long ID, String departmentCode) {
        this.name = name;
        this.email = email;
        this.ID = ID;
        this.departmentCode = departmentCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String toString() {
        return name + " (" + ID + ") - " + email;
    }


}//Person class


//Teacher class 
class Teacher extends Person{
    private int rank;

    public Teacher(String name, String email, long ID, String departmentCode, int rank) {
        super(name, email, ID, departmentCode); //efekurucay24
        this.rank = rank;
    }

    public void setRank(int rank) {
        if (rank >= 1 && rank <= 4) {
            this.rank = rank;
        } else {
            throw new IllegalArgumentException("Invalid rank value.");
        }
    }

    public String getTitle() {
        switch (rank) {
            case 1:
                return "Lecturer";
            case 2:
                return "Assistant Professor";
            case 3:
                return "Associate Professor";
            case 4:
                return "Professor";
            default:
                return "";
        }
    }

    public void promote() {
        if (rank < 4) {
            rank++;
        }
    }

    public void demote() {
        if (rank > 1) {
            rank--;
        }
    }

    public String toString() {
        return getTitle() + " - " + super.toString();
    }
}//Teacher class 


//Student class 
class Student extends Person{

}//Student class 


//GradStudent class
class GradStudent extends Student{

}//GradStudent class 



