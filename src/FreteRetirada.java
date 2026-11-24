package strategy;

/** Estratégia concreta: o cliente retira na loja, então não há frete. */
public class FreteRetirada implements CalculoFrete {

    @Override
    public String getNome() {
        return "Retirada na loja";
    }

    @Override
    public double calcular(double pesoKg, double distanciaKm) {
        return 0.0;
    }
}