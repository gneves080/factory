package strategy;

/**
 * STRATEGY: contrato comum a todos os algoritmos de cálculo de frete.
 */
public interface CalculoFrete {

    /** Nome da modalidade, para exibição. */
    String getNome();

    /** Valor do frete, em reais, para o peso e a distância informados. */
    double calcular(double pesoKg, double distanciaKm);
}