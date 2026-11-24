package factory;

class Caminhao implements Transporte {

    @Override
    public String entregar(String destino) {
        return "Caminhão seguindo pela estrada até " + destino + ".";
    }

    @Override
    public int getCapacidadeKg() {
        return 12_000;
    }
}