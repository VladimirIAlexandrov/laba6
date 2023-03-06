import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class NumberFormatException extends Exception
{
    public NumberFormatException () {
        //super("Ошибка ввода");
        showMessageDialog(null, "Ошибка ввода", "Title", ERROR_MESSAGE);
    }


}