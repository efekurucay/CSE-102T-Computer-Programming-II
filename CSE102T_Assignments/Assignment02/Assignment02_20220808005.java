/**---------------------------------------------------

 * Akdeniz University CSE102T Assignments

 * @author: Yahya Efe Kuruçay

 * @since: 29.02.2024

 * Description: Assignment01

 * Score: ?

 * Website: https://efekurucay.com

*---------------------------------------------------*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 *    ███████╗███████╗███████╗
 *    ██╔════╝██╔════╝██╔════╝
 *    █████╗  █████╗  █████╗  
 *    ██╔══╝  ██╔══╝  ██╔══╝  
 *    ███████╗██║     ███████╗
 *    ╚══════╝╚═╝     ╚══════╝
 *                            
 */
public class Assignment02_20220808005 {

    
}

class Department {
    String Code;//1. Must be 3 or 4 characters 
    String Name;
    Teacher Chair;//1. Throws DepartmentMismatchException if Teacher is not in this department

Department(String code, String name){}

public String getCode() {
    return Code;
}
public void setCode(String code) {
    Code = code;
}
public String getName() {
    return Name;
}
public void setName(String name) {
    Name = name;
}
public Teacher getChair() {
    return Chair;
}
public void setChair(Teacher chair) {
    Chair = chair;
}

}
class Course {




    Department Department; // (replaces Department Code)
    Teacher Teacher;
    private String depCode; //Must be 3 or 4 characters
    private int courseNum; //Must be in the range 100-999 or 5000-5999 or 7000-7999 
    private String title;
    private String description;
    private int AKTS; //must be positive

    //Constructor Method
    Course(String depCode, int courseNumber, String title, String description, int AKTS,Teacher teacher) throws Exception {

        if(!validAKTS(AKTS))throw new Exception("ERROR: AKTS must be positive");
        if(!validCourseNum(courseNumber))throw new Exception("ERROR: Invalid Course Number");
        if(!validDepCode(depCode))throw new Exception("ERROR: Invalid Department Name");
        //if()throw new DepartmentMismatchException
        this.depCode = depCode;
        this.courseNum = courseNumber;
        this.title = title;
        this.description = description;
        this.AKTS = AKTS;

        
    }
    public Department getDepartment() {
        return Department;
    }
    public void setDepartment(Department department) {
        Department = department;
    }
    public Teacher getTeacher() {
        return Teacher;
    }
    public void setTeacher(Teacher teacher) {
        Teacher = teacher;
    }
    
    public String getDepCode() {
        return depCode;
    }
    public void setDepCode(String depCode) throws Exception {
        

        if(validDepCode(depCode)){this.depCode = depCode;}
        else{ throw new Exception("ERROR: Invalid Department Name"); }
    }

    public static boolean validDepCode(String dep){
        int l = dep.length();

        if(l==3 || l==4){return true;}
        else{return false;}

    }
    
    public int getCourseNum() {
        return courseNum;
    }
     
    public void setCourseNum(int courseNum) throws Exception {
       
        if (validCourseNum(courseNum)) 
        this.courseNum = courseNum;
        else  
        throw new Exception("ERROR: Invalid Course Number");
    }
 
    public boolean validCourseNum(int courseNum){
        int c = courseNum;
        if(c>=100&&c<=999|| c>=5000 &&c<=5999 || c>= 7000 && c<=7999  ) return true;

        else return false;



    }


    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAKTS() {
        return AKTS;
    }

    public void setAKTS(int aKTS) throws Exception {
        
        if(validAKTS(aKTS))
        AKTS = aKTS;

        else throw new Exception("ERROR: AKTS must be positive");

    }

    public boolean validAKTS(int AKTS){

        return AKTS>0;
    }


    public String courseCode(){return depCode + courseNum;}


    @Override
    public String toString() {
        return courseCode() + " - " + title + " (" + AKTS + ")";
    }


}


abstract class Person{


    Department Department;
    private String name;

    private String email;//Must be of the form {username}@{university name}.{domain} 
    private long ID;
    private String departmentCode;//Must be 3 or 4 characters 

    Person(String name, String email, long ID, String departmentCode) throws Exception {
        if(!validEmail(email))throw new IllegalArgumentException("ERROR: Invalid Email Address");
        if(!Course.validDepCode(departmentCode))throw new IllegalArgumentException("ERROR: Invalid department code");

    
        this.name = name;
        this.email = email;
        this.ID = ID;
        this.departmentCode = departmentCode;
    }

    public Department getDepartment() {
        return Department;
    }

    public void setDepartment(Department department) {
        Department = department;
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

    public void setEmail(String email) throws Exception {
        if (validEmail(email))
            this.email = email;
        else
            throw new IllegalArgumentException("ERROR: Invalid Email Address");
    }


   

public boolean validEmail(String email) {
    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
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

    public void setDepartmentCode(String departmentCode) throws Exception {

        if(Course.validDepCode(departmentCode))
        this.departmentCode = departmentCode;
        else throw new Exception("ERROR: Invalid department code");
    
    }
 

    @Override
    public String toString() {
        return name + " (" + ID + ") - " + email;
    }
}

class Teacher extends Person{
    private int rank;

    Teacher(String name, String email, long number, String departmentCode, int rank) throws Exception {
        super(name, email, number, departmentCode);
        if (!validRank(rank))throw new IllegalArgumentException("ERROR: Invalid rank value."); 
          
         
        this.rank = rank;
    }

    public void setRank(int rank) {
        if (validRank(rank)) {
            this.rank = rank;
        } else {
            throw new IllegalArgumentException("ERROR: Invalid rank value.");
        }
    }

    public boolean validRank(int rank){

        if (rank >= 1 && rank <= 4) {
            return true;
        } else {
            return false;
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

    @Override
    public String toString() {
        return getTitle() + " " + super.toString();
    }
}





















