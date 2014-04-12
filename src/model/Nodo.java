package model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import processing.core.PApplet;

public class Nodo {

  private BigInteger identificador;
  private Set<BigInteger> nodos;
  PApplet processing;

  public Nodo(PApplet processing, BigInteger identificador,
      List<BigInteger> nodos) {

    this.processing = processing;
    this.identificador = identificador;
    this.nodos = new HashSet<BigInteger>(nodos);

  }

  public Set<BigInteger> getNodos() {
    return nodos;
  }

  public void setNos(Set<BigInteger> nodos) {
    this.nodos = nodos;
  }

  public BigInteger getidentificador() {
    return identificador;
  }

  public void setidentificador(BigInteger identificador) {
    this.identificador = identificador;
  }

}
