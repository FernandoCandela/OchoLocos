package pe.edu.ulima.pm.ocholocos_asig1.model

class Jugador (var nombre: String,var gano: Boolean , var cartas : MutableList<Carta>) {

    fun getCartaByIndex(index: Int): Carta {
        var c: Carta = cartas[index];
        return c;
    }
    fun removeCartaByIndex(index: Int) {
        cartas.removeAt(index);
    }
    fun addCarta(carta:Carta){
        this.cartas.add(carta);
    }

    fun addCartas(cartas :MutableList<Carta>){
        this.cartas.let { list1 -> cartas.let(list1::addAll) }
    }


}