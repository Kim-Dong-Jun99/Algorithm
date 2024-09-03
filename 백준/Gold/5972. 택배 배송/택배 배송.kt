package org
import kotlin.math.*
import java.util.*
var br = System.`in`.bufferedReader()

fun main() {
    initialize()
}

fun getInputArr(): IntArray {
    return br.readLine().split(" ").map { it.toInt() }.toIntArray()
}

fun initialize() {
    val farmSize = getInputArr()
    val N: Int = farmSize[0]
    val M: Int = farmSize[1]

    val dijkstra = IntArray(N+1){50_000_000}
    val roadMap: Array<MutableMap<Int, Int>> = Array(N+1) {mutableMapOf()}
    for (i in 1..M) {
        val road = getInputArr()
        roadMap[road[0]][road[1]] = min(roadMap[road[0]][road[1]] ?: 50_000_000, road[2])
        roadMap[road[1]][road[0]] = min(roadMap[road[1]][road[0]] ?: 50_000_000, road[2])
    }
    solve(N, dijkstra, roadMap)
}

fun solve(N: Int, dijkstra: IntArray, roadMap: Array<MutableMap<Int, Int>>) {
    val heap:PriorityQueue<Node> = PriorityQueue<Node>(Node::compare)
    heap.add(Node(1, 0))
    while (!heap.isEmpty()) {
        val node = heap.remove()
        if (dijkstra[node.pos] <= node.value) {
            continue
        }
        dijkstra[node.pos] = node.value
        for ((k,v)  in roadMap[node.pos]) if (dijkstra[k] > node.value + v) {
            heap.add(Node(k, v + node.value))
        }
    }
    println(dijkstra[N])
}

class Node(val pos: Int, val value: Int) {
    fun compare(c: Node):Int {
        return this.value.compareTo(c.value)
    }
}