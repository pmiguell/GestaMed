package classes;

import utilitarios.DataFormato;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Consulta implements Serializable, Cloneable {

    ArrayList<Doenca> diagnostico = new ArrayList<>();
    private LocalDateTime data;
    private Medico medico;
    private Paciente paciente;
    private int batimentos;
    private String pressao;
    private float temperatura;
    private String observacoes;
    private int id;

    public Consulta(LocalDateTime data, Medico medico, Paciente paciente, int id) {
        this.data = data;
        this.medico = new Medico(medico);
        this.paciente = new Paciente(paciente);
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public int getId() {
        return id;
    }

    public void setMedidas(int batimentos, String pressao, float temperatura) {
        this.batimentos = batimentos;
        this.pressao = pressao;
        this.temperatura = temperatura;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void addDiagnostico(Doenca doenca) {
        this.diagnostico.add(doenca);
    }

    public String getDiagnostico() {
        String s = "";
        for (Doenca d : diagnostico) {
            s += d.getNome() + "\n";
            s += d.mostrarMedicamento();
        }
        return s;
    }

    public ArrayList<Doenca> getArrayDiagnostico() {
        return diagnostico;
    }

    @Override
    public String toString() {
        String string = "";
        string += "============================================================\n"
                + "ID: " + getId()
                + "\nData: " + data.format(DataFormato.formato1())
                + "\n------------------------------------------------------------"
                + "\nPaciente: " + paciente.toString()
                + "\n------------------------------------------------------------"
                + "\nMedico: " + medico.mostra()
                + "\n------------------------------------------------------------"
                + "\nBatimento: " + batimentos
                + "\nPressao: " + pressao
                + "\nTemperatura: " + temperatura
                + "\n------------------------------------------------------------"
                + "\n" + getDiagnostico()
                + "\n------------------------------------------------------------"
                + "\nObservacoes: " + observacoes
                + "\n------------------------------------------------------------";
        return string;
    }

    public String consultaAgendada(Pessoa pessoa) {

        if (!this.getData().toLocalDate().isBefore(LocalDate.now())) {
            if (this.getPaciente().getCpf().equals(pessoa.getCpf()) && pessoa instanceof Paciente) {
                return "=======================================================\n"
                        + "Consulta Id: " + id + "\nData: " + data.format(DataFormato.formato1())
                        + "\n-----------------------------------------------------"
                        + "\nMedico: " + medico.mostra()
                        + "\n-----------------------------------------------------";
            } else if (this.getMedico().getCpf().equals(pessoa.getCpf()) && pessoa instanceof Medico) {
                return "=======================================================\n"
                        + "Consulta Id: " + id + "\nData: " + data.format(DataFormato.formato1())
                        + "\n-----------------------------------------------------"
                        + "\nPaciente: " + paciente.toString()
                        + "\n-----------------------------------------------------";
            }
        }

        return "";
    }

    public String historicoConsulta(Pessoa pessoa, LocalDate dataI, LocalDate dataF) {
        if (pessoa instanceof Paciente) {
            if (this.getPaciente().getCpf().equals(pessoa.getCpf())) {
                if (!getData().toLocalDate().isBefore(dataI) && !getData().toLocalDate().isAfter(dataF)) {
                    return this.toString();
                }
            }
        } else if (pessoa instanceof Medico) {
            if (this.getMedico().getCpf().equals(pessoa.getCpf())) {
                if (!getData().toLocalDate().isBefore(dataI) && !getData().toLocalDate().isAfter(dataF)) {
                    return this.toString();
                }
            }
        }
        return "";
    }
}
