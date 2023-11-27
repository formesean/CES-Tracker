import javax.swing.*;
import java.sql.Timestamp;

public class EvaluationForm {
    private String evalformID;
    private String userID;
    private String eventID;
    private String qOne;
    private String qTwo;
    private String qThree;
    private String qFour;
    private String qFive;
    private String role;
    private String rolePoints;
    private String rating;
    private ImageIcon beginningImg;
    private ImageIcon middleImg;
    private ImageIcon endImg;
    private Timestamp submitted_at;

    public EvaluationForm(String evalformID, String userID, String eventID, String qOne, String qTwo, String qThree, String qFour, String qFive, String role, String rolePoints,String rating, ImageIcon beginningImg, ImageIcon middleImg, ImageIcon endImg, Timestamp submitted_at) {
        this.evalformID = evalformID;
        this.userID = userID;
        this.eventID = eventID;
        this.qOne = qOne;
        this.qTwo = qTwo;
        this.qThree = qThree;
        this.qFour = qFour;
        this.qFive = qFive;
        this.role = role;
        this.rolePoints = rolePoints;
        this.rating = rating;
        this.beginningImg = beginningImg;
        this.middleImg = middleImg;
        this.endImg = endImg;
        this.submitted_at = submitted_at;
    }

    public String getEvalformID() {
        return evalformID;
    }

    public String getUserID() {
        return userID;
    }

    public String getEventID() {
        return eventID;
    }

    public String getQOne() {
        return qOne;
    }

    public String getQTwo() {
        return qTwo;
    }

    public String getQThree() {
        return qThree;
    }

    public String getQFour() {
        return qFour;
    }

    public String getQFive() {
        return qFive;
    }

    public String getRole() {
        return role;
    }

    public String getRolePoints() {
        return rolePoints;
    }

    public String getRating() {
        return rating;
    }

    public ImageIcon getBeginningImg() {
        return beginningImg;
    }

    public ImageIcon getMiddleImg() {
        return middleImg;
    }

    public ImageIcon getEndImg() {
        return endImg;
    }

    public Timestamp getSubmitted_at() {
        return submitted_at;
    }
}
