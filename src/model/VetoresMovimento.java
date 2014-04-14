package model;

import processing.core.PApplet;
import processing.core.PVector;

public class VetoresMovimento {

  PApplet processing;
  PVector local, dir, acel;

  public VetoresMovimento(PApplet processing) {
    this.processing = processing;

    local = new PVector(processing.random(processing.width),
        processing.random(processing.height));
    dir = PVector.random2D();
    acel = PVector.random2D();
  }

}
