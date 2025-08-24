package models

class Player (val name: String) {
    val hand = mutableListOf<Tile>()

    fun addTile(tile: Tile) {
        hand.add(tile)
    }

    fun throwTile(tile: Tile): Tile? {
        if (hand.contains(tile)) {
            hand.remove(tile)
            return tile
        }
        return null
    }

    override fun toString(): String {
        return "$name com ${hand.size} peças: $hand"
    }
}