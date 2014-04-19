package view;

import processing.core.PVector;

public class VetorComposto {

  PVector local, dir, acel;

  public VetorComposto() {

    local = new PVector(0, 0);

    dir = PVector.random2D();

    acel = new PVector(34e-3F, 12e-2F);

  }

}
