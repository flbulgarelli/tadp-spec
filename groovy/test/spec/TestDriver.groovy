package spec

import static org.junit.Assert.*
import junit.framework.AssertionFailedError
import static Spec.*

import org.junit.Test

class TestDriver {
  
  @Test
  public void deberiaSerIgualPasaSiLosValoresSonIguales()  {
    5.deberia ser igual 5
  }
  
  @Test(expected  = AssertionError)
  public void deberiaSerIgualFallaSiLosValoresSonDistintos()  {
    5.deberia ser igual 4
  }
  
  @Test
  public void deberiaSerMenorQuePasaSiElReceptorEsMenor()  {
    5.deberia ser menorQue 10
  }
  
  
  @Test
  public void unaEspecificacionEvaluaLasExpectativasParaCadaConjuntoDeDatos()  {
    especificacion {
      esperando { 
        (x+y).deberia ser igual z
      }
      siendo {[
        [x: 4, y: 5, z: 9],
        [x: 1, y: 2, z: 3],
        [x: 1, y: 3, z: 4]
      ]}
    }
  }
  
  @Test
  void sePuedeExpresarElConjuntoDeDatosEnFormaDeTabla()  {
    especificacion {
      esperando {
        (x+y).deberia ser igual z
      }
      siendo {
        x | y | z
       '--+---+---'
        1 | 2 | 3
        4 | 4 | 8
      }
    }
  }
  
}
