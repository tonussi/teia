package view;

public class EspecialistaMovimento implements Movedor {

  @Override
  public void combina(VetorComposto vetorComposto) {

    vetorComposto.local.add(vetorComposto.dir);
    vetorComposto.local.add(vetorComposto.acel);

  }

}
