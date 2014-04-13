package dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import model.Info;
import model.Nodo;

public interface AmigoDataAccessObject {

  public int quantidadeAmigos();

  public void comparaTabelas();

  public List<Info> listaAmigosPorGenero(String sex);

  public List<Nodo> listaRelacoes();

  public int primeiroId(String nomeId, String nomeTabela);

  public Map<BigInteger, BigInteger> mapeiaRelacoes();

}