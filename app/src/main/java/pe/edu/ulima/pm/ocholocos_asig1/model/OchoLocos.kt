package pe.edu.ulima.pm.ocholocos_asig1.model

class OchoLocos {
    var ronda: Int = 1;
    var turno: Int = 0;
    var baraja: Baraja = Baraja();
    var jugadores: MutableList<Jugador> = mutableListOf();

    constructor(jugadores: MutableList<Jugador>){
        this.jugadores = jugadores;
        this.repartirCartas();
        if (this.baraja.cartaCentral.isEspecial()){
            this.efectoCarta(true);
        }
    }

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

    fun numCartasJugadorActual(): Int {
        return this.jugadorActual().cartas.size;
    }

    fun cambioTurno() {
        if (turno == this.jugadores.size - 1) {
            this.turno = 0;
        } else {
            this.turno++;
        }
    }

    fun cartasCompatibles(indexCarta: Int): Boolean {
        var cartaJ: Carta = this.jugadorActual().getCartaByIndex(indexCarta);
        var cartaC: Carta = this.baraja.cartaCentral;
        return if (cartaJ.compatible(cartaC)) {
            if ( this.jugadorActual().cartas.size == 0){
                this.jugadorActual().gano =true;
                //acabar el juego
            }
            this.baraja.cartaCentral = cartaJ;
            this.baraja.addCarta(cartaC);
            this.jugadorActual().removeCartaByIndex(indexCarta);
            if (cartaJ.isEspecial()) {
                //carta especial
                this.efectoCarta(false);
            }
            true;
        } else {
            false;
        }
    }

    fun siguienteJugador(): Jugador {
        return if (this.turno == this.jugadores.size - 1) {
            this.jugadores[0];
        } else {
            this.jugadores[this.turno + 1];
        }
    }

    fun robarCarta() {
        var carta: Carta = this.baraja.getFirstCarta();
        this.jugadorActual().addCarta(carta);
    }

    fun efectoCarta(inicio: Boolean) {
        var cartaC: Carta = this.baraja.cartaCentral;
        var sigJugador: Jugador;
        var cartas: MutableList<Carta>;
        if (cartaC.efecto == Efecto.MASTRES) {
            sigJugador = if(inicio) this.jugadorActual() else this.siguienteJugador();
            cartas = this.baraja.getListofCartas(3)!!;
            sigJugador.addCartas(cartas);
            this.cambioTurno();
        }else{
            //Efecto.SALTO
            this.cambioTurno();

        }

    }


}