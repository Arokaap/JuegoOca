package modelo

import jdk.jshell.execution.Util
import recursos.Utilidades
import java.io.Serializable
import java.lang.StringBuilder

class Juego() {

    private var numJugadores: Byte = 0
    private var jugadores: MutableList<Jugador> = arrayListOf()

    fun aniadirJugador() {
        var mayorId: Byte = 0

        if (jugadores.isNotEmpty()) {
            for (jugador in jugadores) {
                if (jugador.getId() >= mayorId) {
                    mayorId = (jugador.getId() + 1).toByte()
                }
            }
        } else {
            mayorId = 1
        }

        Utilidades.limpiarPantalla()
        val nick = Utilidades.pedirString("\u001B[33;1mIntroduzca el nick del jugador $mayorId")
        Utilidades.limpiarPantalla()

        jugadores.add(Jugador(mayorId, nick))

        Utilidades.textoVerde("Jugador añadido correctamente!")
        this.numJugadores++
    }

    fun getJugadores() {
        Utilidades.limpiarPantalla()
        if (jugadores.isNotEmpty()) {
            for (jugador in jugadores) {
                Utilidades.textoInformativo("Jugador numero ${jugador.getId()}")
                Utilidades.textoVerde("Nick: ${jugador.getNick()}")
                println("")
            }
        } else {
            Utilidades.textoError("No hay jugadores")
        }

    }

    fun eliminarJugador() {
        var eliminado = false
        getJugadores()
        if (jugadores.isNotEmpty()) {
            val id = Utilidades.pedirByte("Indique el numero del jugador que desea eliminar:")

            Utilidades.limpiarPantalla()
            for (jugador in jugadores) {
                if (jugador.getId() == id) {
                    jugadores.remove(jugador)
                    Utilidades.textoVerde("Jugador eliminado correctamente")
                    eliminado = true
                    this.numJugadores--
                    break
                }
            }

            for (jugador in jugadores) {
                if (jugador.getId() > id && eliminado) {
                    jugador.setId((jugador.getId() - 1).toByte())
                }
            }

            if (!eliminado) {
                Utilidades.textoError("No existe el jugador indicado")
            }
        }
    }

