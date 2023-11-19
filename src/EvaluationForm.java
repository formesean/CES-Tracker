import javax.swing.*;

public class EvaluationForm {
    private String userID;
    private String qOne;
    private String qTwo;
    private ImageIcon beginningImg;
    private ImageIcon middleImg;
    private ImageIcon endImg;

    public EvaluationForm(String userID, String qOne, String qTwo, ImageIcon beginningImg, ImageIcon middleImg, ImageIcon endImg) {
        this.userID = userID;
        this.qOne = qOne;
        this.qTwo = qTwo;
        this.beginningImg = beginningImg;
        this.middleImg = middleImg;
        this.endImg = endImg;
    }

    public String getUserID() {
        return userID;
    }

    public String getQOne() {
        return qOne;
    }

    public String getQTwo() {
        return qTwo;
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
}
