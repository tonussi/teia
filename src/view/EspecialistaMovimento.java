package view;

import processing.core.PVector;

public class EspecialistaMovimento implements Movedor {

  @Override
  public void combina(VetorComposto vets) {

    vets.local.add(vets.dir);
    vets.local.add(vets.vel);

    vets.local.limit(300);

  }

  @Override
  public void formacaoNumeroUm(VetorComposto vets) {

    vets.local.add(vets.dir);
    vets.local.limit(90);
    vets.local.mult(angulo(vets));
    vets.local.rotate(12.6f);
    vets.dir.sub(vets.local);

  }

  @Override
  public float angulo(VetorComposto vets) {
    return -PVector.angleBetween(vets.dir, vets.vel);
  }

}
