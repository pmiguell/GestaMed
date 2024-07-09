package utilitarios;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class DataFormato implements Serializable {

    public static DateTimeFormatter formato1() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }

    public static DateTimeFormatter formato2() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
}
