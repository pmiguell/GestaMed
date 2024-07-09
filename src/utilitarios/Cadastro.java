package utilitarios;

import classes.Medico;
import classes.Paciente;
import classes.Pessoa;
import classes.Secretaria;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cadastro implements Serializable {

    static Scanner scanner = new Scanner(System.in);

    private static String nome, contato, cpfCadastro, sexo;
    private static LocalDate dataNascimento;

    private static void lerDados() {
        System.out.println("Nome: ");
        nome = scanner.nextLine();

        while (true) {
            try {
                System.out.println("Dia de nascimento: ");
                int diaNascimento = scanner.nextInt();

                System.out.println("Mes de nascimento: ");
                int mesNascimento = scanner.nextInt();

                System.out.println("Ano de nascimento: ");
                int anoNascimento = scanner.nextInt();

                dataNascimento = LocalDate.of(anoNascimento, mesNascimento, diaNascimento);
                if (dataNascimento.isAfter(LocalDate.now())) {
                    throw new DateTimeException("Data de nascimento maior que a atual!");
                }
                break;
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Entrada de dado invalida!");
                scanner.nextLine();
            }
        }
        System.out.println("Contato: ");
        contato = scanner.next();
        scanner.nextLine();

        while (true) {
            try {
                System.out.println("Cpf: ");
                cpfCadastro = scanner.nextLine();
                VerificarFormato.vFormatCpf(cpfCadastro);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Sexo: ");
        sexo = scanner.next();
    }

    public static Paciente criarContaPaciente(ArrayList<Pessoa> pessoas) {
        lerDados();

        System.out.println("Nome do convenio ou Particular: ");
        String convenio = scanner.next();
        scanner.nextLine();

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Paciente && pessoa.getCpf().equals(cpfCadastro)) {
                System.out.println("CPF ja cadastrado!");
                return null;
            }
        }

        Paciente paciente = new Paciente(nome, dataNascimento, contato, cpfCadastro, sexo, convenio);

        ToolsPessoa.alterarSenha(paciente);
        System.out.println("Paciente cadastrado com sucesso!");

        return paciente;
    }

    public static Secretaria criarContaSecretaria(ArrayList<Pessoa> pessoas) {
        lerDados();

        scanner.nextLine();

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Secretaria && pessoa.getCpf().equals(cpfCadastro)) {
                System.out.println("CPF ja cadastrado!");
                return null;
            }
        }

        Secretaria secretaria = new Secretaria(nome, dataNascimento, contato, cpfCadastro, sexo);

        ToolsPessoa.alterarSenha(secretaria);
        System.out.println("Secretaria cadastrada com sucesso!");

        return secretaria;
    }

    public static Medico criarContaMedico(ArrayList<Pessoa> pessoas) {
        lerDados();

        System.out.println("Especialidade: ");
        String especialidade = scanner.next();
        scanner.nextLine();

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Medico && pessoa.getCpf().equals(cpfCadastro)) {
                System.out.println("CPF ja cadastrado!");
                return null;
            }
        }

        int idMedico = GetId.setIdMedico(pessoas);

        Medico medico = new Medico(nome, dataNascimento, contato, cpfCadastro, sexo, especialidade, idMedico);

        ToolsPessoa.alterarSenha(medico);
        System.out.println("Medico cadastrado com sucesso!");

        return medico;
    }

}
