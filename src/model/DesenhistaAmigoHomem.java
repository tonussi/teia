package model;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class DesenhistaAmigoHomem implements Desenhador, Movedor {

  VetorComposto vetorComposto;
  AmigoHomem amigoHomem;
  PApplet processing;
  PFont font;

  public DesenhistaAmigoHomem(PApplet processing, PFont font,
      AmigoHomem amigoHomem) {

    this.font = font;
    this.amigoHomem = amigoHomem;
    this.processing = processing;
    this.vetorComposto = new VetorComposto();

  }

  @Override
  public void display() {
    processing.pushMatrix();
    processing.translate(vetorComposto.local.x, vetorComposto.local.y);

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
    processing.triangle(vetorComposto.local.x, vetorComposto.local.y + 3,
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
    processing.fill(255, 60);
    processing.textAlign(PConstants.LEFT);
    processing.textFont(font);
    processing.text(toString(), vetorComposto.local.x + 12,
        vetorComposto.local.y + 12);
  }

}
