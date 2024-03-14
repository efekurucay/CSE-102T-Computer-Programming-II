public class Assignment01_20190808046 {
   
   
   
   
   
   
   
    public static class Course{
        private String Department;
        private int Number;
        private String Title;
        private int AKTS;

        public Course(String department, int number, String title, int AKTS) throws Exception {
            Department = department;
            Number = number;
            Title = title;
            this.AKTS = AKTS;
            if (validDepartment(Department)==false){
                throw new Exception("Error! Invalid Department Name");
            }
            if (validNumber(Number)==false){
                throw new Exception("Error! Invalid Number");
            }
            if (validAKTS(AKTS)==false){
                throw new Exception("Error! AKTS must be positive");
            }
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String department) throws Exception {
            Department = department;
            if (validDepartment(Department)==false){
                throw new Exception("Error! Invalid Department Name");
            }
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int number) throws Exception {
            Number = number;
            if (validNumber(Number)==false){
                throw new Exception("Error! Invalid Number");
            }
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public int getAKTS() {
            return AKTS;
        }

        public void setAKTS(int AKTS) throws Exception {
            this.AKTS = AKTS;
            if (validAKTS(AKTS)==false){
                throw new Exception("Error! AKTS must be positive");
            }
        }
        public String CourseCode(){
            return (getDepartment()+" "+getNumber());
        }

        public String toString() {
            String str= (getDepartment()+" "+getNumber()+" - "+getTitle()
                    +" ("+getAKTS()+")");
            return str;
        }
        public boolean validDepartment(String dep){
            if (dep.length()==3 ^ dep.length()==4 ){
                return true;
            }
            else
                return false;
        }
        public boolean validNumber(int num){
            if (num>=100 && num<=499 ){
                return true;
            }
            else if (num>=5000 && num<=5999){
                return true;
            }
            else if (num>=7000 && num<=7999){
                return true;
            }
            else
                return false;
        }
        public boolean validAKTS(int akts){
            if (akts>0){
                return true;
            }
            else
                return false;
        }
    }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    public static class Person{
        private String Name;
        private String Email;
        private long ID;
        private String Department;

        public Person(String name, String email, long ID, String department) throws Exception {
            Name = name;
            Email = email;
            this.ID = ID;
            Department = department;
            if (validDepartment(Department)==false){
                throw new Exception("Error! Invalid Department Name");
            }
            if (validEmail(Email)==false){
                throw new Exception("Error! Invalid Email Adress");
            }
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) throws Exception {
            Email = email;
            if (validEmail(Email)==false){
                throw new Exception("Error! Invalid Email Adress");
            }
        }

        public long getID() {
            return ID;
        }

        public void setID(long ID) {
            this.ID = ID;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String department) throws Exception {
            Department = department;
            if (validDepartment(Department)==false){
                throw new Exception("Error! Invalid Department Name");
            }

        }
        public String toString() {
            String str= (getName()+" ("+getID()+") - "+getEmail());
            return str;
        }
        public boolean validDepartment(String dep){
            if (dep.length()==3 ^ dep.length()==4 ){
                return true;
            }
            else
                return false;
        }
        public boolean validEmail(String mail){
            if (mail.contains("@")&& mail.contains(".")){
                return true;
            }
            else {return false;}
        }
    }
    
    class Teacher extends Person {
        private int Rank;

        public Teacher(String name, String email, long ID, String department, int rank) throws Exception {
            super(name, email, ID, department);
            Rank = rank;
            if (validRank(rank)==false){
                throw new Exception("Error! Invalid Rank");
            }
        }

        public int getRank() {

            return Rank;
        }

        public void setRank(int rank) throws Exception {
            Rank = rank;
            if (validRank(rank)==false){
                throw new Exception("Error! Invalid Rank");
            }
        }
        public String getTitle(){
            String Title="";
            if (getRank()==1){
                Title= "Assistant Professor";
            }
            else if (getRank()==2){
                Title= "Associate Professor";
            }
            else if (getRank()==3){
                Title= "Professor";
            }
            return Title;
        }
        public void promote() throws Exception {
            if (getRank()==1){
                setRank(2);
            }
            else if(getRank()==2){
                setRank(3);
            }
        }
        public void demote() throws Exception {
            if (getRank()==2){
                setRank(1);
            }
            else if(getRank()==3){
                setRank(2);
            }
        }
        public String toString() {
            String str= (getTitle()+getName()+" ("+getID()+") - "+getEmail());
            return str;
        }
        public boolean validRank(int rank){
            if (rank>=1 && rank<=4){
                return true;
            }
            else{return false;}
        }

    }
    
    class Student extends Person {
        private int AKTS;

        public Student(String name, String email, long ID, String department, int AKTS) throws Exception {
            super(name, email, ID, department);
            this.AKTS = AKTS;
            if (validAKTS(AKTS)==false){
                throw new Exception("Error! Invalid AKTS");
            }
        }

        public int getAKTS() {

            return AKTS;
        }

        public void setAKTS(int AKTS) throws Exception {
            this.AKTS = AKTS;
            if (validAKTS(AKTS)==false){
                throw new Exception("Error! Invalid AKTS");
            }
        }
        public boolean validAKTS(int akts){
            if (akts>=0){
                return true;
            }
            else
                return false;
        }

    }
    
    class GradStudent extends Student{
        private String Thesis;

        public GradStudent(String name, String email, long ID, String department, int AKTS, String thesis) throws Exception {
            super(name, email, ID, department, AKTS);
            Thesis = thesis;
        }

        public String getThesis() {
            return Thesis;
        }

        public void setThesis(String thesis) {
            Thesis = thesis;
        }

    }


    public static void main(String[] args) throws Exception {
Course c=new Course("CSE",101,"Programming 2",6);
Person durum= new Person("Ahmet","Ahmetdrm@gmail.com",201909,"Ada");
        System.out.println(durum);
        c.setDepartment("cse");


    }
}
