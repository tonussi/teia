package dao;

import java.util.List;

import model.Info;
import model.Nodo;

public interface AmigoDataAccessObject {

  public int quantidadeAmigos();

  public void comparaTabelas();

  public List<Info> listadeAmigos(String sex);

  public List<Nodo> listadeRelacoes();

  public int primeiroId();

}