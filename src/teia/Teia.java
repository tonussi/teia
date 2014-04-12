package teia;

import java.util.ArrayList;
import java.util.List;

import model.Amigo;
import model.AmigoHomem;
import model.AmigoMulher;
import model.Aresta;
import model.Info;
import model.Nodo;
import processing.core.PApplet;
import processing.core.PFont;
import dao.AmigoDataAccessObjectImpl;
import dao.DBConnection;
import dao.DBConnectionImpl;

public class Teia extends PApplet {

	List<Amigo> amigos;
	List<Info> infoAmigosHomens;
	List<Info> infoAmigosMulheres;
	List<Aresta> arestas;
	List<Nodo> nodos;
	AmigoDataAccessObjectImpl amigoDataAccessObject;
	DBConnection dbConnection;
	PFont font;

	public Teia() {
		/**
		 * Cria uma fonte para escrever as infos dos amigos na rede
		 */
		font = createFont("Monospace", 6);

		/**
		 * Cria listagem de amigos todos da rede que receberao informacoes das
		 * especializacoes dentre outras coisas como nodos e arestas
		 */
		amigos = new ArrayList<Amigo>();

		/**
		 * Cria uma conexao com o banco de dados
		 */
		dbConnection = new DBConnectionImpl("lucastonussi", "lucastonussi",
				"hung4ro5");

		/**
		 * Cria objeto de acesso as informacoes da rede social em questao para
		 * resolver problemas de listagem
		 */
		amigoDataAccessObject = new AmigoDataAccessObjectImpl(this, font,
				dbConnection);

		/**
		 * Cria listagem das infos dos homens na rede para posteriormente
		 * relacionar com as especializacoes de amigos em homens e mulheres
		 */
		infoAmigosHomens = new ArrayList<Info>(
				amigoDataAccessObject.listadeAmigos("male"));

		/**
		 * Cria listagem das infos das mulheres na rede para posteriormente
		 * relacionar com as especializacoes de amigos em homens e mulheres
		 */
		infoAmigosMulheres = new ArrayList<Info>(
				amigoDataAccessObject.listadeAmigos("female"));

		/**
		 * Processo de imersao pos processamento dos resultados das informacoes
		 * dos amigos do tipo homen
		 */
		for (Info infoAmigo : infoAmigosHomens)
			amigos.add(new AmigoHomem(this, infoAmigo));

		/**
		 * Processo de imersao pos processamento dos resultados das informacoes
		 * dos amigos do tipo mulher
		 */
		for (Info infoAmigo : infoAmigosMulheres)
			amigos.add(new AmigoMulher(this, infoAmigo));

	}

	/**
	 * Para saber sobre resolucoes possiveis para tirar grandes shots basta
	 * visitar o link abaixo e entender mais sobre view port por exemplo
	 * <code>size(3840 × 2160)</code> voce estara com uma resolucao UHD Ultra
	 * high definition television, aspect ratio de 1.78:1 (16:9) e 8,294,400
	 * pixels
	 * 
	 * @see https://en.wikipedia.org/wiki/4K_resolution
	 */
	@Override
	public void setup() {
		size(350, 350);
		translate(width / 2, height / 2);
	}

	/**
	 * Mantenha o draw() o mais simples possivel essa funcao faz parte do
	 * processing e ficara rodando os desenhos.
	 * 
	 * Procure separar em classes especilizadas no desenho que vc quer fazer.
	 * Eliminando ao maximo possivel os condicionais.
	 */
	@Override
	public void draw() {
		background(43);
		translate(width / 2, height / 2);
		for (Amigo amigo : amigos) {
			amigo.display();
			amigo.mova();
		}
	}

	/**
	 * Aperte 's' com o aplicativo rodando que voce gerada screenshots dentro do
	 * diretorio <code>/path-to-this-project/pics/...</code>
	 */
	@Override
	public void keyPressed() {
		if (key == 's')
			save("pics/teia" + random(0, 1000) + ".jpg");
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
