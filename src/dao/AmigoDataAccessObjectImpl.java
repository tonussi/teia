package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Info;
import model.Nodo;
import processing.core.PApplet;
import processing.core.PFont;

public class AmigoDataAccessObjectImpl implements AmigoDataAccessObject {

  private Connection connection;

  private DBConnection dbConnection;

  private Statement statement;

  private ResultSet resultSet;

  private Logger logger = Logger.getLogger(AmigoDataAccessObjectImpl.class
      .getName());

  PApplet processing;

  PFont font;

  /*
   * Precisa ser refatorada da seguinte maneira PApplet processing e PFont font
   * nao deveririam estar aqui.
   * 
   * Essas referencias podem ser feitas na fachada da maneira de ela cria o
   * objetos e referencia ela mesmo.
   * 
   * Ai sim livraremos a classe DAO de qualquer referencia ao motor grafico do
   * processing.
   */
  public AmigoDataAccessObjectImpl(PApplet processing, PFont font,
      DBConnection dbConnection) {

    this.font = font;
    this.processing = processing;
    this.dbConnection = dbConnection;

    try {

      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT VERSION()");

      if (resultSet.next())
        System.out.println(resultSet.getString(1));

    } catch (SQLException event) {

      logger.log(Level.SEVERE, event.getMessage(), event);

    } finally {

      try {
        if (resultSet != null)
          resultSet.close();
        if (statement != null)
          statement.close();
        if (connection != null)
          connection.close();

      } catch (SQLException event) {

        logger.log(Level.WARNING, event.getMessage(), event);

      }
    }
  }

  @Override
  public int quantidadeAmigos() {

    try {

      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT COUNT(*) from amigos");

      if (resultSet.next())
        return resultSet.getInt(1);

    } catch (SQLException event) {

      logger.log(Level.SEVERE, event.getMessage(), event);

    } finally {

      try {

        if (resultSet != null)
          resultSet.close();
        if (connection != null)
          connection.close();

      } catch (SQLException event) {

        logger.log(Level.WARNING, event.getMessage(), event);

      }
    }

    return 0;
  }

  @Override
  public void comparaTabelas() {

    try {

      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement
          .executeQuery("SELECT uid, node1, node2 FROM amigos INNER JOIN relacoes ON amigos.uid = relacoes.node1");

      while (resultSet.next())
        System.out.println(resultSet.getString(1) + resultSet.getString(2)
            + resultSet.getString(3));

    } catch (SQLException event) {

      logger.log(Level.SEVERE, event.getMessage(), event);

    } finally {

      try {
        if (resultSet != null)
          resultSet.close();
        if (connection != null)
          connection.close();

      } catch (SQLException event) {

        logger.log(Level.WARNING, event.getMessage(), event);

      }
    }
  }

  @Override
  public List<Info> listadeAmigos(String sex) {

    List<Info> infos = new ArrayList<Info>();

    try {

      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement
          .executeQuery("SELECT uid, uname, sex, locale, agerank FROM amigos where agerank < 300 AND sex = "
              + "'" + sex + "'");

      while (resultSet.next())
        infos
            .add(new Info(processing, font, BigInteger.valueOf(Long
                .valueOf(resultSet.getString(1))), resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4), resultSet
                    .getInt(5)));

    } catch (SQLException event) {

      logger.log(Level.SEVERE, event.getMessage(), event);

    } finally {

      try {
        if (resultSet != null)
          resultSet.close();
        if (connection != null)
          connection.close();

      } catch (SQLException event) {
        logger.log(Level.WARNING, event.getMessage(), event);
      }
    }

    return infos;
  }

  @Override
  public List<Nodo> listadeRelacoes(int quantidadeRelacoes) {

    List<Nodo> nodos = new ArrayList<Nodo>();
    List<BigInteger> relacoesParaHashSet = new ArrayList<BigInteger>();
    int oid = 0;

    try {

      connection = dbConnection.connect();

      resultSet = statement.executeQuery("SELECT oid FROM relacoes LIMIT 1");

      if (resultSet.next())
        oid = resultSet.getInt(1);
      else
        return null;

      while (oid < quantidadeRelacoes) {
        resultSet = statement
            .executeQuery("SELECT node1 FROM relacoes WHERE oid = " + oid);
        oid = oid + 1;

        while (resultSet.next())
          relacoesParaHashSet.add(BigInteger.valueOf(Long.valueOf(resultSet
              .getString(1))));

        nodos.add(new Nodo(processing, BigInteger.valueOf(Long
            .valueOf(resultSet.getString(1))), relacoesParaHashSet));
      }

    } catch (SQLException event) {

      logger.log(Level.SEVERE, event.getMessage(), event);

    } finally {

      try {
        if (resultSet != null)
          resultSet.close();
        if (connection != null)
          connection.close();

      } catch (SQLException event) {
        logger.log(Level.WARNING, event.getMessage(), event);
      }
    }

    return nodos;
  }
}
