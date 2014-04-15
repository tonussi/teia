package model;

import processing.core.PApplet;

public class DesenhistaAmigoMulher implements Desenhador, Movedor {

  AmigoMulher amigoMulher;
  VetorComposto vetorComposto;
  PApplet processing;

  public DesenhistaAmigoMulher(PApplet processing, AmigoMulher amigoMulher) {

    this.processing = processing;
    this.amigoMulher = amigoMulher;
    this.vetorComposto = new VetorComposto();

  }

  @Override
  public void display() {
    processing.pushMatrix();
    processing.translate(vetorComposto.local.x, vetorComposto.local.y);

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
    processing.rect(vetorComposto.local.x - 3, vetorComposto.local.y, 6, 3);

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
    processing.ellipse(vetorComposto.local.x, vetorComposto.local.y, 20, 20);

    amigoMulher.info.adicionaInformacao(vetorComposto.local.x,
        vetorComposto.local.y);

    processing.popMatrix();
  }

  @Override
  public void mova() {
    vetorComposto.local.add(vetorComposto.dir);
    vetorComposto.local.add(vetorComposto.acel);
  }

}
