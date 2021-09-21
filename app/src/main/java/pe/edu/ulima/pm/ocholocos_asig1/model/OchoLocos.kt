package pe.edu.ulima.pm.ocholocos_asig1.model

class OchoLocos(var jugadores: MutableList<Jugador>) {
    var ronda: Int = 1;
    var turno: Int = 0;
    var baraja: Baraja = Baraja();

    fun repartirCartas() {
        this.baraja = Baraja();
        for (jugador in this.jugadores){
            this.baraja.getListofCartas(8)?.let { jugador.addCartas(it) };
        }

    }
    fun jugadorActual(): Jugador {
        return this.jugadores[this.turno];
    }
    fun getCartasJugadorActual(): MutableList<Carta> {
        return this.jugadorActual().cartas;
    }
    fun numCartasJugadorActual () : Int{
        return this.jugadorActual().cartas.size;
    }

    fun cambioTurno(){
        if (turno == this.jugadores.size-1){
            this.turno = 0;
        }else {
            this.turno++;
        }
    }
    fun cartasCompatibles(){
        //Carta
    }



}