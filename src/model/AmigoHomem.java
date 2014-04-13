package model;

import processing.core.PApplet;

public class AmigoHomem extends Amigo {

  public AmigoHomem(PApplet processing, Info info) {
    super(processing, info);
  }

  @Override
  public void display() {
    processing.pushMatrix();
    processing.translate(local.x, local.y);

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
    processing.triangle(local.x, local.y - 3, local.x - 3, local.y,
        local.x + 3, local.y);

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
    processing.ellipse(local.x, local.y, 20, 20);

    info.adicionaInformacao(local.x, local.y);

    processing.popMatrix();
  }
}
