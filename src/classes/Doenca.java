package classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Doenca implements Serializable {

    HashMap<String, Medicamento> medicamentos = new HashMap<>();
    private String nome;

    public Doenca(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String mostrarMedicamento() {
        String lista = "";
        Iterator<Entry<String, Medicamento>> iterator = medicamentos.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<String, Medicamento> entrada = iterator.next();
            Medicamento medicamento = entrada.getValue();
            lista += medicamento.toString() + "\n";
        }

        return lista;
    }

    public String listarMedicamento() {
        String lista = "";
        Iterator<Entry<String, Medicamento>> iterator = medicamentos.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<String, Medicamento> entrada = iterator.next();
            lista += entrada.getKey() + "\n";
        }

        return lista;
    }

    public void addMedicamento(Medicamento medicamento) {
        this.medicamentos.put(medicamento.getNome(), medicamento);
    }
}
