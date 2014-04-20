package view;

import processing.core.PVector;

public class VetorComposto {

  PVector local, dir, vel;

  public VetorComposto() {

    dir = PVector.random2D();
    local = new PVector(0, 0);
    vel = new PVector(0.9182682f, 0.39595902f, 0.0f);

  }

}
