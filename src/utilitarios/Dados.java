package utilitarios;

import classes.Consulta;
import classes.Doenca;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Dados implements Serializable {

    public static <T> ArrayList<T> carregaDados(String caminho) {
        ArrayList<T> array = new ArrayList<>();
        try (FileInputStream leitorFile = new FileInputStream(caminho); ObjectInputStream leitorObj = new ObjectInputStream(leitorFile)) {

            array = (ArrayList<T>) leitorObj.readObject();

        } catch (FileNotFoundException a){
            System.out.println("O arquivo ainda nao foi criado");
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("O arquivo nao pode ser lido");
        }
        return array;
    }

    public static <T> void salvarDados(ArrayList<T> lista, String caminho) {
        try (FileOutputStream escritorFile = new FileOutputStream(caminho); ObjectOutputStream escritorObj = new ObjectOutputStream(escritorFile)) {

            escritorObj.writeObject(lista);

        } catch (IOException e) {
            System.out.println("Arquivo: " + caminho + " nao pode ser salvo");
        }
    }

    public static HashMap<String, Integer> carregaMedicamento(ArrayList<Consulta> consultas) {
        HashMap<String, Integer> mapMedicamento = new HashMap<>();
        for (Consulta c : consultas) {
            ArrayList<Doenca> doencas = c.getArrayDiagnostico();
            for (Doenca d : doencas) {
                String listaMedicamento = d.listarMedicamento();
                String[] valores = listaMedicamento.split("\n");
                for (String nome : valores) {
                    if (mapMedicamento.containsKey(nome)) {
                        int valor = mapMedicamento.get(nome);
                        valor++;
                        mapMedicamento.replace(nome, valor);
                    } else {
                        mapMedicamento.put(nome, 1);
                    }
                }
            }
        }
        return mapMedicamento;
    }

}
