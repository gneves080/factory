package factory;

/**
 * FACTORY: decide qual classe concreta instanciar.
 * As classes Caminhao, Navio e Aviao são visíveis só dentro do pacote: quem usa a fábrica
 * (fora dele) trabalha apenas com Transporte e TipoTransporte e nem consegue dar "new Caminhao()".
 */
public final class TransporteFactory {

    private TransporteFactory() {
    }

    public static Transporte criar(TipoTransporte tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo de transporte é obrigatório.");
        }
        switch (tipo) {
            case RODOVIARIO:
                return new Caminhao();
            case MARITIMO:
                return new Navio();
            case AEREO:
                return new Aviao();
            default:
                throw new IllegalArgumentException("Tipo de transporte não suportado: " + tipo);
        }
    }
}