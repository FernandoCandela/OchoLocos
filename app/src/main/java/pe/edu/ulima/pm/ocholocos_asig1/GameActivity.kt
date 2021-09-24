package pe.edu.ulima.pm.ocholocos_asig1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.ocholocos_asig1.Clases.Baraja
import pe.edu.ulima.pm.ocholocos_asig1.Clases.Carta
import pe.edu.ulima.pm.ocholocos_asig1.Clases.Efecto
import pe.edu.ulima.pm.ocholocos_asig1.Clases.Jugador

class GameActivity : AppCompatActivity() {
    var ronda: Int = 1;
    var turno: Int = 0;
    lateinit var baraja: Baraja;
    var jugadores: MutableList<Jugador> = mutableListOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        inicioJuego();

        findViewById<Baraja>(R.id.idBaraja).setOnClickListener { v: View ->
            robarCarta();
            setManoJugadores();
            setManoOnClick();
        }
        findViewById<Button>(R.id.butPasar).setOnClickListener { v: View ->
            pasarJugador();
            setManoJugadores();
            setManoOnClick();
        }
        findViewById<Button>(R.id.butCancelar).setOnClickListener { v: View ->
            setResult(RESULT_CANCELED)
            finish()
        }
        findViewById<Button>(R.id.butPasar).setEnabled(false);
    }

    fun inicioJuego() {
        baraja = findViewById(R.id.idBaraja);

        var jugador1 = findViewById<Jugador>(R.id.Jugador1);
        jugador1.nombre = "JUGADOR 1";
        var jugador2 = findViewById<Jugador>(R.id.Jugador2);
        jugador2.nombre = "JUGADOR 2";
        var jugador3 = findViewById<Jugador>(R.id.Jugador3);
        jugador3.nombre = "JUGADOR 3";
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);
        repartirCartas();
        setManoJugadores();
        SetCartaCentral();

        if (baraja.cartaCentral.isEspecial()) {
            efectoCarta(true);
        }
        setManoOnClick();
    }

    fun SetCartaCentral() {
        var linearC = findViewById<LinearLayout>(R.id.linearCartaCentro);
        baraja.cartaCentral.setSize(500, 500)
        linearC.removeAllViews();
        linearC.addView(baraja.cartaCentral);
    }

    fun setManoOnClick() {
        for (jugador in jugadores) {
            for (carta in jugador.cartas) {
                setOnclickCarta(carta);
            }
        }
    }

    fun setOnclickCarta(carta: Carta) {
        carta.setOnClickListener { v: View ->
            carta.isSelect = true;
            cartasCompatibles();
        }
    }

    fun setManoJugadores() {
        for (jugador in jugadores) {
            jugador.removeAllViews();
            for (carta in jugador.cartas) {
                jugador.addView(carta);
            }
        }
    }

    fun repartirCartas() {
        for (jugador in jugadores) {
            baraja.getListofCartas(8)?.let { jugador.addCartas(it) };
        }
    }

    fun jugadorActual(): Jugador {
        return jugadores[turno];
    }

    fun getCartasJugadorActual(): MutableList<Carta> {
        return jugadorActual().cartas;
    }

    fun numCartasJugadorActual(): Int {
        return jugadorActual().cartas.size;
    }

    fun siguienteJugador(): Jugador {
        return if (turno == jugadores.size - 1) {
            jugadores[0];
        } else {
            jugadores[turno + 1];
        }
    }

    fun cargarCartasJugador() {
        for (jugador in jugadores) {
            jugador.visibility = View.GONE;
        }
        jugadorActual().visibility = View.VISIBLE;
        findViewById<TextView>(R.id.txtJugador).text = jugadorActual().nombre;

    }

    fun pasarJugador() {
        cambioTurno();
        cargarCartasJugador();
        findViewById<Button>(R.id.butPasar).setEnabled(false);
    }

    fun cambioTurno() {
        if (turno == jugadores.size - 1) {
            turno = 0;
        } else {
            turno++;
        }
    }

    fun efectoCarta(inicio: Boolean) {
        var cartaC: Carta = baraja.cartaCentral;
        var sigJugador: Jugador;
        var cartas: MutableList<Carta>;
        if (cartaC.efecto === Efecto.MASTRES) {
            sigJugador = if (inicio) jugadorActual() else siguienteJugador();
            cartas = baraja.getListofCartas(3)!!;
            sigJugador.addCartas(cartas);
        } else {
            //Efecto.SALTO
            cambioTurno();
        }
    }

    fun cartasCompatibles() {
        if (iscartasCompatibles()) {
            setManoJugadores();
            SetCartaCentral();
            pasarJugador();
        } else {
            Toast.makeText(this, "Cartas no compatibles", Toast.LENGTH_LONG).show()
        }
    }

    fun iscartasCompatibles(): Boolean {
        var cartaJ: Carta = jugadorActual().getCartaSelect()!!;
        var cartaC: Carta = baraja.cartaCentral;
        return if (cartaJ.compatible(cartaC)) {
            if (jugadorActual().cartas.size - 1 === 1) {
                Toast.makeText(
                    this,
                    "El jugador: " + jugadorActual().nombre + " tiene una carta",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (jugadorActual().cartas.size - 1 == 0) {
                    jugadorActual().gano = true;
                    Toast.makeText(
                        this,
                        "El ganador es:  " + jugadorActual().nombre,
                        Toast.LENGTH_LONG
                    ).show()
                    setResult(RESULT_OK)
                    finish()
                }
            }
            jugadorActual().removeCarta();
            cartaJ.isSelect = false;
            baraja.cartaCentral = cartaJ;
            baraja.addCarta(cartaC);

            if (cartaJ.isEspecial()) {
                //carta especial
                efectoCarta(false);
            }
            true;
        } else {
            cartaJ.isSelect = false;
            false;
        }
    }

    fun robarCarta() {
        var carta: Carta = baraja.getFirstCarta();
        jugadorActual().addCarta(carta);
        findViewById<Button>(R.id.butPasar).setEnabled(true);
    }

}