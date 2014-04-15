package model;

import java.math.BigInteger;

public class Info {

  private String uname, sex, origem;
  private BigInteger uid;
  private int agerank;

  public Info(BigInteger uid, String uname, String sex, String origem,
      int agerank) {
    this.uname = uname;
    this.sex = sex;
    this.origem = origem;
    this.agerank = agerank;
    this.uid = uid;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(uname);
    builder.append("\n");
    builder.append(sex);
    builder.append("\n");
    builder.append(origem);
    builder.append("\n");
    builder.append(uid);
    builder.append("\n");
    builder.append(agerank);
    return builder.toString();
  }

  public BigInteger getUid() {
    return uid;
  }

}
