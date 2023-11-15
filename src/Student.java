public class Student extends User {
    private String yearLevel;

    Student(int uniqueID, String fullName, String email, String password, int idNumber, String type, int cesPoints, String yearLevel) {
        super(uniqueID, fullName, email, password, idNumber, type, cesPoints);
        this.yearLevel = yearLevel;
    }

    public String getYearLevel() {
        return this.yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }
}
