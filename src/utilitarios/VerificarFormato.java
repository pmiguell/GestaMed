package utilitarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificarFormato {

    public static void vFormatCpf(String cpf) throws IllegalArgumentException{
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(cpf);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Formato de CPF invalido! Formato esperado: xxx.xxx.xxx-xx");
        }
    }

    public static void vFormatData(String data) throws IllegalArgumentException{
        String regex = "\\d{2}/\\d{2}/\\d{4}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(data);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Formato de data invalido! Formato esperado: DD/MM/AAAA");
        }
    }

    public static void vFormatHora(String time) throws IllegalArgumentException {
        String regex = "\\d{2}:\\d{2}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(time);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Formato de hora invalido! Formato esperado: HH:MM");
        }
    }

}
