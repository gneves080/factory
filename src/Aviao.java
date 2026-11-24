package factory;

class Aviao implements Transporte {

    @Override
    public String entregar(String destino) {
        return "Avião decolando rumo a " + destino + ".";
    }

    @Override
    public int getCapacidadeKg() {
        return 40_000;
    }
}