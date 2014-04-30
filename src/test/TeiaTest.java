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
import processing.core.PVector;
import view.VetorComposto;
import dao.AmigoDataAccessObjectImpl;
import dao.DBConnection;
import dao.DBConnectionImpl;

/**
 * @author lucastonussi
 * 
 */
public class TeiaTest {

  List<Amigo> amigos;
  List<AmigoMulher> amigosMulheres;
  List<AmigoHomem> amigosHomens;
  List<Info> infoAmigosHomens;
  List<Info> infoAmigosMulheres;
  List<Vertice> grafo;
  Map<BigInteger, BigInteger> mapeamentoNodular;
  AmigoDataAccessObjectImpl amigoDataAccessObject;
  DBConnection dbConnection;
  PFont font;
  PApplet processing;
  Info infoHomem, infoMulher;
  AmigoHomem amigoHomem;
  AmigoMulher amigoMulher;
  VetorComposto vetorComposto;

  private final Logger logger = Logger.getLogger(TeiaTest.class.getName());

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {

    processing = new PApplet();

    vetorComposto = new VetorComposto(PVector.random2D(), PVector.random2D(),
        PVector.random2D());

    font = processing.createFont("Helvetica", 8, true);

    amigos = new ArrayList<>();

    amigosMulheres = new ArrayList<>();

    amigosHomens = new ArrayList<>();

    dbConnection = new DBConnectionImpl("lucastonussi", "lucastonussi",
        "hung4ro5");

    amigoDataAccessObject = new AmigoDataAccessObjectImpl(dbConnection);

    infoAmigosHomens = new ArrayList<>(
        amigoDataAccessObject.listaAmigosPorGeneroEAgerank("male", 100));

    infoAmigosMulheres = new ArrayList<>(
        amigoDataAccessObject.listaAmigosPorGeneroEAgerank("female", 100));

    grafo = new ArrayList<Vertice>(amigoDataAccessObject.listaRelacoes());

    mapeamentoNodular = new HashMap<BigInteger, BigInteger>();
    mapeamentoNodular = amigoDataAccessObject.mapeiaRelacoes();

    infoHomem = new Info(new BigInteger("1318200713"), "Diego Fagundes",
        "male", "pt_BR", 193);
    amigoHomem = new AmigoHomem(infoHomem, vetorComposto);

    infoMulher = new Info(new BigInteger("580905942"), "Erica Mattos",
        "female", "pt_BR", 227);
    amigoMulher = new AmigoMulher(infoMulher, vetorComposto);
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
