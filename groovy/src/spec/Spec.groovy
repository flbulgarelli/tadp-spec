package spec

import org.junit.Assert;

class Spec {
  static {
    Object.metaClass {
      deberia = {
        verbo -> new Condicion(valor: delegate)
      }
      y = {
        verbo -> delegate.deberia(verbo)
      }
    }
  }
  
  static ser = null
  
  static especificacion(bloque) {
    def builder = new EspecificacionBuilder()
    builder.with(bloque)
    builder.evaluar()
  }
}


class Condicion {
  def valor
  
  def menorQue(otroValor) {
    validar("${valor} deberia ser menor que ${otroValor}", valor < otroValor)
  }
  
  def mayorQue(otroValor) {
    validar("${valor} deberia ser mayor que ${otroValor}", valor > otroValor)
  }
  
  
  def igual(otroValor) {
    validar("${valor} deberia ser igual a ${otroValor}", valor == otroValor)
  }
  
  def validar(mensaje, condicion) {
    Assert.assertTrue(
      mensaje,
      condicion)
    valor
  }
  
}

class EspecificacionBuilder {
  def valores
  def bloque
  
  def siendo(bloque) {
    this.valores = bloque()
  }
  
  def esperando(bloque) {
    this.bloque = bloque
  }
  def evaluar() { 
    valores.each { it.with(bloque)  }
  }
}