package builder;

import java.util.Locale;

/**
 * PRODUTO do padrão Builder: objeto imutável e "complexo" (vários campos, alguns opcionais).
 * O construtor é privado: a única forma de criar uma Encomenda é pelo Encomenda.Builder.
 */
public final class Encomenda {

    private final String destinatario;
    private final String endereco;
    private final double pesoKg;
    private final boolean fragil;
    private final boolean comSeguro;
    private final String observacao;

    private Encomenda(Builder b) {
        this.destinatario = b.destinatario;
        this.endereco = b.endereco;
        this.pesoKg = b.pesoKg;
        this.fragil = b.fragil;
        this.comSeguro = b.comSeguro;
        this.observacao = b.observacao;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getEndereco() {
        return endereco;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public boolean isFragil() {
        return fragil;
    }

    public boolean isComSeguro() {
        return comSeguro;
    }

    public String getObservacao() {
        return observacao;
    }

    @Override
    public String toString() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "Encomenda{destinatário='%s', endereço='%s', peso=%.1f kg, frágil=%s, seguro=%s, obs='%s'}",
                destinatario, endereco, pesoKg, fragil ? "sim" : "não", comSeguro ? "sim" : "não", observacao);
    }

    /** BUILDER: monta a Encomenda passo a passo, de forma fluente, e valida tudo em build(). */
    public static class Builder {

        // obrigatórios
        private final String destinatario;
        private final String endereco;
        // opcionais, com valores padrão
        private double pesoKg = 1.0;
        private boolean fragil = false;
        private boolean comSeguro = false;
        private String observacao = "";

        /** Os campos obrigatórios entram no construtor do Builder, então não dá para esquecê-los. */
        public Builder(String destinatario, String endereco) {
            this.destinatario = destinatario;
            this.endereco = endereco;
        }

        public Builder pesoKg(double pesoKg) {
            this.pesoKg = pesoKg;
            return this;
        }

        public Builder fragil(boolean fragil) {
            this.fragil = fragil;
            return this;
        }

        public Builder comSeguro(boolean comSeguro) {
            this.comSeguro = comSeguro;
            return this;
        }

        public Builder observacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        /** Valida as regras e só então entrega o objeto pronto (nunca existe uma Encomenda inválida). */
        public Encomenda build() {
            if (destinatario == null || destinatario.trim().isEmpty()) {
                throw new IllegalStateException("O destinatário é obrigatório.");
            }
            if (endereco == null || endereco.trim().isEmpty()) {
                throw new IllegalStateException("O endereço é obrigatório.");
            }
            if (pesoKg <= 0) {
                throw new IllegalStateException("O peso deve ser maior que zero.");
            }
            if (pesoKg > 30) {
                throw new IllegalStateException("O peso máximo por encomenda é 30 kg.");
            }
            return new Encomenda(this);
        }
    }
}