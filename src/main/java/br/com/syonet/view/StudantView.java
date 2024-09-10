package br.com.syonet.view;

import java.util.List;
import java.util.Scanner;

import br.com.syonet.model.Studant;
import br.com.syonet.service.StudantService;

public class StudantView {

  private int selectedOption;
  private boolean exit;
  private Scanner scanner;

  private StudantService service;

  public StudantView(StudantService service, Scanner scanner) {
    this.service = service;
    this.scanner = scanner;
  }



  public void init() {
    System.out.println("Ola seja vem vindo ao nosso cadastro de estudantes.");
  }

  public void showOptions() {
    System.out.println("Por favor selecione uma operaçoes abaixo:");
    System.out.println();
    System.out.println("\t(1) - criar novo estudantes");
    System.out.println("\t(2) - Listar estudantes");
    System.out.println("\t(3) - Atualizar estudante"); 
    System.out.println("\t(4) - Buscar estudante por nome");
    System.out.println("\t(5) - Deletar estudante"); 
    System.out.println("\t(6) - Buscar estudante por ID");
    System.out.println("\t(0) - sair");
  }

  public Integer getSelectedOption() {
    return selectedOption;
  }

  public boolean isExit() {
    return this.exit;
  }

  public void readSelectedOption() {
    String nextLine = this.scanner.nextLine();
    int answer = Integer.parseInt(nextLine);
    this.exit = answer == 0;
    this.selectedOption = answer;
  }

  public void executeSelectedOperation() {
    switch (this.selectedOption) {
      case 1:
        this.initCreationProcess();
        break;
      case 2:
        this.initListProcess();
        break;
      case 3:
      this.initUpdateProcess();
        break;
      case 4:
      this.initPesquisaProcess();
        break;
      case 5:;
      this.initDeleteProcess();
      break;
      case 6:;
      this.initFindProcess();
      default:
        break;
    }
  }

  private void initUpdateProcess() {
    System.out.println("Informe o ID que deseja atualizar:");
    long id = Long.parseLong(this.scanner.nextLine());

    System.out.println("Informe o novo nome:");
    String name = this.scanner.nextLine();

    System.out.println("Informe a nova idade:");
    int age = Integer.parseInt(this.scanner.nextLine());

    System.out.println("Informe o novo email:");
    String email = this.scanner.nextLine();

    Studant studant = new Studant(id, name, age, email);
    this.service.update(studant);
    System.out.println("Estudante atualizado com sucesso!");
}

  private void initListProcess() {
    List<Studant> studants = this.service.listAll();
    if (studants != null && !studants.isEmpty()) {
      System.out.println();
      System.out.printf("%-5s %-30s %-5s %-30s%n", "ID", "Nome", "Idade", "Email");
      for (Studant studant : studants) {
        System.out.printf("%-5d %-30s %-5d %-30s%n",
                studant.getId(),
                studant.getName(),
                studant.getAge(),
                studant.getEmail());
    }
    System.out.println();

    } else {
      System.out.println("Não há estudantes cadastrados!");
    }
  }

  private void initCreationProcess() {
    System.out.println("Ok, qual é o nome do estudante?");
    String name = this.scanner.nextLine();
    System.out.println("E o email do rapaz ou da moça?");
    String email = this.scanner.nextLine();
    System.out.println("Muito bom! agora qual a idade dela ou dele?");
    Integer idade = Integer.parseInt(this.scanner.nextLine());
    System.out.println("Obrigado temos todas as info, criando novo estudante!");
    Studant studant = new Studant(name, idade, email);
    long id = this.service.save(studant);
    System.out.println("O id do novo estudante é " + id);
  }

private void initPesquisaProcess() {
  System.out.println("Digite o nome ou parte do nome do estudante que deseja buscar:");
  String name = this.scanner.nextLine();
  List<Studant> studants = this.service.Pesquisa(name);
  if (studants != null && !studants.isEmpty()) {
      System.out.printf("%-5s %-30s %-5s %-30s%n", "ID", "Nome", "Idade", "Email");
      for (Studant studant : studants) {
          System.out.printf("%-5d %-30s %-5d %-30s%n",
                  studant.getId(),
                  studant.getName(),
                  studant.getAge(),
                  studant.getEmail());
      }
  } else {
      System.out.println("Nenhum estudante encontrado com esse nome.");
  }
 }

 private void initDeleteProcess() {
  System.out.println("Digite o ID do estudante que deseja deletar:");
  long id = Long.parseLong(this.scanner.nextLine());
  this.service.delete(id);
 }

 private void initFindProcess() {
  System.out.println("Digite o ID do estudante que deseja buscar:");
  long id = Long.parseLong(this.scanner.nextLine());
  Studant studant = this.service.find(id);
  if (studant != null) {
      System.out.printf("ID: %d\nNome: %s\nIdade: %d\nEmail: %s\n",
              studant.getId(),
              studant.getName(),
              studant.getAge(),
              studant.getEmail());
  } else {
      System.out.println("Estudante com ID " + id + " não encontrado.");
  }
}
}
