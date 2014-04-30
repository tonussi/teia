package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Info;
import model.Vertice;

public class AmigoDataAccessObjectImpl implements AmigoDataAccessObject {

  private final DBConnection dbConnection;
  private Connection connection;
  private Statement statement;
  private ResultSet resultSet;
  private final Logger logger = Logger
      .getLogger(AmigoDataAccessObjectImpl.class.getName());

  public AmigoDataAccessObjectImpl(DBConnection dbConnection) {
    this.dbConnection = dbConnection;
  }

  @Override
  public int quantidadeAmigos() {

    int quantidadeAmigos = 0;

    try {
      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("select count(*) from amigos");
      if (resultSet.next())
        quantidadeAmigos = resultSet.getInt(1);
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
    } finally {
      dbConnection.close();
    }
    return quantidadeAmigos;

  }

  @Override
  public void comparaTabelas() {

    try {
      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement
          .executeQuery("select uid, node1, node2 from amigos inner join relacoes on amigos.uid = relacoes.node1");
      while (resultSet.next())
        System.out.println(resultSet.getString(1) + resultSet.getString(2)
            + resultSet.getString(3));
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
    } finally {
      dbConnection.close();
    }

  }

  @Override
  public List<Info> listaAmigosDestacados() {

    List<Info> infos = new ArrayList<Info>();

    try {
      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement
          .executeQuery("select uid, uname, sex, locale, agerank from amigos where agerank > 220");
      while (resultSet.next())
        infos.add(new Info(BigInteger.valueOf(Long.valueOf(resultSet
            .getString(1))), resultSet.getString(2), resultSet.getString(3),
            resultSet.getString(4), resultSet.getInt(5)));
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
    } finally {
      dbConnection.close();
    }
    return infos;

  }

  @Override
  public List<Info> listaAmigosPorGeneroEAgerank(String sex, int agerank) {

    List<Info> infos = new ArrayList<Info>();

    try {
      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement
          .executeQuery("select uid, uname, sex, locale, agerank from amigos where agerank > "
              + "'" + agerank + "'" + " AND sex = " + "'" + sex + "'");
      while (resultSet.next())
        infos.add(new Info(BigInteger.valueOf(Long.valueOf(resultSet
            .getString(1))), resultSet.getString(2), resultSet.getString(3),
            resultSet.getString(4), resultSet.getInt(5)));
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
    } finally {
      dbConnection.close();
    }
    return infos;

  }

  @Override
  public Map<BigInteger, BigInteger> mapeiaRelacoes() {

    Map<BigInteger, BigInteger> mapeamentoNodular = new HashMap<BigInteger, BigInteger>();

    try {
      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("select node1, node2 from relacoes");
      while (resultSet.next())
        mapeamentoNodular.put(
            BigInteger.valueOf(Long.valueOf(resultSet.getString(1))),
            BigInteger.valueOf(Long.valueOf(resultSet.getString(1))));
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
    } finally {
      dbConnection.close();
    }
    logger.info(mapeamentoNodular.toString());
    return mapeamentoNodular;

  }

  @Override
  public List<Vertice> listaRelacoes() {

    List<BigInteger> relacoes = new ArrayList<BigInteger>();
    List<Vertice> grafo = new ArrayList<Vertice>();
    BigInteger uid = BigInteger.ZERO;
    int agerank = 0;

    try {
      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("select count(*) from amigos");
      if (resultSet.next())
        agerank = resultSet.getInt(1);
      for (int i = agerank; i > 0; i--) {
        resultSet = statement
            .executeQuery("select uid from amigos where agerank = " + "'" + i
                + "'");
        if (resultSet.next())
          uid = BigInteger.valueOf(Long.valueOf(resultSet.getString(1)));
        resultSet = statement
            .executeQuery("select node2 from relacoes where node1 = " + "'"
                + uid + "'");
        while (resultSet.next())
          relacoes
              .add(BigInteger.valueOf(Long.valueOf(resultSet.getString(1))));
        grafo.add(new Vertice(uid, relacoes));
        System.out.println("(Amigo=" + uid + "): tem os seguintes amigos: "
            + relacoes.toString());
        relacoes.clear();
      }
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
    } finally {
      dbConnection.close();
    }
    return grafo;

  }

  @Override
  public int primeiroId(String nomeId, String nomeTabela) {

    int id = 0;

    try {
      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("select" + nomeId + " from "
          + nomeTabela + " LIMIT 1");
      if (resultSet.next())
        id = resultSet.getInt(1);
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
    } finally {
      dbConnection.close();
    }
    return id;

  }
}
