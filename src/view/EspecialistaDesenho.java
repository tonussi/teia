package view;

import model.AmigoDestacado;
import model.AmigoHomem;
import model.AmigoMulher;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class EspecialistaDesenho implements DesenhadorEspecializado {

  PApplet processing;
  PFont font;

  public EspecialistaDesenho(PApplet processing, PFont font) {

    this.processing = processing;
    this.font = font;

  }

  @Override
  public void displayHomem(AmigoHomem amigoHomem) {

    processing.pushMatrix();
    processing.translate(amigoHomem.getVetorComposto().local.x,
        amigoHomem.getVetorComposto().local.y);

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
    processing.triangle(amigoHomem.getVetorComposto().local.x,
        amigoHomem.getVetorComposto().local.y + 3,
        amigoHomem.getVetorComposto().local.x - 3,
        amigoHomem.getVetorComposto().local.y,
        amigoHomem.getVetorComposto().local.x + 3,
        amigoHomem.getVetorComposto().local.y);

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
    processing.ellipse(amigoHomem.getVetorComposto().local.x,
        amigoHomem.getVetorComposto().local.y, 20, 20);
    processing.popMatrix();

  }

  @Override
  public void displayMulher(AmigoMulher amigoMulher) {

    processing.pushMatrix();
    processing.translate(amigoMulher.getVetorComposto().local.x,
        amigoMulher.getVetorComposto().local.y);

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
    processing.rect(amigoMulher.getVetorComposto().local.x - 3,
        amigoMulher.getVetorComposto().local.y, 6, 3);

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
    processing.ellipse(amigoMulher.getVetorComposto().local.x,
        amigoMulher.getVetorComposto().local.y, 20, 20);
    processing.popMatrix();

  }

  @Override
  public void displayDestacado(AmigoDestacado amigoDestacado) {

    processing.pushMatrix();
    processing.translate(amigoDestacado.getVetorComposto().local.x,
        amigoDestacado.getVetorComposto().local.y);

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
    processing.strokeWeight(2);
    processing.stroke(88, 244, 32);
    processing.ellipse(amigoDestacado.getVetorComposto().local.x,
        amigoDestacado.getVetorComposto().local.y, 80, 80);

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
    processing.triangle(amigoDestacado.getVetorComposto().local.x,
        amigoDestacado.getVetorComposto().local.y - 3,
        amigoDestacado.getVetorComposto().local.x - 3,
        amigoDestacado.getVetorComposto().local.y,
        amigoDestacado.getVetorComposto().local.x + 3,
        amigoDestacado.getVetorComposto().local.y);

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
    processing.ellipse(amigoDestacado.getVetorComposto().local.x,
        amigoDestacado.getVetorComposto().local.y, 20, 20);
    processing.popMatrix();

  }

  @Override
  public void escrevaInfoAmigoHomem(AmigoHomem amigoHomem) {

    processing.pushMatrix();
    processing.translate(amigoHomem.getVetorComposto().local.x,
        amigoHomem.getVetorComposto().local.y);
    processing.fill(255, 60);
    processing.textAlign(PConstants.LEFT);
    processing.textFont(font);
    processing.text(amigoHomem.getInfo().toString(),
        amigoHomem.getVetorComposto().local.x,
        amigoHomem.getVetorComposto().local.y + 22);
    processing.popMatrix();

  }

  @Override
  public void escrevaInfoAmigoMulher(AmigoMulher amigoMulher) {

    processing.pushMatrix();
    processing.translate(amigoMulher.getVetorComposto().local.x,
        amigoMulher.getVetorComposto().local.y);
    processing.fill(255, 60);
    processing.textAlign(PConstants.LEFT);
    processing.textFont(font);
    processing.text(amigoMulher.getInfo().toString(),
        amigoMulher.getVetorComposto().local.x,
        amigoMulher.getVetorComposto().local.y + 22);
    processing.popMatrix();

  }

  @Override
  public void escrevaInfoAmigoDestacado(AmigoDestacado amigoDestacado) {

    processing.pushMatrix();
    processing.translate(amigoDestacado.getVetorComposto().local.x,
        amigoDestacado.getVetorComposto().local.y);
    processing.fill(255, 60);
    processing.textAlign(PConstants.LEFT);
    processing.textFont(font);
    processing.text(amigoDestacado.getInfo().toString(),
        amigoDestacado.getVetorComposto().local.x,
        amigoDestacado.getVetorComposto().local.y + 22);
    processing.popMatrix();

  }

}
