package controlador

import modelo.Juego
import recursos.Utilidades

fun main(args: Array<String>) {
    var partida = Juego()
    var repetir = true

    do {
        when (Juego.menu()) {
            1.toByte() -> partida.comenzarPartida()
            2.toByte() -> partida.aniadirJugador()
            3.toByte() -> partida.eliminarJugador()
            4.toByte() -> partida.getJugadores()
            5.toByte() -> {
                Utilidades.limpiarPantalla()
                println("Todos los creditos reservados a:")
                Utilidades.textoVerde("Aarón Aragón Aroca")
            }

            6.toByte() -> {
                Utilidades.limpiarPantalla()
                Utilidades.textoVerde("Gracias por jugar!")
                repetir = false
            }

            else -> {
                Utilidades.limpiarPantalla()
                Utilidades.textoError("Opción incorrecta")
            }
        }
    } while (repetir)
}