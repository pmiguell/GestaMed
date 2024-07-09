package classes;

import java.io.Serializable;
import java.util.*;
import java.time.*;

import utilitarios.*;

public class Main implements Serializable {

    static ArrayList<Doenca> doencas = Dados.carregaDados(".\\arrayDoencas.dat");
    static ArrayList<Pessoa> pessoas = Dados.carregaDados(".\\arrayPessoas.dat");
    static ArrayList<Consulta> consultas = Dados.carregaDados(".\\arrayConsultas.dat");
    static Scanner scanner = new Scanner(System.in);
    static String senhaAcesso = "senhaboot123";

    public static void menuPaciente(Paciente paciente) {
        int op = -1;
        do {
            try {
                System.out.println("========= MENU PACIENTE =========");
                System.out.println("1. Agendar consulta");
                System.out.println("2. Remover consulta");
                System.out.println("3. Visualizar historico de consultas");
                System.out.println("4. Visualizar agendamentos");
                System.out.println("5. Alterar senha");
                System.out.println("6. Alterar convenio");
                System.out.println("7. Sair");
                System.out.println("========= MENU PACIENTE =========");
                op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        System.out.println("Particular ou Convenio: ");
                        String tipoConsulta = scanner.nextLine();
                        if (tipoConsulta.equalsIgnoreCase("Convenio")) {
                            System.out.println("Entre com convenio: ");
                            tipoConsulta = scanner.nextLine();
                        }
                        ToolsConsulta.agendarConsulta(tipoConsulta, paciente, pessoas, consultas);
                        break;
                    case 2:
                        if (ToolsConsulta.removerConsulta(paciente, consultas)) {
                            System.out.println("Consulta removida com sucesso!");
                        } else {
                            System.out.println("Nao foi possivel remover a consulta");
                        }
                        break;
                    case 3:
                        ToolsPessoa.visualizarHistorico(paciente, consultas);
                        break;
                    case 4:
                        ToolsPessoa.visualizarAgendamento(paciente, consultas);
                        break;
                    case 5:
                        ToolsPessoa.alterarSenha(paciente);
                        break;
                    case 6:
                        ToolsPessoa.alterarConvenio(paciente);
                        break;
                    case 7:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada de dado invalida!");
                scanner.nextLine();
            }
        } while (op != 7);
    }

