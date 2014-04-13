/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;

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
  Info infoHomem, infoMulher;
  Amigo amigoHomem, amigoMulher;

  private final Logger logger = Logger.getLogger(TeiaTest.class.getName());

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {

    processing = new PApplet();

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
      logger.info(infoAmigo.toString());
    }

    for (Info infoAmigo : infoAmigosMulheres) {
      amigos.add(new AmigoMulher(processing, infoAmigo));
      logger.info(infoAmigo.toString());
    }

    infoHomem = new Info(processing, font, new BigInteger("1318200713"),
        "Diego Fagundes", "male", "pt_BR", 193);
    amigoHomem = new AmigoHomem(processing, infoHomem);

    infoMulher = new Info(processing, font, new BigInteger("580905942"),
        "Erica Mattos", "female", "pt_BR", 227);
    amigoMulher = new AmigoMulher(processing, infoMulher);
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    grafo.clear();
    mapeamentoNodular.clear();
    amigos.clear();
    infoAmigosHomens.clear();
    infoAmigosMulheres.clear();
    dbConnection.close();
  }

  @Test
  public void testSeVerticeTemIdentificadorHomemIgualAoInfoHomem() {
    for (Vertice vertice : grafo)
      if (vertice.getIdentificador().equals(new BigInteger("1318200713")))
        assertEquals(infoHomem.getUid(), vertice.getIdentificador());
  }

  @Test
  public void testSeVerticeTemIdentificadorMulherIgualAoInfoMulher() {
    for (Vertice vertice : grafo)
      if (vertice.getIdentificador().equals(new BigInteger("580905942")))
        assertEquals(infoMulher.getUid(), vertice.getIdentificador());
  }

}
