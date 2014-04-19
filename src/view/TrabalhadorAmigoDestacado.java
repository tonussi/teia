package view;

import java.util.List;

import model.AmigoDestacado;

public class TrabalhadorAmigoDestacado extends Thread {

  List<AmigoDestacado> amigosHomens;
  DesenhadorEspecializado especialistaDesenho;
  MovedorEspecializado especialistaMovimento;

  public TrabalhadorAmigoDestacado(List<AmigoDestacado> amigosHomens,
      DesenhadorEspecializado especialistaDesenho, MovedorEspecializado especialistaMovimento) {
    this.amigosHomens = amigosHomens;
    this.especialistaDesenho = especialistaDesenho;
    this.especialistaMovimento = especialistaMovimento;
  }

  @Override
  public void run() {
    for (AmigoDestacado amigoDestacado : amigosHomens) {
      especialistaDesenho.escrevaInfoAmigoDestacado(amigoDestacado);
      especialistaDesenho.displayDestacado(amigoDestacado);
      especialistaMovimento.combina(amigoDestacado.getVetorComposto());
    }
  }
}
