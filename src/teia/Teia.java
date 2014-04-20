package teia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.AmigoDestacado;
import model.AmigoHomem;
import model.AmigoMulher;
import model.Info;
import model.Vertice;
import processing.core.PApplet;
import processing.core.PFont;
import view.Desenhador;
import view.EspecialistaDesenho;
import view.EspecialistaMovimento;
import view.Movedor;
import view.TrabalhadorAmigoDestacado;
import view.TrabalhadorAmigoHomem;
import view.TrabalhadorAmigoMulher;
import view.VetorComposto;
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

  List<AmigoMulher> amigosMulheres;
  List<AmigoHomem> amigosHomens;
  List<AmigoDestacado> amigosDestacados;

  List<Info> infosAmigosHomens;
  List<Info> infosAmigosMulheres;
  List<Info> infosAmigosDestacados;

  Desenhador especialistaDesenho;
  Movedor especialistaMovimento;

  List<Vertice> grafo;
  Map<BigInteger, BigInteger> mapeamentoNodular;
  AmigoDataAccessObjectImpl amigoDataAccessObject;
  DBConnection dbConnection;
  PFont font;

  TrabalhadorAmigoHomem trabalhadorAmigoHomem;
  TrabalhadorAmigoMulher trabalhadorAmigoMulher;
  TrabalhadorAmigoDestacado trabalhadorAmigoDestacado;

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
     * Cria listagem de amigos tipo mulher
     */
    amigosDestacados = new ArrayList<AmigoDestacado>();

    /*
     * Cria listagem de amigos destacados (homens ou
     * mulheres)
     */
    infosAmigosDestacados = new ArrayList<Info>();

    /*
     * Cria um desenhista especializado em todos os desenhos
     */
    especialistaDesenho = new EspecialistaDesenho(this, font);

    /*
     * Cria um especialista em movimento
     */
    especialistaMovimento = new EspecialistaMovimento();

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
     * Cris listagem das infos de amigos destacados baseado
     * em nenhuma regra podem vir homens ou mulhers.
     */
    infosAmigosDestacados = new ArrayList<Info>(
        amigoDataAccessObject.listaAmigosDestacados());

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

    /*
     * Preenche o mapeamento nodular com dados das relacoes
     */
    mapeamentoNodular = amigoDataAccessObject.mapeiaRelacoes();

    /*
     * Cria lista de informacao para cada amigo tipo homem
     */
    for (Info infoHomem : infosAmigosHomens)
      amigosHomens.add(new AmigoHomem(infoHomem, new VetorComposto()));

    /*
     * Cria lista de informacao para cada amigo tipo mulher
     */
    for (Info infoMulher : infosAmigosMulheres)
      amigosMulheres.add(new AmigoMulher(infoMulher, new VetorComposto()));

    /*
     * Cria lista de informacao para cada amigo tipo
     * destacado
     */
    for (Info infoDestacado : infosAmigosDestacados)
      amigosDestacados.add(new AmigoDestacado(infoDestacado,
          new VetorComposto()));

    /*
     * Inicia threads trabalhadores dos desenhos dos amigos
     * tipo homem
     */
    trabalhadorAmigoHomem = new TrabalhadorAmigoHomem(amigosHomens,
        especialistaDesenho, especialistaMovimento);
    trabalhadorAmigoHomem.start();

    /*
     * Inicia threads trabalhadores dos desenhos dos amigos
     * tipo mulher
     */
    trabalhadorAmigoMulher = new TrabalhadorAmigoMulher(amigosMulheres,
        especialistaDesenho, especialistaMovimento);
    trabalhadorAmigoMulher.setPriority(10);
    trabalhadorAmigoMulher.start();

    /*
     * Inicia threads trabalhadores dos desenhos dos amigos
     * tipo mulher
     */
    trabalhadorAmigoDestacado = new TrabalhadorAmigoDestacado(amigosDestacados,
        especialistaDesenho, especialistaMovimento);
    trabalhadorAmigoDestacado.setPriority(4);
    trabalhadorAmigoDestacado.start();
  }

  /**
   * 
   * @see http://wiki.processing.org/w/
   *      Window_Size_and_Full_Screen
   * 
   *      O codigo a seguir cria a possibilidade de voce
   *      redimensionar o seu canvas onde o processing
   *      estara rodando a aplicacao.
   *      <code>if (frame != null)
   *      frame.setResizable(true);</code>
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

    size(800, 450);

    if (frame != null)
      frame.setResizable(true);

    translate(width / 2, height / 2);

    background(43);

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

    trabalhadorAmigoHomem.run();

    trabalhadorAmigoMulher.run();

    trabalhadorAmigoDestacado.run();
  }

  /**
   * Aperte 's' com o aplicativo rodando que voce gerada
   * screenshots dentro do diretorio
   * <code>/path-to-this-project/pics/...</code>
   */
  @Override
  public void keyPressed() {
    if (key == 's')
      save("pics/teia" + (this.frameCount - this.random(1000)) + ".jpg");
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
