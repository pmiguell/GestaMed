package classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Paciente extends Pessoa implements Serializable {

    private String convenio;

    public Paciente(String nome, LocalDate dataNascimento, String contato, String cpf, String sexo, String convenio) {
        super(nome, dataNascimento, contato, cpf, sexo);
        this.convenio = convenio;
    }

    public Paciente(Paciente paciente) {
        super(paciente.getNome(), paciente.getDataNascimento(), paciente.getContato(), paciente.getCpf(), paciente.getSexo());
        this.convenio = paciente.getConvenio();
    }


    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getConvenio() {
        return convenio;
    }

    @Override
    public String getTipoPessoa() {
        return "Paciente";
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nConvenio: " + getConvenio();
    }
}
