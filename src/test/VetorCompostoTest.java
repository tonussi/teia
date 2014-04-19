package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import processing.core.PVector;

public class VetorCompostoTest {

  PVector local, dir, acel, test, empty;
  Map<String, PVector> vectors;

  @Before
  public void setUp() throws Exception {

    vectors = new HashMap<String, PVector>();
    local = new PVector(1.4e-3F, 3e-3F);
    dir = new PVector(1e-3F, 6e-3F);
    acel = new PVector(1.2e-3F, 1.2e-3F);
    test = new PVector(1.4e-3F + 1e-3F, 3e-3F + 6e-3F);
    empty = new PVector();

    vectors.put("local", local);
    vectors.put("acel", acel);
    vectors.put("dir", dir);

  }

  @Test
  public void testQuandoForAdicionadoNovoVetor() {
    vectors.put("local", local);
    assertEquals(local, vectors.get("local"));
  }

  @Test
  public void testQuandoForRemovidoPeloLabel() {
    vectors.remove("dir");
    assertEquals(vectors.get("dir"), null);
  }

  @Test
  public void testQuandoForTrabalhadoPeloNome() {
    vectors.get("local").add(vectors.get("dir"));
    assertEquals(test, vectors.get("local"));
  }
}
