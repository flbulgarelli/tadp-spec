require 'rspec'
require_relative '../src/xspec'

describe 'en una prueba' do
  context 'cuanto se evalua una asercion' do
    it 'deberia pasar si se cumple la condicion' do
      5.deberia ser menor_que 10
    end

    it 'deberia fallar si no se cumple' do
      expect { 5.deberia ser menor_que 1 }.to raise_error
    end
  end

  context 'cuando se evalua una especificacion' do
    it 'deberia pasar si pasa para todos los escenarios' do
      especificacion do
        esperando do
          (x + y).deberia ser igual_a z
        end
        siendo do
          [{x: 4, y: 5, z: 9},
           {x: 1, y: 3, z: 4}]
        end
      end
    end
    it 'deberia fallar si falla para alguno de los escenarios' do
      expect {
        especificacion do
          esperando do
            (x + y).deberia ser igual_a z
          end
          siendo do
            [{x: 4, y: 5, z: 9},
             {x: 1, y: 3, z: 40}]
          end
        end
      }.to raise_error
    end


  end
end

