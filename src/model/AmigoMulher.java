package model;

import processing.core.PApplet;

public class AmigoMulher extends Amigo {

  public AmigoMulher(PApplet processing, Info info) {
    super(processing, info);
  }

  @Override
  public void display() {
    processing.pushMatrix();
    processing.translate(local.x, local.y);

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
    processing.rect(local.x - 3, local.y, 6, 3);

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
    processing.ellipse(local.x, local.y, 20, 20);

    info.adicionaInformacao(local.x, local.y);

    processing.popMatrix();
  }
}
