package strategy;

/** Estratégia concreta: entrega rápida, com taxa de urgência e valores mais altos. */
public class FreteExpresso implements CalculoFrete {

    private static final double TAXA_URGENCIA = 15.00;

    @Override
    public String getNome() {
        return "Expresso";
    }

    @Override
    public double calcular(double pesoKg, double distanciaKm) {
        return TAXA_URGENCIA + 12.00 + (pesoKg * 3.00) + (distanciaKm * 0.08);
    }
}