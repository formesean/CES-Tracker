public class Admin extends User {
    private String position;

    Admin(int uniqueID, String fullName, String email, String password, int idNumber, String type, int cesPoints, String position) {
        super(uniqueID, fullName, email, password, idNumber, type, cesPoints);
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
