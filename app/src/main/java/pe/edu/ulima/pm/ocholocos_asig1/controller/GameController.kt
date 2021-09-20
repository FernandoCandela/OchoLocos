package pe.edu.ulima.pm.ocholocos_asig1.controller

import pe.edu.ulima.pm.ocholocos_asig1.model.Baraja

class GameController {









}
fun main(args:Array<String>){

    var baraja = Baraja();
    baraja.crearBaraja();
    baraja.barajar();
    baraja.getFirstCarta();
    baraja.getListofCartas(8);

}