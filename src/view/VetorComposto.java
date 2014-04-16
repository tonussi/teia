package view;

import processing.core.PVector;

public class VetorComposto {

  PVector local, dir, acel;

  public VetorComposto() {

    local = PVector.random2D();

    dir = PVector.random2D();

    acel = PVector.random2D();

  }

}
