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

    processing.fill(255);
    processing.noStroke();
    processing.triangle(local.x, local.y + 3, local.x + 3, local.y,
        local.x - 3, local.y);

    processing.stroke(88, 32, 244);
    processing.strokeWeight(2);
    processing.noFill();

    processing.ellipse(local.x, local.y, 20, 20);

    info.adicionaInformacao(local.x, local.y);

    processing.popMatrix();
  }
}
