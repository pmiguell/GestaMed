package classes;

import utilitarios.DataFormato;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pessoa implements Serializable {

    private String nome;
    private LocalDate dataNascimento;
    private String contato;
    private String cpf;
    private String sexo;
    private String senha;

    public Pessoa(String nome, LocalDate dataNascimento, String contato, String cpf, String sexo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.contato = contato;
        this.cpf = cpf;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public abstract String getTipoPessoa();

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getContato() {
        return contato;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSenha(String senha) throws Exception {
        if (senha.length() < 8) {
            throw new Exception("A senha deve ser maior ou igual a 8 digitos!");
        }
        if (senha.equals(getSenha())) {
            throw new Exception("A senha deve ser diferente da atual!");
        }
        this.senha = senha;
    }

    public String toString() {
        String dataFormatada = dataNascimento.format(DataFormato.formato2());
        return "Nome: " + nome
                + "\nData de Nascimento: " + dataFormatada
                + "\nContato: " + contato
                + "\nCPF: " + cpf
                + "\nSexo: " + sexo;
    }
}
