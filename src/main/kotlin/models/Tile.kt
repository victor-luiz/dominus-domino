package models

data class Tile(private val oneNumber: Int, private val twoNumber: Int) {

    val isDoble = oneNumber == twoNumber;

    override fun toString(): String {
        return "[$oneNumber|$twoNumber]"
    }
}