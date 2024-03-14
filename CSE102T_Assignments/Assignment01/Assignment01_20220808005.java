
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
       Course c = new Course("CSE", 102, "Programming 2", "Introduction to OOP", 6);
       System.out.println(c.courseCode()+" - "+c.getTitle());
       System.out.println(c);

       Teacher t = new Teacher("Joseph LEDET","josephledet@akdeniz.edu.tr",123L,"CSE",1);
       System.out.println(t);

       Student s = new Student("Yahya Efe Kurucay", "contact@efekurucay.com", 456L, "CSE");
       System.out.println(s);

       s.passCourse(c);
       System.out.println(s.getAKTS());

       System.out.println("---------");

       Course course = new Course("CSE", 101, "Computer Programming 1", "Introduction to Programming", 6);
       Student student = new Student("Yahya Efe", "yahya@efekurucay.com",123L, "CSE");
       student.passCourse(course);
       course.setCourseNumber(course.getCourseNumber()+10);
       System.out.println(student);
       System.out.println(course);
       course = new Course("CSE", 102, "Computer Programming 2", "Introduction to OOP", 4);
       student.passCourse(course);
       course.setCourseNumber(course.getCourseNumber()-10);
       System.out.println(course);
       System.out.println(student);



    }
}//class Assignment01



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


}
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
    private int AKTS;

    public Student(String name, String email, long ID, String departmentCode) {
        super(name, email, ID, departmentCode);
        this.AKTS = 0;
    }

    public int getAKTS() {
        return AKTS;
    }

    public void passCourse(Course course) {
        AKTS += course.getAKTS();
    }
}//Student class 


//GradStudent class
class GradStudent extends Student{

    private int rank;
    private String thesisTopic;

    public GradStudent(String name, String email, long ID, String departmentCode, int rank, String thesisTopic) {
        super(name, email, ID, departmentCode);
        this.rank = rank;
        this.thesisTopic = thesisTopic;
    }

    public void setRank(int rank) {
        if (rank == 1 || rank == 2 || rank == 3) {
            this.rank = rank;
        } else {
            throw new IllegalArgumentException("Invalid rank value.");
        }
    }

    public String getLevel() {
        switch (rank) {
            case 1:
                return "Master's Student";
            case 2:
                return "Doctoral Student";
            case 3:
                return "Doctoral Candidate";
            default:
                return "";
        }
    }

    public String getThesisTopic() {
        return thesisTopic;
    }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    public String toString() {
        return getLevel() + " - " + super.toString();
    }

}//GradStudent class 



