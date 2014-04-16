package view;

import model.Amigo;
import model.AmigoHomem;
import model.AmigoMulher;


public interface DesenhadorEspecializado {

  public void displayHomem(AmigoHomem amigoHomem);

  public void displayMulher(AmigoMulher amigoMulher);

  public void displayDestacadoHomem(AmigoHomem amigoHomem);

  public void displayDestacadoMulher(AmigoMulher amigoMulher);

  public void escreva(Amigo amigo);

}
