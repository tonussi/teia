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

  private final DBConnection dbConnection;

  private Connection connection;

  private Statement statement;

  private ResultSet resultSet;

  private final Logger logger = Logger
      .getLogger(AmigoDataAccessObjectImpl.class.getName());

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

  }

  @Override
  public int quantidadeAmigos() {

    int quantidadeAmigos = 0;

    try {

      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT COUNT(*) from amigos");

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
          .executeQuery("SELECT uid, node1, node2 FROM amigos INNER JOIN relacoes ON amigos.uid = relacoes.node1");

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
      dbConnection.close();
    }

    return infos;
  }

  @Override
  public List<Nodo> listadeRelacoes() {

    List<BigInteger> relacoes = new ArrayList<BigInteger>();
    List<Nodo> nodos = new ArrayList<Nodo>();
    BigInteger uid = BigInteger.ZERO;
    int agerank = 0;

    try {

      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT COUNT(*) from amigos");

      if (resultSet.next())
        agerank = resultSet.getInt(1);

      System.out.println(agerank);

      for (int i = agerank; i > 0; i--) {
        resultSet = statement
            .executeQuery("SELECT uid FROM amigos WHERE agerank = " + "'" + i
                + "'");

        if (resultSet.next())
          uid = BigInteger.valueOf(Long.valueOf(resultSet.getString(1)));

        System.out.println(uid.toString());

        resultSet = statement
            .executeQuery("SELECT node2 FROM relacoes WHERE node1 = " + "'"
                + uid + "'");

        while (resultSet.next())
          relacoes
              .add(BigInteger.valueOf(Long.valueOf(resultSet.getString(1))));

        nodos.add(new Nodo(processing, uid, relacoes));

        System.out.println("Nodo: " + relacoes.toString());

        relacoes.clear();
      }

    } catch (SQLException event) {

      logger.log(Level.SEVERE, event.getMessage(), event);

    } finally {

      dbConnection.close();
    }

    return nodos;
  }

  @Override
  public int primeiroId() {

    int id = 0;

    try {

      connection = dbConnection.connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT oid FROM relacoes LIMIT 1");

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
