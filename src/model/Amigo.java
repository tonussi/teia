package model;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Amigo {

  protected Info info;
  protected PVector local, dir, acel;
  PApplet processing;

  public Amigo(PApplet processing, Info info) {

    this.processing = processing;
    this.info = info;

    local = new PVector(processing.random(processing.width),
        processing.random(processing.height));
    dir = PVector.random2D();
    acel = PVector.random2D();
  }

  public void mova() {
    local.add(dir);
    local.add(acel);
  }

  public void display() {
  }
}