public class User {
    private int uniqueID;
    private String fullName;
    private String email;
    private String password;
    private int idNumber;
    private String type;
    private int cesPoints;

    User(int uniqueID, String fullName, String email, String password, int idNumber, String type, int cesPoints) {
        this.uniqueID = uniqueID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.idNumber = idNumber;
        this.type = type;
        this.cesPoints = cesPoints;
    }

    public int getUniqueID() {
        return this.uniqueID;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public int getIDNumber() {
        return this.idNumber;
    }

    public String getType() {
        return this.type;
    }

    public int getCESPoints() {
        return this.cesPoints;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCESPoints(int cesPoints) {
        this.cesPoints = cesPoints;
    }
}
