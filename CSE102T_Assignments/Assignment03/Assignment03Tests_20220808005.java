import org.junit.*;
import static org.junit.Assert.*;

public class Assignment03Tests_20220808005 {
   
   
   
    //       ├─ shouldNotCreateDepartmentWithInvalidID ✔
    @Test
void shouldNotCreateDepartmentWithInvalidID() {
    try {
        new Department("CS", "Computer Science");
        fail("Should have thrown InvalidCodeException");
    } catch (InvalidCodeException e) {
        assertEquals("invalidCodeException: Department - code CS (length must be 3 or 4)", e.toString());
    }
}
    // │     ├─ shouldNotCreateCourseWithDifferentDepartment ✔
    @Test
void shouldNotCreateCourseWithDifferentDepartment() {
        Department cse = new Department("CSE", "Computer Science Engineering");
        Department eee = new Department("EEE", "Electrical and Electronics Engineering");
        Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
        
        try {
            new Course(eee, 101, "Physics", "Intro to Physics", 4, t);
            fail("Should have thrown DepartmentMismatchException");
        } catch (DepartmantMismatchException e) {
            assertEquals("DepartmentMismatchException: John Doe (123) cannot teach EEE101 because currently assigned to CSE", e.toString());
        }
    }
    // │     ├─ departmentShouldNotSetChairDifferentDepartment ✘ Should have thrown DepartmentMismatchException
    @Test
void departmentShouldNotSetChairDifferentDepartment() {
        Department cse = new Department("CSE", "Computer Science Engineering");
        Department eee = new Department("EEE", "Electrical and Electronics Engineering");
        Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
        
        try {
            eee.setChair(t);
            fail("Should have thrown DepartmentMismatchException");
        } catch (DepartmantMismatchException e) {
            assertEquals("DepartmentMismatchException: John Doe (123) cannot be chair of EEE because currently assigned to CSE", e.toString());
        }
    }
    // │     ├─ DepartmentMismatchExceptionShouldHaveDepartmentTeacherConstructor ✔
    @Test
void DepartmentMismatchExceptionShouldHaveDepartmentTeacherConstructor() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Course course = new Course(new Department("EEE", "Electrical and Electronics Engineering"), 101, "Physics", "Intro to Physics", 4, t);
    
    try {
        throw new DepartmantMismatchException(course, t);
    } catch (DepartmantMismatchException e) {
        assertEquals("DepartmentMismatchException: John Doe (123) cannot teach EEE101 because currently assigned to CSE", e.toString());
    }
}
    // │     ├─ shouldGetGPA ✔
    @Test
void shouldGetGPA() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Student s = new Student("Alice", "alice@example.com", 456L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);
    Course c102 = new Course(cse, 102, "Programming 2", "Object-Oriented Programming", 4, t);

    s.addCourse(c101, 85); // 6 AKTS
    s.addCourse(c102, 75); // 4 AKTS

    assertEquals(3.4, s.getGPA(), 0.001); // 3.4 GPA bekleniyor
}
    // │     ├─ shouldCourseResultThrowCourseNotFoundException ✘ Should have thrown CourseNotFoundException
    @Test
void shouldCourseResultThrowCourseNotFoundException() {
    Student student = new Student("Bob", "bob@example.com", 5678L, new Department("CSE", "Computer Science Engineering"));
    
    try {
        student.courseResult(new Course(new Department("CSE", "Computer Science Engineering"), 101, "Programming 1", "Introduction to Programming", 6, new Teacher("John Doe", "john.doe@example.com", 123L, new Department("CSE", "Computer Science Engineering"), 1)));
        fail("Should have thrown CourseNotFoundException");
    } catch (CourseNotFoundException e) {
        assertEquals("CourseNotFoundException: 5678 has not yet taken CSE101", e.toString());
    }
}
    // │     ├─ shouldAddCourse ✔
    @Test
void shouldAddCourse() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Student s = new Student("Alice", "alice@example.com", 456L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    s.addCourse(c101, 85); // Dersi ve notu ekle

    assertEquals(1, s.getCourses().size()); // Öğrencinin ders listesinde 1 ders olmalı
    assertEquals(1, s.getGrades().size()); // Öğrencinin not listesinde 1 not olmalı
    assertEquals(85.0, s.getGrades().get(0)); // Eklenen notun doğru olduğunu kontrol et
}

    // │     ├─ departmentShouldSetChair ✔
    @Test
