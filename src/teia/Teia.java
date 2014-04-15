package teia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Amigo;
import model.AmigoHomem;
import model.AmigoMulher;
import model.DesenhistaAmigoDestacado;
import model.DesenhistaAmigoHomem;
import model.DesenhistaAmigoMulher;
import model.Info;
import model.Vertice;
import processing.core.PApplet;
import processing.core.PFont;
import dao.AmigoDataAccessObjectImpl;
import dao.DBConnection;
import dao.DBConnectionImpl;

/**
 * Essa classe cria uma teia de amigos da maneira que
 * existem relacoes entre os amigos da rede social cada
 * amigo pode ser homem ou mulher, ser de nascionalidades
 * diferentes, dentre outras propriedades. Basicamente a
 * construcao e implementacao dessas propriedades devem
 * gerar novas classes modelo e devem ser construidas
 * realizacoes em cima dessas propriedades diferentes
 * 
 * 
 * @author lucastonussi
 */
public class Teia extends PApplet {

  List<Amigo> amigos;
  List<AmigoMulher> amigosMulheres;
  List<AmigoHomem> amigosHomens;
  List<Info> infosAmigosHomens;
  List<Info> infosAmigosMulheres;
  List<DesenhistaAmigoHomem> desenhistasAmigosHomens;
  List<DesenhistaAmigoMulher> desenhistasAmigosMulheres;
  List<DesenhistaAmigoDestacado> desenhistasAmigosDestacados;
  List<Vertice> grafo;
  Map<BigInteger, BigInteger> mapeamentoNodular;
  AmigoDataAccessObjectImpl amigoDataAccessObject;
  DBConnection dbConnection;
  PFont font;

  public Teia() {
    /*
     * Cria uma fonte para escrever as infos dos amigos na
     * rede
     */
    font = createFont("Helvetica", 8, true);

    /*
     * Cria listagem de amigos tipo homem
     */
    amigosHomens = new ArrayList<AmigoHomem>();

    /*
     * Cria listagem de amigos tipo mulher
     */
    amigosMulheres = new ArrayList<AmigoMulher>();

    /*
     * Cria listagem de desenhadores de amigos homens
     */
    desenhistasAmigosHomens = new ArrayList<DesenhistaAmigoHomem>();

    /*
     * Cria listagem de desenhadores de amigos mulheres
     */
    desenhistasAmigosMulheres = new ArrayList<DesenhistaAmigoMulher>();

    /*
     * Cria listagem de desenhadores de amigos destacados do
     * todo
     */
    desenhistasAmigosDestacados = new ArrayList<DesenhistaAmigoDestacado>();

    /*
     * Cria uma conexao com o banco de dados
     */
    dbConnection = new DBConnectionImpl("lucastonussi", "lucastonussi",
        "hung4ro5");

    /*
     * Cria objeto de acesso as informacoes da rede social
     * em questao para resolver problemas de listagem
     */
    amigoDataAccessObject = new AmigoDataAccessObjectImpl(dbConnection);

    /*
     * Cria listagem das infos dos homens na rede para
     * posteriormente relacionar com as especializacoes de
     * amigos em homens e mulheres
     */
    infosAmigosHomens = new ArrayList<Info>(
        amigoDataAccessObject.listaAmigosPorGenero("male"));

    /*
     * Cria listagem das infos das mulheres na rede para
     * posteriormente relacionar com as especializacoes de
     * amigos em homens e mulheres
     */
    infosAmigosMulheres = new ArrayList<Info>(
        amigoDataAccessObject.listaAmigosPorGenero("female"));

    /*
     * Cria lista de relacoes entre os amigos da rede cada
     * nodo tem max(n) arestas
     */
    grafo = new ArrayList<Vertice>(amigoDataAccessObject.listaRelacoes());

    /*
     * Cria mapeamento direto da tabela de relacoes em um
     * mapeamento hash
     */
    mapeamentoNodular = new HashMap<BigInteger, BigInteger>();
    mapeamentoNodular = amigoDataAccessObject.mapeiaRelacoes();

    /*
     * Cria lista de informacao para cada amigo tipo homem
     */
    for (Info infoHomem : infosAmigosHomens)
      amigosHomens.add(new AmigoHomem(infoHomem));

    /*
     * Adiciona cada info ao seu respectivo amigo tipo homem
     */
    for (AmigoHomem amigoHomem : amigosHomens)
      desenhistasAmigosHomens.add(new DesenhistaAmigoHomem(this, font,
          amigoHomem));

    /*
     * Cria lista de informacao para cada amigo tipo mulher
     */
    for (Info infoMulher : infosAmigosMulheres)
      amigosMulheres.add(new AmigoMulher(infoMulher));

    /*
     * Adiciona cada info ao seu respectivo amigo tipo
     * mulher
     */
    for (AmigoMulher amigoMulher : amigosMulheres)
      desenhistasAmigosMulheres.add(new DesenhistaAmigoMulher(this, font,
          amigoMulher));
  }

  /**
   * 
   * @see http://wiki.processing.org/w/
   *      Window_Size_and_Full_Screen
   * 
   *      O codigo a seguir cria a possibilidade de voce
   *      redimensionar o seu canvas onde o processing
   *      estara rodando a aplicacao.
   *      <code>if (frame != null) frame.setResizable(true);</code>
   * 
   * 
   * @see https ://en.wikipedia.org/wiki/4 K_resolution Para
   *      saber sobre
   * 
   *      resolucoes possiveis para tirar grandes shots
   *      basta visitar o link abaixo e entender mais sobre
   *      view port por exemplo
   *      <code>size(3840, 2160)</code> voce estara com uma
   *      resolucao UHD Ultra high definition television,
   *      aspect ratio de 1.78:1 (16:9) e 8,294,400 pixels
   */
  @Override
  public void setup() {
    size(1200, 700);

    if (frame != null)
      frame.setResizable(true);

    translate(width / 2, height / 2);
  }

  /**
   * Mantenha o draw() o mais simples possivel essa funcao
   * faz parte do processing e ficara rodando os desenhos.
   * 
   * Procure separar em classes especilizadas no desenho que
   * vc quer fazer. Eliminando ao maximo possivel os
   * condicionais.
   */
  @Override
  public void draw() {
    background(43);
    translate(width / 2, height / 2);

    for (DesenhistaAmigoHomem amigo : desenhistasAmigosHomens) {
      amigo.display();
      amigo.mova();
    }

    for (DesenhistaAmigoMulher amigo : desenhistasAmigosMulheres) {
      amigo.display();
      amigo.mova();
    }

  }

  /**
   * Aperte 's' com o aplicativo rodando que voce gerada
   * screenshots dentro do diretorio
   * <code>/path-to-this-project/pics/...</code>
   */
  @Override
  public void keyPressed() {
    if (key == 's')
      save("pics/teia" + random(1000) + ".jpg");
  }

  /**
   * Chamada principal do software
   * 
   * @param _args
   */
  public static void main(String _args[]) {
    PApplet.main(new String[] { teia.Teia.class.getName() });
  }
}
