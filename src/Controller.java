import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;

public class Controller {
    /**
     * Writes the logged-in user ID to a file for future reference.
     *
     * @param id The unique ID of the logged-in user.
     */
    protected void writeLoggedUserToFile(String id) {
        try {
            FileWriter writer = new FileWriter("loggedUser.txt");
            writer.write(id);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the state of a JButton based on the content of the "settings.config" file.
     * If the content of the file is "disabled", the button is set to disabled.
     * @param button - The JButton to be checked and possibly disabled.
     */
    public void checkButtonState(JButton button) {
        File file = new File("settings.config");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("disabled")) {
                    button.setEnabled(false);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypts a password using the StrongPasswordEncryptor.
     *
     * @param password The password to be encrypted.
     * @return The encrypted password.
     */
    protected static String encryptPassword(String password) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        return passwordEncryptor.encryptPassword(password);
    }

    /**
     * Converts an ImageIcon to a byte array for storage.
     *
     * @param icon The ImageIcon to be converted.
     * @return The byte array representing the image.
     * @throws IOException If there is an issue with image conversion.
     */
    protected byte[] convertImageIconToBytes(ImageIcon icon) throws IOException {
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    /**
     * Converts a byte array to an ImageIcon for display.
     *
     * @param bytes The byte array representing the image.
     * @return The ImageIcon created from the byte array.
     * @throws IOException If there is an issue with image conversion.
     */
    protected ImageIcon convertBytesToImageIcon(byte[] bytes) throws IOException {
        if (bytes != null && bytes.length > 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            return new ImageIcon(bufferedImage);
        }
        return null;
    }
}