void departmentShouldSetChair() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher chair = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);

    cse.setChair(chair); // Bölümün başkanını atayın

    assertEquals(chair, cse.getChair()); // Başkanın doğru bir şekilde atandığını kontrol et
}

    // │     ├─ shouldCreateCourse ✔
    @Test
void shouldCreateCourse() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);

    Course course = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    assertEquals(cse, course.getDepartment()); // Dersin bölümünün doğru atanıp atanmadığını kontrol et
    assertEquals(101, course.getCourseNumber()); // Dersin numarasının doğru atanıp atanmadığını kontrol et
    assertEquals("Programming 1", course.getTitle()); // Dersin başlığının doğru atanıp atanmadığını kontrol et
    assertEquals("Introduction to Programming", course.getDescription()); // Dersin açıklamasının doğru atanıp atanmadığını kontrol et
    assertEquals(6, course.getAKTS()); // Dersin AKTS'nin doğru atanıp atanmadığını kontrol et
    assertEquals(t, course.getTeacher()); // Dersin öğretmeninin doğru atanıp atanmadığını kontrol et
}

    // │     ├─ shouldNotCreateGradStudent ✔
    @Test
void shouldNotCreateGradStudent() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    
    try {
        new GradStudent("Alice", "alice@example.com", 456L, cse, 4, "MDE");
        fail("Should have thrown InvalidRankException");
    } catch (InvalidRankException e) {
        assertEquals("InvalidRankException: 4", e.toString());
    }
}

    // │     ├─ shouldCreateStudent ✔
    @Test
void shouldCreateStudent() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    
    Student student = new Student("Alice", "alice@example.com", 456L, cse);

    assertEquals("Alice", student.getName()); // Öğrencinin adının doğru atanıp atanmadığını kontrol et
    assertEquals("alice@example.com", student.getEmail()); // Öğrencinin e-postasının doğru atanıp atanmadığını kontrol et
    assertEquals(456L, student.getId()); // Öğrencinin kimlik numarasının doğru atanıp atanmadığını kontrol et
    assertEquals(cse, student.getDepartment()); // Öğrencinin bölümünün doğru atanıp atanmadığını kontrol et
}


    // │     ├─ teachershouldSetDepartmentAndChair ✘ Should set department expected:<Department@4e32a067> but was:<Department@4a0d2d79>
    @Test
void teachershouldSetDepartmentAndChair() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Department eee = new Department("EEE", "Electrical and Electronics Engineering");
    Teacher teacher = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);

    teacher.setDepartment(eee); // Öğretmenin bölümünü değiştir

    assertNull(cse.getChair()); // Önceki bölümde başkanlık pozisyonunun boş olup olmadığını kontrol et
    assertEquals(teacher, eee.getChair()); // Yeni bölümde başkanlık pozisyonunun öğretmene atanıp atanmadığını kontrol et
}

    // │     ├─ gradStudentCourseResultThrowException ✘ Index 0 out of bounds for length 0
    @Test
void gradStudentCourseResultThrowException() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    GradStudent gradStudent = new GradStudent("Bob", "bob@example.com", 789L, cse, 1, "Software Engineering");
    
    try {
        gradStudent.courseResult(new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1)));
        fail("Should have thrown CourseNotFoundException");
    } catch (CourseNotFoundException e) {
        assertEquals("CourseNotFoundException: 789 has not yet taken CSE101", e.toString());
    }
}

    // │     ├─ shouldGetCourseGPAPoints ✔
@Test
void shouldGetCourseGPAPoints() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Student s = new Student("Alice", "alice@example.com", 456L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    s.addCourse(c101, 85); // Ders notu ekle

    assertEquals(4.0, s.courseGPAPoints(c101), 0.001); // Ders notunun GPA'sını kontrol et
}

    
    // │     ├─ courseShouldNotSetTeacherFromDifferentDpt ✔

    @Test
void courseShouldNotSetTeacherFromDifferentDpt() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Department eee = new Department("EEE", "Electrical and Electronics Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, eee, 1);

    try {
        new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);
        fail("Should have thrown DepartmantMismatchException");
    } catch (DepartmantMismatchException e) {
        assertEquals("DepartmentMismatchException: John Doe (123) cannot teach CSE101 because currently assigned to EEE", e.toString());
    }
}
    // │     ├─ shouldReplaceCourse ✘ expected:<11> but was:<17>
    @Test
