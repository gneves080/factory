package factory;

class Navio implements Transporte {

    @Override
    public String entregar(String destino) {
        return "Navio cruzando o mar até o porto de " + destino + ".";
    }

    @Override
    public int getCapacidadeKg() {
        return 500_000;
    }
}