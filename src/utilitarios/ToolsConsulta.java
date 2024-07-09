package utilitarios;

import classes.Consulta;
import classes.Medico;
import classes.Paciente;
import classes.Pessoa;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToolsConsulta implements Serializable {

    static Scanner scanner = new Scanner(System.in);

    public static void agendarConsulta(String tipoConsulta, Paciente paciente, ArrayList<Pessoa> pessoas, ArrayList<Consulta> consultas) {
        int cont = 0;
        System.out.println("Lista dos medicos disponiveis\n-------------------------------------");
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Medico) {
                if (((Medico) pessoa).atendeConvenio(tipoConsulta)) {
                    System.out.println(((Medico) pessoa).mostra());
                    System.out.println("-------------------------------------");
                    cont++;
                }
            }
        }

        if (cont != 0) {
            try {
                scanner.nextLine();
                System.out.println("Digite o id do medico desejado: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Medico medico = null;

                for (Pessoa pessoa : pessoas) {
                    if (pessoa instanceof Medico) {
                        if (((Medico) pessoa).getId() == id) {
                            System.out.println("Lista dos horarios disponiveis\n-------------------------------------");
                            medico = ((Medico) pessoa);
                            if (medico.mostraHorariosDisponiveis().isEmpty()) {
                                System.out.println("Medico sem horarios disponiveis");
                                return;
                            } else {
                                System.out.println(medico.mostraHorariosDisponiveis());
                            }
                        }
                    }
                }

                if (medico != null) {
                    LocalDateTime dataFinal;
                    String dataEscolhida;
                    String horarioEscolhido;

                    do {
                        try {
                            System.out.println("Escolha uma data: (DD/MM/AAAA): ");
                            dataEscolhida = scanner.nextLine();
                            VerificarFormato.vFormatData(dataEscolhida);

                            System.out.println("Escolha um horario: (HH:MM): ");
                            horarioEscolhido = scanner.nextLine();
                            VerificarFormato.vFormatHora(horarioEscolhido);

                            dataEscolhida += " " + horarioEscolhido;

                            dataFinal = LocalDateTime.parse(dataEscolhida, DataFormato.formato1());

                            if (!medico.removerHorarioDisponivel(dataFinal) || dataFinal.isBefore(LocalDateTime.now())) {
                                System.out.println("Data nao esta esta entre os dias e horarios disponiveis");
                            } else {
                                break;
                            }

                        } catch (IllegalArgumentException | DateTimeException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (true);

                    int idConsulta = GetId.setIdConsulta(consultas);

                    consultas.add(new Consulta(dataFinal, medico, paciente, idConsulta));
                    System.out.println("Consulta agendada com sucesso");

                } else {
                    System.out.println("Medico nao encontrado");
                }
            } catch (InputMismatchException e){
                System.out.println("O valor do id do medico deve ser numerico");
            }
        } else {
            System.out.println("Nenhum medico disponivel");
        }
    }

    public static boolean removerConsulta(Paciente paciente, ArrayList<Consulta> consultas) {
        ToolsPessoa.visualizarAgendamento(paciente, consultas);

        try {
            System.out.println("Id da consulta a ser removida: ");
            int removerId = scanner.nextInt();

            for (Consulta c : consultas) {
                if (c.getId() == removerId) {
                    c.getMedico().adicionarHorarioDisponivel(c.getData());
                    consultas.remove(c);
                    return true;
                }
            }
            return false;
        } catch (InputMismatchException e){
            System.out.println("O valor do id da consulta deve ser numerico");
            return false;
        }
    }
}
