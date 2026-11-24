import builder.Encomenda;
import factory.Transporte;
import factory.TipoTransporte;
import factory.TransporteFactory;
import strategy.CalculadoraDeFrete;
import strategy.FreteEconomico;
import strategy.FreteExpresso;
import strategy.FreteRetirada;

import java.util.Locale;

public class Main {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    public static void main(String[] args) {
        demonstrarBuilder();
        demonstrarStrategy();
        demonstrarFactory();
    }

    // ------------------------------------------------------------------ BUILDER
    private static void demonstrarBuilder() {
        titulo("BUILDER: montando uma Encomenda passo a passo");

        Encomenda completa = new Encomenda.Builder("Maria Silva", "Rua das Flores, 100 - São Paulo")
                .pesoKg(2.5)
                .fragil(true)
                .comSeguro(true)
                .observacao("Entregar no período da tarde")
                .build();
        System.out.println("Completa : " + completa);

        // Só os campos obrigatórios: os opcionais assumem os valores padrão
        Encomenda simples = new Encomenda.Builder("João Souza", "Av. Central, 50 - Recife").build();
        System.out.println("Simples  : " + simples);

        // Validação: o build() recusa objetos inválidos
        try {
            new Encomenda.Builder("Ana", "Rua A, 1").pesoKg(45).build();
        } catch (IllegalStateException e) {
            System.out.println("Inválida : recusada -> " + e.getMessage());
        }
        try {
            new Encomenda.Builder("  ", "Rua B, 2").build();
        } catch (IllegalStateException e) {
            System.out.println("Inválida : recusada -> " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------- STRATEGY
    private static void demonstrarStrategy() {
        titulo("STRATEGY: trocando o cálculo de frete em tempo de execução");

        double peso = 5.0;
        double distancia = 400.0;
        System.out.println(String.format(PT_BR, "Encomenda de %.1f kg a %.0f km:", peso, distancia));

        CalculadoraDeFrete calculadora = new CalculadoraDeFrete(new FreteEconomico());
        imprimirFrete(calculadora, peso, distancia);

        calculadora.setEstrategia(new FreteExpresso());   // troca a estratégia, sem mudar a calculadora
        imprimirFrete(calculadora, peso, distancia);

        calculadora.setEstrategia(new FreteRetirada());
        imprimirFrete(calculadora, peso, distancia);

        try {
            calculadora.setEstrategia(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Estratégia nula recusada -> " + e.getMessage());
        }
    }

    private static void imprimirFrete(CalculadoraDeFrete calculadora, double peso, double distancia) {
        System.out.println(String.format(PT_BR, "  %-16s R$ %7.2f",
                calculadora.getNomeEstrategia(), calculadora.calcular(peso, distancia)));
    }

    // ------------------------------------------------------------------ FACTORY
    private static void demonstrarFactory() {
        titulo("FACTORY: criando transportes sem conhecer as classes concretas");

        for (TipoTransporte tipo : TipoTransporte.values()) {
            Transporte transporte = TransporteFactory.criar(tipo);
            System.out.println(String.format(PT_BR, "%-10s -> %-8s | capacidade %,d kg | %s",
                    tipo,
                    transporte.getClass().getSimpleName(),
                    transporte.getCapacidadeKg(),
                    transporte.entregar("Salvador")));
        }

        try {
            TransporteFactory.criar(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo nulo recusado -> " + e.getMessage());
        }
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("==== " + texto + " ====");
    }
}