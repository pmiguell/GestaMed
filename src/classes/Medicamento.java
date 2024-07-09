package classes;

import java.io.Serializable;

public class Medicamento implements Serializable {

    private String nome;
    private int quantidade;
    private int miligrama;
    private String posologia;

    public Medicamento(String nome, int quantidade, int miligrama, String posologia) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.miligrama = miligrama;
        this.posologia = posologia;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getMiligrama() {
        return miligrama;
    }

    public String getPosologia() {
        return posologia;
    }

    public String toString() {
        return "Nome: " + getNome() + " - Quantidade: " + getQuantidade() + " - Miligrama: " + getMiligrama() + " - Posologia: " + getPosologia();
    }
}
