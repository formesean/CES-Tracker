import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;

public class Controller {
    protected void writeLoggedUserToFile(String id) {
        try {
            FileWriter writer = new FileWriter("loggedUser.txt");
            writer.write(id);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static String encryptPassword(String password) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        return passwordEncryptor.encryptPassword(password);
    }

    protected byte[] convertImageIconToBytes(ImageIcon icon) throws IOException {
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    protected ImageIcon convertBytesToImageIcon(byte[] bytes) throws IOException {
        if (bytes != null && bytes.length > 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            return new ImageIcon(bufferedImage);
        }
        return null;
    }
}
