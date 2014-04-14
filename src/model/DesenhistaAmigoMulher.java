package model;

import processing.core.PApplet;

public class DesenhistaAmigoMulher extends AmigoMulher implements Desenhador,
    Movedor {

  VetoresMovimento vetoresMovimento;
  PApplet processing;

  public DesenhistaAmigoMulher(PApplet processing, Info info) {

    super(info);
    this.processing = processing;
    this.vetoresMovimento = new VetoresMovimento(this.processing);

  }

  @Override
  public void display() {
    processing.pushMatrix();
    processing.translate(vetoresMovimento.local.x, vetoresMovimento.local.y);

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
    processing.rect(vetoresMovimento.local.x - 3, vetoresMovimento.local.y, 6,
        3);

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
    processing.ellipse(vetoresMovimento.local.x, vetoresMovimento.local.y, 20,
        20);

    info.adicionaInformacao(vetoresMovimento.local.x, vetoresMovimento.local.y);

    processing.popMatrix();
  }

  @Override
  public void mova() {
    vetoresMovimento.local.add(vetoresMovimento.dir);
    vetoresMovimento.local.add(vetoresMovimento.acel);
  }
}
