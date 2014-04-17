package view;

import model.AmigoDestacado;
import model.AmigoHomem;
import model.AmigoMulher;

public interface DesenhadorEspecializado {

  public void displayHomem(AmigoHomem amigoHomem);

  public void displayMulher(AmigoMulher amigoMulher);

  public void escrevaInfoAmigoHomem(AmigoHomem amigoHomem);

  public void escrevaInfoAmigoMulher(AmigoMulher amigoMulher);

  public void escrevaInfoAmigoDestacado(AmigoDestacado amigoDestacado);

  public void displayDestacado(AmigoDestacado amigoDestacado);

}
