
public class Librarian extends LibraryUser {

    public Librarian(String userId, String name) {
        super(userId, name);
    }

    @Override
    public String getRole() {
        return "Librarian";
    }
}