    fun comenzarPartida() {
        var repetir = true

        Utilidades.limpiarPantalla()
        if (jugadores.size < 2) {
            Utilidades.textoError("Se necesitan al menos dos jugadores para comenzar!")
        } else {
            do {
                for (jugador in jugadores) {
                    Utilidades.textoInformativo("Turno del jugador ${jugador.getId()} (${jugador.getNick()})")
                    var lanzar = Utilidades.pedirString("Escribe lanzar para tirar tu dado")

                    while (!lanzar.equals("lanzar", true)) {
                        Utilidades.textoError("Escribe lanzar para tirar tu dado!")
                        lanzar = Utilidades.pedirString("")

                    }

                    var dado = ((Math.random() * (6 - 1 + 1)).toInt() + 1).toByte()
                    jugador.setPosicion((jugador.getPosicion() + dado).toByte())

                    Utilidades.limpiarPantalla()
                    Utilidades.textoVerde("Te ha salido el número $dado")

                    if (jugador.getPosicion() > 57) {
                        var diferencia = jugador.getPosicion() - 63
                        if (diferencia > 0) {
                            jugador.setPosicion((63 - diferencia).toByte())
                        }

                        if (jugador.getPosicion() == 59.toByte()) {
                            Utilidades.textoVerde("De oca a oca y tiras por que te toca!")
                            jugador.setPosicion(54)
                            Utilidades.textoInformativo("${jugador.getNick()} está en la posición ${jugador.getPosicion()}")
                            lanzar = Utilidades.pedirString("Escribe lanzar para tirar tu dado")

                            while (!lanzar.equals("lanzar", true)) {
                                Utilidades.textoError("Escribe lanzar para tirar tu dado!")
                                lanzar = Utilidades.pedirString("")

                            }

                            dado = ((Math.random() * (6 - 1 + 1)).toInt() + 1).toByte()
                            jugador.setPosicion((jugador.getPosicion() + dado).toByte())

                            Utilidades.limpiarPantalla()
                            Utilidades.textoVerde("Te ha salido el número $dado")
                        }

                        if (jugador.getPosicion() == 58.toByte()) {
                            Utilidades.textoError("OHHHH!!! Has muerto vuelves a empezar de cero :(")
                            jugador.setPosicion(0)
                            Utilidades.textoInformativo("${jugador.getNick()} está en la posición ${jugador.getPosicion()}")
                            lanzar = Utilidades.pedirString("Escribe lanzar para tirar tu dado")

                            while (!lanzar.equals("lanzar", true)) {
                                Utilidades.textoError("Escribe lanzar para tirar tu dado!")
                                lanzar = Utilidades.pedirString("")

                            }

                            dado = ((Math.random() * (6 - 1 + 1)).toInt() + 1).toByte()
                            jugador.setPosicion((jugador.getPosicion() + dado).toByte())

                            Utilidades.limpiarPantalla()
                            Utilidades.textoVerde("Te ha salido el número $dado")
                        }

                    } else {
                        if (jugador.getPosicion() == 5.toByte() || jugador.getPosicion() == 14.toByte() || jugador.getPosicion() == 23.toByte() ||
                            jugador.getPosicion() == 32.toByte() || jugador.getPosicion() == 41.toByte() || jugador.getPosicion() == 50.toByte()
                        ) {
                            Utilidades.textoVerde("De oca a oca y tiras por que te toca!")
                            jugador.setPosicion((jugador.getPosicion() + 4).toByte())
                            Utilidades.textoInformativo("${jugador.getNick()} está en la posición ${jugador.getPosicion()}")
                            lanzar = Utilidades.pedirString("Escribe lanzar para tirar tu dado")

                            while (!lanzar.equals("lanzar", true)) {
                                Utilidades.textoError("Escribe lanzar para tirar tu dado!")
                                lanzar = Utilidades.pedirString("")

                            }

                            dado = ((Math.random() * (6 - 1 + 1)).toInt() + 1).toByte()
                            jugador.setPosicion((jugador.getPosicion() + dado).toByte())

                            Utilidades.limpiarPantalla()
                            Utilidades.textoVerde("Te ha salido el número $dado")
                        }

                        if (jugador.getPosicion() == 6.toByte()) {
                            Utilidades.textoVerde("De puente a puente y tiras porque te lleva la corriente!")
                            jugador.setPosicion((jugador.getPosicion() + 6).toByte())
                            Utilidades.textoInformativo("${jugador.getNick()} está en la posición ${jugador.getPosicion()}")
                            lanzar = Utilidades.pedirString("Escribe lanzar para tirar tu dado")

                            while (!lanzar.equals("lanzar", true)) {
                                Utilidades.textoError("Escribe lanzar para tirar tu dado!")
                                lanzar = Utilidades.pedirString("")

                            }

                            dado = ((Math.random() * (6 - 1 + 1)).toInt() + 1).toByte()
                            jugador.setPosicion((jugador.getPosicion() + dado).toByte())

                            Utilidades.limpiarPantalla()
                            Utilidades.textoVerde("Te ha salido el número $dado")
                        }

                        if (jugador.getPosicion() == 9.toByte() || jugador.getPosicion() == 18.toByte() ||
                            jugador.getPosicion() == 27.toByte() || jugador.getPosicion() == 36.toByte() || jugador.getPosicion() == 45.toByte() || jugador.getPosicion() == 54.toByte()
                        ) {
                            Utilidades.textoVerde("De oca a oca y tiras por que te toca!")
                            jugador.setPosicion((jugador.getPosicion() + 5).toByte())
                            Utilidades.textoInformativo("${jugador.getNick()} está en la posición ${jugador.getPosicion()}")
                            lanzar = Utilidades.pedirString("Escribe lanzar para tirar tu dado")

                            while (!lanzar.equals("lanzar", true)) {
                                Utilidades.textoError("Escribe lanzar para tirar tu dado!")
                                lanzar = Utilidades.pedirString("")

                            }

                            dado = ((Math.random() * (6 - 1 + 1)).toInt() + 1).toByte()
                            jugador.setPosicion((jugador.getPosicion() + dado).toByte())

                            Utilidades.limpiarPantalla()
                            Utilidades.textoVerde("Te ha salido el número $dado")
                        }

                        if (jugador.getPosicion() == 26.toByte()) {
                            Utilidades.textoVerde("De dado a dado y tiro por que me ha tocado!")
                            jugador.setPosicion(53)
                            Utilidades.textoInformativo("${jugador.getNick()} está en la posición ${jugador.getPosicion()}")
                            lanzar = Utilidades.pedirString("Escribe lanzar para tirar tu dado")

                            while (!lanzar.equals("lanzar", true)) {
                                Utilidades.textoError("Escribe lanzar para tirar tu dado!")
                                lanzar = Utilidades.pedirString("")

                            }

                            dado = ((Math.random() * (6 - 1 + 1)).toInt() + 1).toByte()
                            jugador.setPosicion((jugador.getPosicion() + dado).toByte())

                            Utilidades.limpiarPantalla()
                            Utilidades.textoVerde("Te ha salido el número $dado")
                        }

                        if (jugador.getPosicion() == 53.toByte()) {
                            Utilidades.textoVerde("De dado a dado y tiro por que me ha tocado!")
                            jugador.setPosicion(26)
                            Utilidades.textoInformativo("${jugador.getNick()} está en la posición ${jugador.getPosicion()}")
                            lanzar = Utilidades.pedirString("Escribe lanzar para tirar tu dado")

                            while (!lanzar.equals("lanzar", true)) {
                                Utilidades.textoError("Escribe lanzar para tirar tu dado!")
                                lanzar = Utilidades.pedirString("")

                            }

                            dado = ((Math.random() * (6 - 1 + 1)).toInt() + 1).toByte()
                            jugador.setPosicion((jugador.getPosicion() + dado).toByte())

                            Utilidades.limpiarPantalla()
                            Utilidades.textoVerde("Te ha salido el número $dado")
                        }


                    }
                    Utilidades.textoInformativo("${jugador.getNick()} está en la posición ${jugador.getPosicion()}")
                    println("\n\n\n\n")

                    if (jugador.getPosicion() == 63.toByte()) {
                        repetir = false
                        Utilidades.limpiarPantalla()
                        Utilidades.textoVerde("Enhorabuena has conseguido la victoria ${jugador.getNick()}")
                        for (jugador in jugadores) {
                            jugador.setPosicion(0)
                        }
                        break
                    }
                }
            } while (repetir)
        }
    }

    companion object {
        fun menu(): Byte {
            val sb = StringBuilder("\n\n\u001b[33;1m--------MENU---------")
                .append("\n1. Empezar partida")
                .append("\n2. Añadir jugador")
                .append("\n3. Eliminar jugador")
                .append("\n4. Mostrar jugadores")
                .append("\n5. Creditos")
                .append("\n6. Salir\u001b[0m")

            println(sb)

            return Utilidades.pedirByte("Introduzca una opción:")
        }
    }
}