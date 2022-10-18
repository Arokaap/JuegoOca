package modelo

import java.io.Serializable

class Jugador(id: Byte, nick: String) {
    private var id: Byte
    private var nick: String
    private var posicion: Byte = 0

    init {
        this.id = id
        this.nick = nick
    }

    fun getId(): Byte {
        return this.id
    }

    fun getNick(): String {
        return this.nick
    }

    fun setId(value: Byte) {
        this.id = value
    }

    fun getPosicion(): Byte {
        return this.posicion
    }

    fun setPosicion(value: Byte) {
        this.posicion = value
    }

}