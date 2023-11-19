import java.util.ArrayList;

public class User {
    private String uniqueID;
    private String fullName;
    private String email;
    private int idNumber;
    private String type;
    private int cesPoints;

    public User() {
    }

    User(String uniqueID, String fullName, String email, int idNumber, String type, int cesPoints) {
        this.uniqueID = uniqueID;
        this.fullName = fullName;
        this.email = email;
        this.idNumber = idNumber;
        this.type = type;
        this.cesPoints = cesPoints;
    }

    public String getUniqueID() {
        return this.uniqueID;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmail() {
        return this.email;
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

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
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
