package model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertice {

  private final BigInteger identificador;
  private final Map<BigInteger, List<BigInteger>> mapeamentoNodular;

  public Vertice(BigInteger identificador, List<BigInteger> mapeamentoNodular) {

    this.identificador = identificador;

    this.mapeamentoNodular = new HashMap<BigInteger, List<BigInteger>>();

    this.mapeamentoNodular.put(identificador, mapeamentoNodular);

  }

  public BigInteger getIdentificador() {
    return identificador;
  }

}
