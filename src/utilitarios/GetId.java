package utilitarios;

import classes.Consulta;
import classes.Medico;
import classes.Pessoa;

import java.io.Serializable;
import java.util.ArrayList;

public class GetId implements Serializable {

    public static int setIdMedico(ArrayList<Pessoa> pessoa) {
        int idMedico = 100000;
        for (Pessoa p : pessoa) {
            if (p instanceof Medico) {
                if (((Medico) p).getId() > idMedico) {
                    idMedico = ((Medico) p).getId();
                }
            }
        }
        return idMedico + 1;
    }

    public static int setIdConsulta(ArrayList<Consulta> cs) {
        int idConsulta = 1000;
        for (Consulta c : cs) {
            if (c.getId() > idConsulta) {
                idConsulta = c.getId();
            }
        }
        return idConsulta + 1;
    }
}
