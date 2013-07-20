require 'ostruct'

class Comparacion
  def initialize(valor)
    @valor = valor
  end

  def evaluar(otro)
    raise "#{otro} deberia ser #{mensaje_to_s} #{@valor}" unless otro.send mensaje, @valor
  end

  def mensaje_to_s
    mensaje.to_s
  end
end

class MenorQue < Comparacion
  def mensaje
    :<
  end
end

class IgualA < Comparacion
  def mensaje
    :==
  end
end

class EspecificacionBuilder
  def siendo(&bloque)
    @valores = bloque.call
  end

  def esperando(&bloque)
    @bloque = bloque
  end

  def evaluar
    @valores.
        map { |it| OpenStruct.new(it) }.
        each { |it| it.instance_eval(&@bloque) }
  end
end

class Object
  def especificacion(&bloque)
    builder = EspecificacionBuilder.new
    builder.instance_eval(&bloque)
    builder.evaluar
  end

  def menor_que(otro)
    MenorQue.new(otro)
  end

  def igual_a(otro)
    IgualA.new(otro)
  end

  def deberia(asercion)
    asercion.evaluar(self)
  end

  def ser(condicion)
    condicion
  end
end