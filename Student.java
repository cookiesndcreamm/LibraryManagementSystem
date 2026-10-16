
public class Student extends LibraryUser {

    public Student(String userId, String name) {
        super(userId, name);
    }

    @Override
    public String getRole() {
        return "Student";
    }
}

