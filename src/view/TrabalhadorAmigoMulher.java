package view;

import java.util.List;

import model.AmigoMulher;

public class TrabalhadorAmigoMulher extends Thread {

  List<AmigoMulher> amigosMulheres;
  DesenhadorEspecializado especialistaDesenho;
  MovedorEspecializado especialistaMovimento;

  public TrabalhadorAmigoMulher(List<AmigoMulher> amigosMulheres,
      DesenhadorEspecializado especialistaDesenho, MovedorEspecializado especialistaMovimento) {
    this.amigosMulheres = amigosMulheres;
    this.especialistaDesenho = especialistaDesenho;
    this.especialistaMovimento = especialistaMovimento;
  }

  @Override
  public void run() {
    try {
      for (AmigoMulher amigoMulher : amigosMulheres) {
        especialistaDesenho.displayMulher(amigoMulher);
        especialistaDesenho.escrevaInfoAmigoMulher(amigoMulher);
        especialistaMovimento.combina(amigoMulher.getVetorComposto());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
