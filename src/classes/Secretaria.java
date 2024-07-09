package classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Secretaria extends Pessoa implements Serializable {

    public Secretaria(String nome, LocalDate dataNascimento, String contato, String cpf, String sexo) {
        super(nome, dataNascimento, contato, cpf, sexo);
    }

    @Override
    public String getTipoPessoa() {
        return "Secretaria";
    }
}
