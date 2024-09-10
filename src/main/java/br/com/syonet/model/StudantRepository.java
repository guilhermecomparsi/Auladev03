package br.com.syonet.model;

import java.util.List;

public interface StudantRepository {
  Studant create(Studant studant);
  List<Studant> listAll();
  void update(Studant studant); 
  List<Studant> Pesquisa(String name);
  void delete(long id);
  Studant find(long id);

}

