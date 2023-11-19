public class Admin extends User {
    Admin() {
        super();
    }

    Admin(String uniqueID, String fullName, String email, int idNumber, String type, int cesPoints) {
        super(uniqueID, fullName, email, idNumber, type, cesPoints);
    }
}
