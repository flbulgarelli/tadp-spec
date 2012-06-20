package spec
import static Spec.*

especificacion("foo") {
  5.deberia ser menorQue 10 y ser mayorQue 2
}

especificacion("bar") {
  5.deberia ser igualA 7
}

especificacion("baz") {
  esperar {
    (x + y).deberia ser igualA 10
  }
  siendo {
    x | y | z
   '---------'
    4 | 5 | 9
    0 | 0 | 0
  }
}