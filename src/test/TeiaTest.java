/**
 * 
 */
package test;

import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import model.Amigo;
import model.AmigoHomem;
import model.AmigoMulher;
import model.Info;
import model.Vertice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import processing.core.PApplet;
import processing.core.PFont;
import dao.AmigoDataAccessObjectImpl;
import dao.DBConnection;
import dao.DBConnectionImpl;

/**
 * @author lucastonussi
 * 
 */
public class TeiaTest {

  List<Amigo> amigos;
  List<Info> infoAmigosHomens;
  List<Info> infoAmigosMulheres;
  List<Vertice> grafo;
  Map<BigInteger, BigInteger> mapeamentoNodular;
  AmigoDataAccessObjectImpl amigoDataAccessObject;
  DBConnection dbConnection;
  PFont font;
  PApplet processing;
  AmigoHomem amigoHomem;
  Info info;
  AmigoMulher amigoMulher;

  private final Logger logger = Logger.getLogger(TeiaTest.class.getName());

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    font = processing.createFont("Helvetica", 6, true);

    amigos = new ArrayList<Amigo>();

    dbConnection = new DBConnectionImpl("lucastonussi", "lucastonussi",
        "hung4ro5");

    amigoDataAccessObject = new AmigoDataAccessObjectImpl(processing, font,
        dbConnection);

    infoAmigosHomens = new ArrayList<Info>(
        amigoDataAccessObject.listaAmigosPorGenero("male"));

    infoAmigosMulheres = new ArrayList<Info>(
        amigoDataAccessObject.listaAmigosPorGenero("female"));

    grafo = new ArrayList<Vertice>(amigoDataAccessObject.listaRelacoes());

    mapeamentoNodular = new HashMap<BigInteger, BigInteger>();
    mapeamentoNodular = amigoDataAccessObject.mapeiaRelacoes();

    for (Info infoAmigo : infoAmigosHomens) {
      amigos.add(new AmigoHomem(processing, infoAmigo));

      logger.info("\n" + "(" + infoAmigo.getUname() + "=" + infoAmigo.getUid()
          + ")" + ", que e de nacionalidade " + infoAmigo.getOrigem()
          + ", e do genero " + infoAmigo.getSex() + " tambem eh seu amigo nro "
          + infoAmigo.getAgerank() + " esta mapeado para os amigos: "
          + mapeamentoNodular.get(infoAmigo.getUid()));
    }

    for (Info infoAmigo : infoAmigosMulheres) {
      amigos.add(new AmigoMulher(processing, infoAmigo));

      logger.info("\n" + "(" + infoAmigo.getUname() + "=" + infoAmigo.getUid()
          + ")" + ", que e de nacionalidade " + infoAmigo.getOrigem()
          + ", e do genero " + infoAmigo.getSex() + " tambem eh seu amigo nro "
          + infoAmigo.getAgerank() + " esta mapeado para os amigos: "
          + mapeamentoNodular.get(infoAmigo.getUid()));
    }

    info = new Info(processing, font, new BigInteger("1"), "Nome", "Sexo",
        "Origem", 1);
    amigoMulher = new AmigoMulher(processing, info);

    info = new Info(processing, font, new BigInteger("1"), "Nome", "Sexo",
        "Origem", 1);
    amigoMulher = new AmigoMulher(processing, info);

  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    grafo.clear();
    mapeamentoNodular.clear();
  }

  @Test
  public void test() {
    fail("Not yet implemented");

  }
}
