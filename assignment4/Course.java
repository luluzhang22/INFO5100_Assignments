
public class Course {
    private String title;

    private int numberOfStudent;

    private Student[] students = new Student[10];

    Course(String title){
        this.title = title;
    }

    public Student[] getStudents() {
        return students;
    }

    public boolean isFull(){
        return numberOfStudent == 10;
    }

    public void registerStudent(Student student){
        if(isFull()){
            System.out.println("The number of registered students for this course is full, " + student.getName() + " can't register for this course!");
            return;
        }
        students[numberOfStudent] = student;
        numberOfStudent++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }
}
