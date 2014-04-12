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

    processing.fill(255);

    processing.rect(local.x - 3, local.y, 6, 3);

    processing.stroke(255);
    processing.strokeWeight(2);
    processing.noFill();

    processing.ellipse(local.x, local.y, 20, 20);

    info.adicionaInformacao(local.x, local.y);

    processing.popMatrix();
  }
}
