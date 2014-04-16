package view;

import java.util.List;

import model.AmigoHomem;

public class TrabalhadorAmigoHomem extends Thread {

  List<AmigoHomem> amigosHomens;
  DesenhadorEspecializado especialistaDesenho;
  Movedor especialistaMovimento;

  public TrabalhadorAmigoHomem(List<AmigoHomem> amigosHomens,
      DesenhadorEspecializado especialistaDesenho, Movedor especialistaMovimento) {
    this.amigosHomens = amigosHomens;
    this.especialistaDesenho = especialistaDesenho;
    this.especialistaMovimento = especialistaMovimento;
  }

  @Override
  public void run() {
    try {
      for (AmigoHomem amigoHomem : amigosHomens) {
        especialistaDesenho.displayHomem(amigoHomem);
        especialistaDesenho.escreva(amigoHomem);
        especialistaMovimento.combina(amigoHomem.getVetorComposto());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