void shouldReplaceCourse() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Student s = new Student("Alice", "alice@example.com", 456L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);
    Course c102 = new Course(cse, 102, "Programming 2", "OOP", 4, t);

    s.addCourse(c101, 85); // Dersi ekle
    s.addCourse(c102, 70); // Diğer dersi ekle

    s.addCourse(c101, 90); // İlk dersi güncelle

    assertEquals(1, s.getCourses().size()); // Öğrencinin ders listesinde hala 1 ders olmalı
    assertEquals(90.0, s.getGrades().get(0)); // Güncellenen notun doğru olduğunu kontrol et
}

    // │     ├─ shouldGetCourseGradeLetter ✔
    @Test
void shouldGetCourseGradeLetter() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Student s = new Student("Alice", "alice@example.com", 456L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    s.addCourse(c101, 85); // Ders notu ekle

    assertEquals("BA", s.courseGradeLetter(c101)); // Ders notunun harf notunu kontrol et
}

    // │     ├─ shouldCreateTeacher ✔
    @Test
void shouldCreateTeacher() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    
    Teacher teacher = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);

    assertEquals("John Doe", teacher.getName()); // Öğretmenin adının doğru atanıp atanmadığını kontrol et
    assertEquals("john.doe@example.com", teacher.getEmail()); // Öğretmenin e-postasının doğru atanıp atanmadığını kontrol et
    assertEquals(123L, teacher.getId()); // Öğretmenin kimlik numarasının doğru atanıp atanmadığını kontrol et
    assertEquals(cse, teacher.getDepartment()); // Öğretmenin bölümünün doğru atanıp atanmadığını kontrol et
    assertEquals("Teaching Assistant", teacher.getTitle()); // Öğretmenin başlık bilgisinin doğru atanıp atanmadığını kontrol et
}

    // │     ├─ shouldCreateDepartment ✔
    @Test
void shouldCreateDepartment() {
    Department cse = new Department("CSE", "Computer Science Engineering");

    assertEquals("CSE", cse.getCode()); // Bölümün kodunun doğru atanıp atanmadığını kontrol et
    assertEquals("Computer Science Engineering", cse.getName()); // Bölümün adının doğru atanıp atanmadığını kontrol et
    assertNull(cse.getChair()); // Bölüm başkanının başlangıçta atanmamış olup olmadığını kontrol et
}

    // │     ├─ teachershouldSetDepartment ✘ Should set department expected:<Department@6770946d> but was:<Department@69b776f3>
    @Test
void teachershouldSetDepartment() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Department eee = new Department("EEE", "Electrical and Electronics Engineering");
    Teacher teacher = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);

    teacher.setDepartment(eee); // Öğretmenin bölümünü değiştir

    assertEquals(eee, teacher.getDepartment()); // Öğretmenin bölümünün doğru atanıp atanmadığını kontrol et
}

    // │     ├─ teacherShouldPromoteDemote ✔
    @Test
void teacherShouldPromoteDemote() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher teacher = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 2);

    teacher.promote(); // Öğretmenin rütbesini yükselt
    assertEquals(3, teacher.rank); // Öğretmenin rütbesinin doğru yükseltilip yükseltilmediğini kontrol et

    teacher.demote(); // Öğretmenin rütbesini düşür
    assertEquals(2, teacher.rank); // Öğretmenin rütbesinin doğru düşürülüp düşürülmediğini kontrol et
}

    // │     ├─ shouldGetGPAwithOneFailedCourse ✔
    @Test
void shouldGetGPAwithOneFailedCourse() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Student s = new Student("Alice", "alice@example.com", 456L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);
    Course c102 = new Course(cse, 102, "Programming 2", "OOP", 4, t);

    s.addCourse(c101, 85); // Birinci ders notu ekle
    s.addCourse(c102, 40); // İkinci dersi başarısız notla ekle

    assertEquals(1.5, s.getGPA(), 0.001); // Öğrencinin GPA'sını kontrol et
}

    // │     ├─ shouldGetCourseResultFailed ✔
    @Test
