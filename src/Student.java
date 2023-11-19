public class Student extends User {
    private String yearLevel;

    Student() {
        super();
    }

    Student(String uniqueID, String fullName, String email, int idNumber, String type, int cesPoints, String yearLevel) {
        super(uniqueID, fullName, email, idNumber, type, cesPoints);
        this.yearLevel = yearLevel;
    }

    public String getYearLevel() {
        return this.yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }
}
