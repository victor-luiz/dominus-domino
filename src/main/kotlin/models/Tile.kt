package models

data class Tile(private val oneNumber: Byte, private val twoNumber: Byte) {

    val isDoble = oneNumber == twoNumber;

    override fun toString(): String {
        return "[$oneNumber|$twoNumber]"
    }
}