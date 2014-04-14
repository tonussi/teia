package model;

import processing.core.PApplet;

public class DesenhistaAmigoHomem extends AmigoHomem implements Desenhador,
    Movedor {

  VetoresMovimento vetoresMovimento;
  PApplet processing;

  public DesenhistaAmigoHomem(PApplet processing, Info info) {

    super(info);
    this.processing = processing;
    this.vetoresMovimento = new VetoresMovimento(this.processing);

  }

  @Override
  public void display() {
    processing.pushMatrix();
    processing.translate(vetoresMovimento.local.x, vetoresMovimento.local.y);

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
    processing.triangle(vetoresMovimento.local.x, vetoresMovimento.local.y - 3,
        vetoresMovimento.local.x - 3, vetoresMovimento.local.y,
        vetoresMovimento.local.x + 3, vetoresMovimento.local.y);

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
