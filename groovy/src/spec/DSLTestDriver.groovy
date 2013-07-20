package spec;

import static org.junit.Assert.*
import static spec.Spec.*

import org.junit.Test

class DSLTestDriver {
  
  @Test
  void testName() {
     5.deberia ser menorQue 10 y ser mayorQue 4 
  }
  
  @Test
  void testName4() {
     5.deberia ser unoDe 1, 2, 5
  }
  
  @Test
  void testName3() {
     (5 + 6).deberia ser igualA 11 
  }
  
  @Test
  void testName2() {
     {-> 0 / 0 }.deberia explotar con ArithmeticException y tener mensaje 'Division undefined'
  }
}

