package view;

import model.AmigoDestacado;
import model.AmigoHomem;
import model.AmigoMulher;
import processing.core.PApplet;
import processing.core.PFont;

public class EspecialistaDesenho implements Desenhador {

  PApplet processing;
  PFont font;

  public EspecialistaDesenho(PApplet processing, PFont font) {
    this.processing = processing;
    this.font = font;
  }

  @Override
  public void displayHomem(AmigoHomem amigoHomem) {

    processing.pushMatrix();
    processing.translate(amigoHomem.getVetorComposto().getLocal().x, amigoHomem
        .getVetorComposto().getLocal().y);

    /*
     * Desenho: Triangulo
     * 
     * Preenchimento: Branco
     * 
     * Contorno: Branco
     * 
     * Peso do Contorno: 1 pixel wide
     */
    processing.fill(255);
    processing.stroke(255);
    processing.strokeWeight(1);
    processing.triangle(amigoHomem.getVetorComposto().getLocal().x, amigoHomem
        .getVetorComposto().getLocal().y + 3, amigoHomem.getVetorComposto()
        .getLocal().x - 3, amigoHomem.getVetorComposto().getLocal().y,
        amigoHomem.getVetorComposto().getLocal().x + 3, amigoHomem
            .getVetorComposto().getLocal().y);

    /*
     * Desenho: Circulo
     * 
     * Preenchimento: Vazio
     * 
     * Contorno: Azul Escuro
     * 
     * Peso do Contorno: 2 pixel wide
     */
    processing.noFill();
    processing.strokeWeight(2);
    processing.stroke(88, 32, 244);
    processing.ellipse(amigoHomem.getVetorComposto().getLocal().x, amigoHomem
        .getVetorComposto().getLocal().y, 20, 20);
    processing.popMatrix();

  }

  @Override
  public void displayMulher(AmigoMulher amigoMulher) {

    processing.pushMatrix();
    processing.translate(amigoMulher.getVetorComposto().getLocal().x,
        amigoMulher.getVetorComposto().getLocal().y);

    /*
     * Desenho: Retangulo
     * 
     * Preenchimento: Branco
     * 
     * Contorno: Branco
     * 
     * Peso do Contorno: 1 pixel wide
     */
    processing.fill(255);
    processing.stroke(255);
    processing.strokeWeight(1);
    processing.rect(amigoMulher.getVetorComposto().getLocal().x - 3,
        amigoMulher.getVetorComposto().getLocal().y, 6, 3);

    /*
     * Desenho: Circulo
     * 
     * Preenchimento: Vazio
     * 
     * Contorno: Rosa Escuro
     * 
     * Peso do Contorno: 2 pixel wide
     */
    processing.noFill();
    processing.strokeWeight(2);
    processing.stroke(244, 32, 88);
    processing.ellipse(amigoMulher.getVetorComposto().getLocal().x, amigoMulher
        .getVetorComposto().getLocal().y, 20, 20);
    processing.popMatrix();

  }

  @Override
  public void displayDestacado(AmigoDestacado amigoDestacado) {

    processing.pushMatrix();
    processing.translate(amigoDestacado.getVetorComposto().getLocal().x,
        amigoDestacado.getVetorComposto().getLocal().y);

    /*
     * Desenho: Circulo
     * 
     * Preenchimento: Vazio
     * 
     * Contorno: Verde Plasma
     * 
     * Peso do Contorno: 2 pixel wide
     */
    processing.noFill();
    processing.strokeWeight(3);
    processing.stroke(88, 244, 32);
    processing.ellipse(amigoDestacado.getVetorComposto().getLocal().x,
        amigoDestacado.getVetorComposto().getLocal().y, 50, 50);

    /*
     * Desenho: Circulo
     * 
     * Preenchimento: Vazio
     * 
     * Contorno: Branco
     * 
     * Peso do Contorno: 2 pixel wide
     */
    processing.noFill();
    processing.stroke(255);
    processing.strokeWeight(2);
    processing.ellipse(amigoDestacado.getVetorComposto().getLocal().x,
        amigoDestacado.getVetorComposto().getLocal().y, 16, 16);

    /*
     * Desenho: Quad
     * 
     * Preenchimento: Vazio
     * 
     * Contorno: Alaranjado
     * 
     * Peso do Contorno: 2 pixel wide
     */
    processing.noFill();
    processing.strokeWeight(2);
    processing.stroke(281, 70, 49);
    processing.quad(amigoDestacado.getVetorComposto().getLocal().x - 9,
        amigoDestacado.getVetorComposto().getLocal().y + 2, amigoDestacado
            .getVetorComposto().getLocal().x - 3, amigoDestacado
            .getVetorComposto().getLocal().y + 3, amigoDestacado
            .getVetorComposto().getLocal().x + 3, amigoDestacado
            .getVetorComposto().getLocal().y - 5, amigoDestacado
            .getVetorComposto().getLocal().x + 3, amigoDestacado
            .getVetorComposto().getLocal().y + 7);
    processing.popMatrix();

  }

  @Override
  public void escrevaInfoAmigoHomem(AmigoHomem amigoHomem) {

    processing.pushMatrix();
    processing.translate(amigoHomem.getVetorComposto().getLocal().x, amigoHomem
        .getVetorComposto().getLocal().y);
    processing.fill(255, 70);
    processing.textFont(font);
    processing.text(amigoHomem.getInfo().toString(), amigoHomem
        .getVetorComposto().getLocal().x, amigoHomem.getVetorComposto()
        .getLocal().y + 22);
    processing.popMatrix();

  }

  @Override
  public void escrevaInfoAmigoMulher(AmigoMulher amigoMulher) {

    processing.pushMatrix();
    processing.translate(amigoMulher.getVetorComposto().getLocal().x,
        amigoMulher.getVetorComposto().getLocal().y);
    processing.fill(255, 70);
    processing.textFont(font);
    processing.text(amigoMulher.getInfo().toString(), amigoMulher
        .getVetorComposto().getLocal().x, amigoMulher.getVetorComposto()
        .getLocal().y);
    processing.popMatrix();

  }

  @Override
  public void escrevaInfoAmigoDestacado(AmigoDestacado amigoDestacado) {

    processing.pushMatrix();
    processing.translate(amigoDestacado.getVetorComposto().getLocal().x,
        amigoDestacado.getVetorComposto().getLocal().y);
    processing.fill(255, 70);
    processing.textFont(font);
    processing.text(amigoDestacado.getInfo().toString(), amigoDestacado
        .getVetorComposto().getLocal().x, amigoDestacado.getVetorComposto()
        .getLocal().y + 22);
    processing.popMatrix();

  }

}
