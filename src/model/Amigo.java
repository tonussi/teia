package model;

import view.VetorComposto;

public abstract class Amigo {

  VetorComposto vetorComposto;
  protected Info info;

  public Amigo(Info info, VetorComposto vetorComposto) {

    this.info = info;
    this.vetorComposto = vetorComposto;

  }

  public Info getInfo() {
    return info;
  }

  public VetorComposto getVetorComposto() {
    return vetorComposto;
  }

}