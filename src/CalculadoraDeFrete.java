package strategy;

/**
 * CONTEXTO do Strategy: usa uma estratégia, sem saber qual é a classe concreta,
 * e permite trocá-la em tempo de execução.
 */
public class CalculadoraDeFrete {

    private CalculoFrete estrategia;

    public CalculadoraDeFrete(CalculoFrete estrategiaInicial) {
        setEstrategia(estrategiaInicial);
    }

    public void setEstrategia(CalculoFrete estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException("A estratégia de frete não pode ser nula.");
        }
        this.estrategia = estrategia;
    }

    public String getNomeEstrategia() {
        return estrategia.getNome();
    }

    public double calcular(double pesoKg, double distanciaKm) {
        return estrategia.calcular(pesoKg, distanciaKm);
    }
}