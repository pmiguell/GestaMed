package classes;

import utilitarios.DataFormato;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Medico extends Pessoa implements Serializable{

    ArrayList<String> convenio = new ArrayList<>();
    ArrayList<String> diasDisponiveis = new ArrayList<>();
    ArrayList<LocalDateTime> horariosDisponiveis = new ArrayList<>();
    private String especialidade;
    private int id;

    public Medico(String nome, LocalDate dataNascimento, String contato, String cpf, String sexo, String especialidade, int id) {

        super(nome, dataNascimento, contato, cpf, sexo);
        this.especialidade = especialidade;
        this.convenio.add("Particular");
        this.id = id;
    }

    public Medico(Medico medico) {
        super(medico.getNome(), medico.getDataNascimento(), medico.getContato(), medico.getCpf(), medico.getSexo());
        this.convenio.addAll(medico.getConvenio());
        this.especialidade = medico.getEspecialidade();
        this.id = medico.getId();
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getConvenio() {
        return convenio;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void addConvenio(String convenio) {
        this.convenio.add(convenio);
    }

    public boolean atendeConvenio(String c) {
        for (String conv : convenio) {
            if (conv.equalsIgnoreCase(c)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarDiasDisponiveis(String dia) throws DateTimeException {
        try {
            if (!diasDisponiveis.contains(dia)) {
                LocalDate data = LocalDate.parse(dia, DataFormato.formato2());
                diasDisponiveis.add(dia);
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeException("Data invalida");
        }
    }

    public void adicionarHorarioDisponivel(String horario) throws DateTimeException {
        for (String a : diasDisponiveis) {
            a += " " + horario;
            LocalDateTime data = LocalDateTime.parse(a, DataFormato.formato1());
            adicionarHorarioDisponivel(data);
        }
    }

    public void adicionarHorarioDisponivel(LocalDateTime horarioDisponivel) throws DateTimeException {
        if (!horariosDisponiveis.contains(horarioDisponivel)) {
            horariosDisponiveis.add(horarioDisponivel);
        } else {
            throw new DateTimeException("Nao foi possivel cadastrar horario");
        }
    }

    public boolean removerHorarioDisponivel(LocalDateTime horarioDisponivel) {
        for (LocalDateTime h : horariosDisponiveis) {
            if (h.equals(horarioDisponivel)) {
                horariosDisponiveis.remove(h);
                return true;
            }
        }
        return false;
    }

    public String mostraHorariosDisponiveis() {
        String horarios = "";
        for (LocalDateTime h : horariosDisponiveis) {
            if (!h.isBefore(LocalDateTime.now())) {
                String dataFormatada = h.format(DataFormato.formato1());
                horarios += dataFormatada + "\n";
            }
        }
        return horarios;
    }

    public String mostra() {
        return "Id: " + getId()
                + "\nNome: " + super.getNome()
                + "\nEspecialidade: " + getEspecialidade();
    }

    @Override
    public String getTipoPessoa() {
        return "Medico";
    }
}
