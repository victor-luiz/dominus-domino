package controllers

import models.Player
import models.Tile

class Game (playersName: List<String>) {
    val allTiles = mutableListOf<Tile>()
    val remnant = mutableListOf<Tile>()
    val players = mutableListOf<Player>()
    val game = ArrayDeque<Tile>()

    init {
        for (name in playersName) {
            players.add(Player(name))
        }
    }

    fun start() {
        println("--- Iniciando o Jogo de Dominó ---")
        createTiles()
        shuffleAndDealTiles()
        for (player in players) {
            println(player.toString())
        }
        println("Pedras restantes ${remnant.size}")
    }

    private fun createTiles() {
        allTiles.clear()
        for (i in 0..6) {
            for (j in i..6) {
                allTiles.add(Tile(i, j));
            }
        }
        println("Criadas ${allTiles.size} peças.")
    }

    private fun shuffleAndDealTiles() {
        allTiles.shuffle()
        remnant.addAll(allTiles)
        for (player in players) {
            for (i in 1..7) {
                player.addTile(remnant.removeFirst());
            }
        }
    }
}