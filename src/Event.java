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
        return uniqueID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getType() {
        return type;
    }

    public String getMode() {
        return mode;
    }

    public String getRoles() {
        return roles;
    }

    public String getRolePoints() {
        return rolePoints;
    }
}