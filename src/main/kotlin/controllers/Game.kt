package controllers

import models.Player
import models.Tile

class Game (playersName: List<String>) {
    private val allTiles = mutableListOf<Tile>()
    private val remnant = mutableListOf<Tile>()
    private val players = mutableListOf<Player>()
    private val game = ArrayDeque<Tile>()

    init {
        playersName.forEach { players.add(Player(it)) }
    }

    fun start() {
        headGame()
        var indexCurrentPlayer = players.indexOf(initalGame())
        indexCurrentPlayer = nextPlayer(indexCurrentPlayer)
        println(game.toString())
        println("----------------------\n")
        println("Jogador atual: ${players[indexCurrentPlayer].name}")
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

    private fun headGame() {
        println("--- Iniciando Dominó ---")
        createTiles()
        shuffleAndDealTiles()
        println("\n--- Mãos Iniciais ---")
        players.forEach { println(it) }
        println("----------------------\n")
    }

    private fun initalGame(): Player? {
        val initialPlayer = findPlayerInitial()
        if (initialPlayer == null) {
            println("ERRO: Não foi possível determinar o jogador inicial.")
            return null
        }

        val (playerInitial, tileInitial) = initialPlayer
        playerInitial.throwTile(tileInitial)
        game.addFirst(tileInitial)

        return playerInitial;
    }

    private fun findPlayerInitial(): Pair<Player, Tile>? {
        for (numberDoble in 6 downTo 0) {
            val tileDoble = Tile(numberDoble, numberDoble);
            for (player in players) {
                if (player.hand.contains(tileDoble)) {
                    println("Jogador inicial encontrado: ${player.name} com a peça $tileDoble")
                    return Pair(player, tileDoble);
                }
            }
        }
        return null
    }

    private fun nextPlayer(index: Int): Int {
        return (index + 1) % players.size
    }
}