package model;

import processing.core.PApplet;

public class Aresta {
  private int comprimento;
  private int largura;
  private int forca;
  PApplet parent;

  public Aresta(PApplet p, int comprimento, int largura, int forca) {
    super();

    parent = p;
    this.comprimento = comprimento;
    this.largura = largura;
    this.forca = forca;
  }

  public void display() {
  }

  public int getComprimento() {
    return comprimento;
  }

  public void setComprimento(int comprimento) {
    this.comprimento = comprimento;
  }

  public int getLargura() {
    return largura;
  }

  public void setLargura(int largura) {
    this.largura = largura;
  }

  public int getForca() {
    return forca;
  }

  public void setForca(int forca) {
    this.forca = forca;
  }
}