    public static void menuSecretaria(Secretaria secretaria) {
        int op = -1;

        do {
            try {
                System.out.println("========= MENU SECRETARIA =========");
                System.out.println("1. Agendar consulta paciente");
                System.out.println("2. Remover consulta paciente");
                System.out.println("3. Cadastrar paciente");
                System.out.println("4. Cadastrar medico");
                System.out.println("5. Cadastrar secretaria");
                System.out.println("6. Alterar senha");
                System.out.println("7. Sair");
                System.out.println("========= MENU SECRETARIA =========");
                op = scanner.nextInt();
                scanner.nextLine();

                Paciente paciente = null;
                String cpfPaciente;

                switch (op) {
                    case 1:
                        try {
                            System.out.println("Informe o CPF do paciente: ");
                            cpfPaciente = scanner.nextLine();
                            VerificarFormato.vFormatCpf(cpfPaciente);

                            System.out.println("Informe o Convenio");
                            String convenioPaciente = scanner.nextLine();

                            for (Pessoa pessoa : pessoas) {
                                if (pessoa.getCpf().equals(cpfPaciente)) {
                                    paciente = ((Paciente) pessoa);
                                }
                            }
                            ToolsConsulta.agendarConsulta(convenioPaciente, paciente, pessoas, consultas);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 2:
                        try {
                            System.out.println("Informe o CPF do paciente: ");
                            cpfPaciente = scanner.nextLine();

                            for (Pessoa pessoa : pessoas) {
                                if (pessoa.getCpf().equals(cpfPaciente)) {
                                    paciente = ((Paciente) pessoa);
                                }
                            }

                            if (ToolsConsulta.removerConsulta(paciente, consultas)) {
                                System.out.println("Consulta removida com sucesso!");
                            } else {
                                System.out.println("Nao foi possivel remover a consulta!");
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 3:
                        paciente = Cadastro.criarContaPaciente(pessoas);
                        if (paciente == null) {
                            System.out.println("Nao foi possivel cadastrar o paciente!");
                            break;
                        } else {
                            pessoas.add(paciente);
                        }
                        break;
                    case 4:
                        Medico medico = Cadastro.criarContaMedico(pessoas);
                        if (medico == null) {
                            System.out.println("Nao foi possivel cadastrar o medico!");
                            break;
                        } else {
                            pessoas.add(medico);
                        }
                        break;
                    case 5:
                        Secretaria secretariaNova = Cadastro.criarContaSecretaria(pessoas);
                        if (secretariaNova == null) {
                            System.out.println("Nao foi possivel cadastrar a secretaria!");
                            break;
                        } else {
                            pessoas.add(secretariaNova);
                        }
                        break;
                    case 6:
                        ToolsPessoa.alterarSenha(secretaria);
                        break;
                    case 7:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada de dado invalida!");
                scanner.nextLine();
            }
        } while (op != 7);
    }

    public static void menuMedico(Medico medico) {
        int op = -1;

        do {
            try {
                System.out.println("========= MENU MEDICO =========");
                System.out.println("1. Realizar consulta");
                System.out.println("2. Cadastrar dia disponivel");
                System.out.println("3. Cadastrar horario disponivel");
                System.out.println("4. Visualizar agendamentos");
                System.out.println("5. Visualizar historico de consultas");
                System.out.println("6. Visualizar historico de paciente");
                System.out.println("7. Visualizar historico de medicamentos");
                System.out.println("8. Alterar senha");
                System.out.println("9. Adicionar convenio");
                System.out.println("10. Sair");
                System.out.println("========= MENU MEDICO =========");
                op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        for (Consulta consulta : consultas) {
                            if ((consulta.getMedico().getCpf().equals(medico.getCpf())) && (consulta.getData().toLocalDate().equals(LocalDate.now()))) {
                                System.out.println(consulta.consultaAgendada(medico));
                            }
                        }

                        System.out.println("Informe o ID da consulta: ");
                        int idConsulta = scanner.nextInt();
                        scanner.nextLine();

                        for (Consulta consulta : consultas) {
                            if (consulta.getId() == idConsulta) {
                                System.out.println("Informe os batimentos: ");
                                int batimentos = scanner.nextInt();

                                System.out.println("Informe a temperatura: ");
                                float temperatura = scanner.nextFloat();

                                System.out.println("Informe a pressao: ");
                                String pressao = scanner.nextLine();

                                consulta.setMedidas(batimentos, pressao, temperatura);

                                int option = -1;

                                Doenca dConsulta = null;
                                Doenca dVetor = null;

                                int indexDoenca = -1;

                                do {
                                    try {
                                        boolean existeDoenca = false;
                                        System.out.println("========== MENU DOENCAS ==========");
                                        System.out.println("1. Listar doencas");
                                        System.out.println("2. Informar doenca a ser inserida na consulta");
                                        System.out.println("3. Finalizar consulta");
                                        System.out.println("========== MENU DOENCAS ==========");
                                        option = scanner.nextInt();
                                        scanner.nextLine();

                                        switch (option) {
                                            case 1:
                                                System.out.println("Doencas cadastradas no sistema: ");

                                                for (Doenca doenca : doencas) {
                                                    System.out.println(doenca.getNome());
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Informe o nome da doenca: ");
                                                String doenca = scanner.nextLine();

                                                for (Doenca d : doencas) {
                                                    if (d.getNome().equalsIgnoreCase(doenca)) {
                                                        System.out.println("Medicamentos ja cadastrados para essa doenca: ");
                                                        System.out.println(d.listarMedicamento());
                                                        dConsulta = new Doenca(d.getNome());
                                                        indexDoenca = doencas.indexOf(d);
                                                        dVetor = d;
                                                        existeDoenca = true;
                                                    }
                                                }
                                                if (!existeDoenca) {
                                                    System.out.println("Doenca nao encontrada no sistema! \nNova doenca criada");
                                                    dConsulta = new Doenca(doenca);
                                                }

                                                String resposta;

                                                do {
                                                    System.out.println("Informe o nome medicamento: ");
                                                    String medicamento = scanner.nextLine();

                                                    System.out.println("Informe a quantidade: ");
                                                    int quantidade = scanner.nextInt();

                                                    System.out.println("Informe os miligramas: ");
                                                    int miligrama = scanner.nextInt();
                                                    scanner.nextLine();

                                                    System.out.println("Informe a posologia: ");
                                                    String posologia = scanner.nextLine();

                                                    Medicamento m = new Medicamento(medicamento, quantidade, miligrama, posologia);
                                                    dConsulta.addMedicamento(m);

                                                    if (dVetor != null) {
                                                        if (!dVetor.medicamentos.containsKey(medicamento)) {
                                                            dVetor.addMedicamento(m);
                                                        }
                                                    }

                                                    System.out.println("Gostaria de adicionar novo medicamento a consulta? (s/n): ");
                                                    resposta = scanner.nextLine();

                                                } while (!resposta.equals("n"));

                                                consulta.addDiagnostico(dConsulta);

                                                if (existeDoenca) {
                                                    doencas.remove(indexDoenca);
                                                    doencas.add(indexDoenca, dVetor);
                                                } else {
                                                    doencas.add(dConsulta);
                                                }
                                                System.out.println("Observacoes: ");
                                                String observacoes = scanner.nextLine();
                                                consulta.setObservacoes(observacoes);
                                                break;
                                            case 3:
                                                System.out.println("Encerrando...");
                                                break;
                                            default:
                                                System.out.println("Opcao invalida!");
                                        }
                                    } catch (InputMismatchException e){
                                        System.out.println("Entrada de dado invalida");
                                        scanner.nextLine();
                                    }
                                } while (option != 3);
                                break;
                            }
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("Entre com o dia disponivel: DD/MM/AAAA");
                            String dia = scanner.nextLine();
                            VerificarFormato.vFormatData(dia);
                            medico.adicionarDiasDisponiveis(dia);
                            break;
                        } catch (IllegalArgumentException | DateTimeException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 3:
                        try {
                            System.out.println("Entre com o horario disponivel: HH:MM");
                            String horario = scanner.nextLine();
                            VerificarFormato.vFormatHora(horario);
                            medico.adicionarHorarioDisponivel(horario);
                            break;
                        } catch (IllegalArgumentException | DateTimeException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 4:
                        ToolsPessoa.visualizarAgendamento(medico, consultas);
                        break;
                    case 5:
                        ToolsPessoa.visualizarHistorico(medico, consultas);
                        break;
                    case 6:
                        try {
                            System.out.println("Entre com o CPF do paciente");
                            String cpf = scanner.nextLine();
                            VerificarFormato.vFormatCpf(cpf);

                            Paciente paciente = null;
                            for (Pessoa p : pessoas) {
                                if (p instanceof Paciente && p.getCpf().equals(cpf)) {
                                    paciente = (Paciente) p;
                                }
                            }

                            ToolsPessoa.visualizarHistorico(paciente, consultas);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 7:
                        HashMap<String, Integer> medicamentos = Dados.carregaMedicamento(consultas);

                        for (Map.Entry<String, Integer> entrada : medicamentos.entrySet()) {
                            System.out.println(entrada.getKey() + " " + entrada.getValue());
                        }
                        break;
                    case 8:
                        ToolsPessoa.alterarSenha(medico);
                        break;
                    case 9:
                        System.out.println("Entre com o novo convenio do medico");
                        medico.addConvenio(scanner.nextLine());
                        break;
                    case 10:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada de dado invalida!");
                scanner.nextLine();
            } catch (Exception e){
                System.out.println("Erro desconhecido");
            }
        } while (op != 10);
    }

    public static void main(String[] a) {
        int op = -1;

        do {
            try {
                System.out.println("======= MENU =======");
                System.out.println("1. Logar");
                System.out.println("2. Criar conta do paciente");
                System.out.println("3. Criar conta da secretaria");
                System.out.println("4. Encerrar");
                System.out.println("======= MENU =======");
                op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        try {
                            System.out.println("CPF: ");
                            String cpf = scanner.nextLine();
                            VerificarFormato.vFormatCpf(cpf);

                            System.out.println("Senha: ");
                            String senha = scanner.nextLine();
                            int cont = 0;

                            System.out.println("Tipo de usuario: Paciente, Secretaria ou Medico?");
                            String tipoPessoa = scanner.nextLine();

                            for (Pessoa pessoa : pessoas) {
                                if (pessoa.getCpf().equals(cpf) && pessoa.getSenha().equals(senha) && tipoPessoa.equalsIgnoreCase(pessoa.getTipoPessoa())) {
                                    System.out.println("Login realizado com sucesso\nBem-vindo!!!");
                                    cont++;
                                    if (pessoa instanceof Paciente) {
                                        menuPaciente(((Paciente) pessoa));
                                        break;
                                    } else if (pessoa instanceof Secretaria) {
                                        menuSecretaria((Secretaria) pessoa);
                                        break;
                                    } else if (pessoa instanceof Medico) {
                                        menuMedico((Medico) pessoa);
                                        break;
                                    }
                                }
                            }
                            if (cont == 0) {
                                System.out.println("CPF, senha ou tipo do usuario invalidos");
                                //tipo usario invalido se refere ao classe de Medico, Secretaria, Paciente
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 2:
                        Paciente paciente = Cadastro.criarContaPaciente(pessoas);
                        if (paciente == null) {
                            System.out.println("Nao foi possivel cadastrar o paciente!");
                            break;
                        } else {
                            pessoas.add(paciente);
                        }
                        break;
                    case 3:
                        System.out.println("Informe a senha de acesso ao sistema: ");
                        String senhaSec = scanner.nextLine();

                        if (senhaSec.equals(senhaAcesso)) {
                            System.out.println("Acesso liberado ... ");
                            Secretaria secretaria = Cadastro.criarContaSecretaria(pessoas);
                            if (secretaria == null) {
                                System.out.println("Nao foi possivel cadastrar a secretaria!");
                                break;
                            } else {
                                pessoas.add(secretaria);
                            }
                        } else {
                            System.out.println("Acesso negado!");
                        }
                        break;
                    case 4:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada de dado invalida!");
                scanner.nextLine();
            }
        } while (op != 4);

        Dados.salvarDados(pessoas, ".\\arrayPessoas.dat");
        Dados.salvarDados(doencas, ".\\arrayDoencas.dat");
        Dados.salvarDados(consultas, ".\\arrayConsultas.dat");

    }
}
