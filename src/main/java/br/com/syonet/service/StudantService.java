package br.com.syonet.service;

import java.util.List;

import br.com.syonet.model.Studant;
import br.com.syonet.model.StudantRepository;

public class StudantService {
  private StudantRepository repository;

  public StudantService(StudantRepository repository) {
    this.repository = repository;
  }

  public void update(Studant studant) {
    if (studant.getId() != 0) {
        this.repository.update(studant);
    } else {
        throw new IllegalArgumentException("Estudante deve ter um id para ser atualizado.");
    }
}

  public long save(Studant studant) {
    if (studant.getId() == 0) {
      Studant newStudant = this.repository.create(studant);
      return newStudant.getId();
    }
    throw new IllegalArgumentException("Id não pode ser atribuido");
  }

  public List<Studant> listAll() {
    return this.repository.listAll();
  }
  public List<Studant> Pesquisa(String name) {
    return this.repository.Pesquisa(name);
  }

  public void delete(long id) {
    this.repository.delete(id);
  }

  public Studant find(long id) {
    return this.repository.find(id);
  }
}
