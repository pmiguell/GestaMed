package utilitarios;

import classes.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToolsPessoa implements Serializable {

    static Scanner scanner = new Scanner(System.in);

    public static void alterarSenha(Pessoa pessoa) {
        System.out.println("Informe a senha: ");
        String senhaNova = scanner.next();
        while (true) {
            try {
                pessoa.setSenha(senhaNova);
                System.out.println("Senha cadastrada com sucesso!");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Digite novamente a senha: ");
                senhaNova = scanner.next();
            }
        }
    }

    public static void visualizarAgendamento(Pessoa pessoa, ArrayList<Consulta> consultas) {
        for (Consulta c : consultas) {
            System.out.println(c.consultaAgendada(pessoa));
        }
    }

    public static void visualizarHistorico(Pessoa pessoa, ArrayList<Consulta> consultas) {
        try {
            System.out.println("Escolha uma data inicial: (DD/MM/AAAA): ");
            String dataInicial = scanner.nextLine();
            VerificarFormato.vFormatData(dataInicial);

            System.out.println("Escolha uma data final: (DD/MM/AAAA): ");
            String dataFinal = scanner.nextLine();
            VerificarFormato.vFormatData(dataFinal);

            LocalDate dataI = LocalDate.parse(dataInicial, DataFormato.formato2());
            LocalDate dataF = LocalDate.parse(dataFinal, DataFormato.formato2());

            for (Consulta c : consultas) {
                System.out.println(c.historicoConsulta(pessoa, dataI, dataF));
            }
        } catch (IllegalArgumentException | DateTimeParseException e){
            System.out.println(e.getMessage());
        }
    }

    public static void alterarConvenio(Pessoa pessoa) {
        System.out.println("Digite o novo convenio: ");
        String convenioNovo = scanner.next();

        if (pessoa instanceof Paciente) {
            ((Paciente) pessoa).setConvenio(convenioNovo);
            System.out.println("Convenio alterado com sucesso!");
        } else if (pessoa instanceof Medico) {
            ((Medico) pessoa).addConvenio(convenioNovo);
            System.out.println("Convenio adicionado com sucesso!");
        }
    }

}
