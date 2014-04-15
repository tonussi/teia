package model;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class DesenhistaAmigoDestacado implements Desenhador, Movedor {

  AmigoDestacado amigoDestacado;
  VetorComposto vetorComposto;
  PApplet processing;
  PFont font;

  public DesenhistaAmigoDestacado(PApplet processing, PFont font,
      AmigoDestacado amigoDestacado) {

    this.processing = processing;
    this.font = font;
    this.amigoDestacado = amigoDestacado;
    this.vetorComposto = new VetorComposto();

  }

  @Override
  public void display() {
    processing.pushMatrix();
    processing.translate(vetorComposto.local.x, vetorComposto.local.y);

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
    processing.ellipse(vetorComposto.local.x, vetorComposto.local.y, 80, 80);

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
    processing.triangle(vetorComposto.local.x, vetorComposto.local.y - 3,
        vetorComposto.local.x - 3, vetorComposto.local.y,
        vetorComposto.local.x + 3, vetorComposto.local.y);

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
    processing.ellipse(vetorComposto.local.x, vetorComposto.local.y, 20, 20);

    processing.popMatrix();
  }

  @Override
  public void mova() {
    vetorComposto.local.add(vetorComposto.dir);
    vetorComposto.local.add(vetorComposto.acel);
  }

  @Override
  public void escreve() {
    processing.pushMatrix();
    processing.translate(vetorComposto.local.x, vetorComposto.local.y);
    processing.fill(255, 60);
    processing.textAlign(PConstants.LEFT);
    processing.textFont(font);
    processing.text(amigoDestacado.info.toString(), vetorComposto.local.x + 12,
        vetorComposto.local.y - 12);
    processing.popMatrix();
  }

}