void shouldGetCourseResultFailed() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Student s = new Student("Alice", "alice@example.com", 456L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);
    Course c102 = new Course(cse, 102, "Programming 2", "OOP", 4, t);

    s.addCourse(c101, 85); // Birinci ders notu ekle
    s.addCourse(c102, 40); // İkinci dersi başarısız notla ekle

    assertEquals("Failed", s.courseResult(c102)); // Öğrencinin başarısız bir dersi doğru şekilde geçip geçmediğini kontrol et
}

    // │     ├─ DepartmentMismatchExceptionShouldHaveCourseTeacherConstructor ✔
    @Test
void DepartmentMismatchExceptionShouldHaveCourseTeacherConstructor() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    try {
        throw new DepartmantMismatchException(c101, t);
    } catch (DepartmantMismatchException e) {
        assertEquals("DepartmentMismatchException: John Doe (123) cannot teach CSE101 because currently assigned to CSE", e.toString());
    }
}

    // │     ├─ courseMutators ✔
    @Test
void courseMutators() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    // Department değiştir
    Department eee = new Department("EEE", "Electrical and Electronics Engineering");
    c101.setDepartment(eee);
    assertEquals(eee, c101.getDepartment());

    // Ders numarası değiştir
    c101.setCourseNumber(102);
    assertEquals(102, c101.getCourseNumber());

    // Ders başlığı değiştir
    c101.setTitle("Programming 2");
    assertEquals("Programming 2", c101.getTitle());

    // Ders açıklaması değiştir
    c101.setDescription("Object Oriented Programming");
    assertEquals("Object Oriented Programming", c101.getDescription());

    // AKTS değiştir
    c101.setAKTS(4);
    assertEquals(4, c101.getAKTS());
}

    // │     ├─ teacherShouldNotDemote ✔
    @Test
void teacherShouldNotDemote() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher teacher = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);

    // En düşük rütbede olduğunda rütbesini düşürmeye çalış
    try {
        teacher.demote();
        fail("Should have thrown InvalidRankException");
    } catch (InvalidRankException e) {
        // Beklenen davranış
    }

    // Rütbesinin düşmediğini doğrula
    assertEquals(1, teacher.rank);
}

    // │     ├─ gradStudentShouldGetCourseGPAPoints ✔
    @Test
void gradStudentShouldGetCourseGPAPoints() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    GradStudent gradStudent = new GradStudent("Alice", "alice@example.com", 456L, cse, 1, "Thesis Topic");
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    // Öğrencinin ders notunu ekleyin
    gradStudent.addCourse(c101, 85);

    // GPAPuanlarını kontrol edin
    assertEquals(4.0, gradStudent.courseGPAPoints(c101));
}

    // │     ├─ gradStudentShouldGetCourseResult ✔
    @Test
void gradStudentShouldGetCourseResult() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    GradStudent gradStudent = new GradStudent("Alice", "alice@example.com", 456L, cse, 1, "Thesis Topic");
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    // Öğrencinin ders notunu ekleyin
    gradStudent.addCourse(c101, 85);

    // Ders sonucunu kontrol edin
    assertEquals("Passed", gradStudent.courseResult(c101));
}

    // │     ├─ teacherShouldNotPromote ✔
    @Test
void teacherShouldNotPromote() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher teacher = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 5);

    // En yüksek rütbede olduğunda rütbesini yükseltmeye çalış
    try {
        teacher.promote();
        fail("Should have thrown InvalidRankException");
    } catch (InvalidRankException e) {
        // Beklenen davranış
    }

    // Rütbesinin yükselmediğini doğrula
    assertEquals(5, teacher.rank);
}

    // │     ├─ gradStudentShouldGetCourseGradeLetter ✔
    @Test
void gradStudentShouldGetCourseGradeLetter() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    GradStudent gradStudent = new GradStudent("Alice", "alice@example.com", 456L, cse, 1, "Thesis Topic");
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    // Öğrencinin ders notunu ekleyin
    gradStudent.addCourse(c101, 85);

    // Harf notunu kontrol edin
    assertEquals("AA", gradStudent.courseGradeLetter(c101));
}

    // │     ├─ shouldThrowInvalidGradeException ✔
    @Test
