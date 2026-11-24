package factory;

/**
 * PRODUTO (interface) do Factory: o código cliente só conhece este contrato.
 */
public interface Transporte {

    String entregar(String destino);

    int getCapacidadeKg();
}