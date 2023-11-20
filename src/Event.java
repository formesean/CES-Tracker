public class Event {
    private String uniqueID;
    private String name;
    private String location;
    private String date;
    private String startTime;
    private String endTime;
    private String type;
    private String mode;
    private String roles;
    private String rolePoints;

    Event(String uniqueID, String name, String location, String date, String startTime, String endTime, String type, String mode, String roles, String rolePoints) {
        this.uniqueID = uniqueID;
        this.name = name;
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.mode = mode;
        this.roles = roles;
        this.rolePoints = rolePoints;
    }

    public String getUniqueID() {
        return this.uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRolePoints() {
        return this.rolePoints;
    }

    public void setRolePoints(String rolePoints) {
        this.rolePoints = rolePoints;
    }
}
