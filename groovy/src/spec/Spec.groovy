package spec;
import org.junit.Assert
class Spec {
  static {
    ExpandoMetaClass.enableGlobally()
    Object.metaClass {
      deberia = { condicion -> 
        condicion(delegate) 
      }
      y = { delegate.deberia(it) } 
    }
  }
  
  static ser = {it -> new CondicionPorValor(valor: it) }
  static tener = ser //:P
  static explotar =  {it ->
    try {
      it()
      Assert.fail("Deberia explotar")
    } catch(e) {
      new CondicionPorExcepcion(excepcion: e)
    }
  }
  
}
class CondicionPorExcepcion {
  def excepcion
  def con(claseDeExcepcion) {
    Assert.assertEquals("${excepcion} deberia ser de clase ${claseDeExcepcion}", claseDeExcepcion, excepcion.class)
    excepcion
  }
}

class CondicionPorValor {
  def valor
  
  def mensaje(x) {
    validar "tener mensaje ${x}", { x == it.message }
  }
  
  def igualA(x) {
    validar "ser igual a ${x}", { x == it }
  }
  
  def mayorQue(x) {
    validar "ser mayor que ${x}", { it > x }
  }
  
  def menorQue(x) {
    validar "ser menor que ${x}", { it < x }
  }
  
  def unoDe(...elementos) {
    validar "ser uno de ${elementos}", {it in elementos}
  }
  
  def validar(mensaje, condicion) {
    Assert.assertTrue("${valor} deberia ${mensaje}", condicion(valor))
    valor
  }
  
}