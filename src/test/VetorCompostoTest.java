package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import processing.core.PVector;

public class VetorCompostoTest {

  PVector local, dir, acel, testVector;
  String localLabel, dirLabel, acelLabel;
  Map<String, PVector> vectors;

  @Before
  public void setUp() throws Exception {

    vectors = new HashMap<String, PVector>();

    local = PVector.random2D();
    dir = PVector.random2D();
    acel = PVector.random2D();

    localLabel = "localLabel";
    dirLabel = "dirLabel";
    acelLabel = "acelLabel";

    vectors.put("localLabel", local);
    vectors.put("dirLabel", dir);
    vectors.put("acelLabel", acel);

    testVector = vectors.get(localLabel);

    vectors.remove(dirLabel);

  }

  @After
  public void tearDown() throws Exception {
    vectors.clear();
  }

  @Test
  public void testQuandoForAdicionadoNovoVetor() {
    assertEquals(testVector, vectors.get(localLabel));
  }

  @Test
  public void testQuandoForRemovidoPeloLabel() {
    assertEquals(vectors.get(dirLabel), null);
  }

}
