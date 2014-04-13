package model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PApplet;

public class Vertice {

  private final BigInteger identificador;
  private final Map<BigInteger, List<BigInteger>> mapeamentoNodular;
  PApplet processing;

  public Vertice(PApplet processing, BigInteger identificador,
      List<BigInteger> mapeamentoNodular) {

    this.processing = processing;
    this.identificador = identificador;

    this.mapeamentoNodular = new HashMap<BigInteger, List<BigInteger>>();
    this.mapeamentoNodular.put(identificador, mapeamentoNodular);

  }

}
