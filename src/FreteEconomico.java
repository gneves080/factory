package strategy;

/** Estratégia concreta: barata, cobra pouco por peso e por distância. */
public class FreteEconomico implements CalculoFrete {

    @Override
    public String getNome() {
        return "Econômico";
    }

    @Override
    public double calcular(double pesoKg, double distanciaKm) {
        return 8.00 + (pesoKg * 1.50) + (distanciaKm * 0.02);
    }
}