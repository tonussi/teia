package view;

import java.math.BigInteger;
import java.util.HashMap;

public interface DesenhadorGrafo {

  public void desenhaAresta(HashMap<BigInteger, BigInteger> relacoes,
      VetorComposto vets);

}
