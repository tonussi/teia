package model;

import java.math.BigInteger;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class Info {

  private final String uname, sex, origem;
  private final int agerank;
  private final BigInteger uid;
  PApplet processing;
  PFont font;

  public Info(PApplet processing, PFont font, BigInteger uid, String uname,
      String sex, String origem, int agerank) {
    this.uname = uname;
    this.sex = sex;
    this.origem = origem;
    this.agerank = agerank;
    this.uid = uid;
    this.processing = processing;
    this.font = font;
  }

  public void adicionaInformacao(float x, float y) {
    processing.fill(255, 40);
    processing.textAlign(PConstants.LEFT);
    processing.textFont(font);
    processing.text(toString(), x, y + 20);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (uname != null) {
      builder.append(uname);
      builder.append("\n");
    }
    if (sex != null) {
      builder.append(sex);
      builder.append("\n");
    }
    if (origem != null) {
      builder.append(origem);
      builder.append("\n");
    }
    if (uid != null) {
      builder.append(uid);
      builder.append("\n");
    }
    if (agerank < 0) {
      builder.append(agerank);
      builder.append("\n");
    }
    return builder.toString();
  }

}