void shouldThrowInvalidGradeException() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Student student = new Student("Jane Doe", "jane.doe@example.com", 456L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, null);

    // Geçersiz bir not ekleyin
    try {
        student.addCourse(c101, 105); // Geçersiz bir not
        fail("Should have thrown InvalidGradeException");
    } catch (InvalidGradeException e) {
        // Beklenen davranış
    }
}

    // │     ├─ departmentAccessors ✔
    @Test
void departmentAccessors() {
    // Department nesnesi oluştur
    Department department = new Department("CSE", "Computer Science Engineering");

    // Kodu kontrol et
    assertEquals("CSE", department.getCode());

    // İsmi kontrol et
    assertEquals("Computer Science Engineering", department.getName());

    // Başkanı kontrol et (başlangıçta null olmalı)
    assertNull(department.getChair());
}

    // │     ├─ departmentShouldNotSetInvalidID ✔
    @Test
void departmentShouldNotSetInvalidID() {
    // Geçersiz bir departman kodu ile departman oluşturmayı deneyin
    try {
        Department department = new Department("CS", "Computer Science");
        fail("Should have thrown InvalidCodeException");
    } catch (InvalidCodeException e) {
        // Beklenen davranış
    }
}

    // │     ├─ shouldCreateGradStudent ✔
    @Test
void shouldCreateGradStudent() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    GradStudent gradStudent = new GradStudent("Alice", "alice@example.com", 456L, cse, 1, "Thesis Topic");

    // Yüksek lisans öğrencisinin adını kontrol et
    assertEquals("Alice", gradStudent.getName());

    // Yüksek lisans öğrencisinin e-posta adresini kontrol et
    assertEquals("alice@example.com", gradStudent.getEmail());

    // Yüksek lisans öğrencisinin kimlik numarasını kontrol et
    assertEquals(456L, gradStudent.getId());

    // Yüksek lisans öğrencisinin bölümünü kontrol et
    assertEquals(cse, gradStudent.getDepartment());

    // Yüksek lisans öğrencisinin rütbesini kontrol et
    assertEquals(1, gradStudent.rank);

    // Yüksek lisans öğrencisinin tez konusunu kontrol et
    assertEquals("Thesis Topic", gradStudent.getThesisTopic());
}

    // │     ├─ courseAccessors ✔
    @Test
void courseAccessors() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Teacher t = new Teacher("John Doe", "john.doe@example.com", 123L, cse, 1);
    Course course = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);

    // Bölümü kontrol et
    assertEquals(cse, course.getDepartment());

    // Öğretmeni kontrol et
    assertEquals(t, course.getTeacher());

    // Ders numarasını kontrol et
    assertEquals(101, course.getCourseNumber());

    // Ders başlığını kontrol et
    assertEquals("Programming 1", course.getTitle());

    // Ders açıklamasını kontrol et
    assertEquals("Introduction to Programming", course.getDescription());

    // AKTS'yi kontrol et
    assertEquals(6, course.getAKTS());
}

    // │     ├─ shouldGetCourseResultConditionallyPassed ✔
    @Test
void shouldGetCourseResultConditionallyPassed() {
    Department cse = new Department("CSE", "Computer Science Engineering");
    Student student = new Student("John Doe", "john.doe@example.com", 123L, cse);
    Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, null);

    // Öğrencinin ders notunu ekleyin (55 puan, koşullu geçme sınırı olan 45'in üzerinde)
    student.addCourse(c101, 55);

    // Ders sonucunu kontrol edin
    assertEquals("Conditionally Passed", student.courseResult(c101));
}

    // │     └─ departmentMutators ✔
    
    @Test
void departmentMutators() {
    // Yeni bir Department nesnesi oluştur
    Department department = new Department("CSE", "Computer Science Engineering");

    // Kodu değiştir
    department.setCode("ECE");
    assertEquals("ECE", department.getCode());

    // İsmi değiştir
    department.setName("Electrical and Computer Engineering");
    assertEquals("Electrical and Computer Engineering", department.getName());

    // Başkanı atanmışsa, başkanı değiştir
    Teacher chair = new Teacher("Alice", "alice@example.com", 456L, department, 3);
    department.setChair(chair);
    assertEquals(chair, department.getChair());

    // Başkan atanmamışsa, başkanı null yap
    department.setChair(null);
    assertNull(department.getChair());
}

}