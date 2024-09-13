import java.util.*

val BR = System.`in`.bufferedReader()

val DX = intArrayOf(0, -1, -1, -1, 0, 1, 1, 1)
val DY = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
val sharkMovePriority = intArrayOf(2, 0, 6, 4)
val smell = Array(4) { IntArray(4) {0}}
val fishCount = Array(4) { IntArray(4) {0}}

var inputArr = intArrayOf()
var M = 0
var S = 0
var fishes = mutableListOf<Fish>()
var copyFishes = mutableListOf<Fish>()
var shark = Position(0, 0)

fun main() {
    initialize()
    solve()
}

fun getInputArray(): IntArray {
    return BR.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun initialize() {
    inputArr = getInputArray()
    M = inputArr[0]
    S = inputArr[1]
    
    for (i in 1..M) {
        inputArr = getInputArray()
        fishes.add(Fish(inputArr[0]-1, inputArr[1]-1, inputArr[2]-1))
        fishCount[inputArr[0]-1][inputArr[1]-1] += 1
    }
    
    inputArr = getInputArray()
    shark = Position(inputArr[0]-1, inputArr[1]-1)
    
}

fun solve() {
    for (tc in 1..S) {
        initCopyFish()
        moveFishes(tc)
        moveShark(tc)
        addCopyFish()
    }
    
    println(fishes.size)
}

fun initCopyFish() {
    copyFishes = mutableListOf<Fish>()
    for (fish in fishes) {
        copyFishes.add(Fish(fish.x, fish.y, fish.d))
    }
}

fun moveFishes(tc: Int) {
    for (fish in fishes) {
        moveFish(fish, tc)
    }
}

fun moveFish(fish: Fish, tc:Int) {
    var curD = fish.d
    for (d in 0 until 8) {
        val nx = fish.x + DX[curD]
        val ny = fish.y + DY[curD]
        if (isInner(nx, ny) && !(shark.x == nx && shark.y == ny) && tc > smell[nx][ny]) {
            fishCount[fish.x][fish.y] -= 1
            fish.x = nx
            fish.y = ny
            fish.d = curD
            fishCount[nx][ny] += 1
            return
        }
        curD = (curD + 7) % 8
    }
}

fun isInner(x: Int, y: Int): Boolean {
    return x in 0 until 4 && y in 0 until 4
}

fun moveShark(tc: Int) {
    val heap = PriorityQueue<SharkMove>(SharkMove::compare)
    dfs(0, IntArray(3) {0}, heap, shark.x, shark.y, 0)
    val sharkMove = heap.remove()
    while (!heap.isEmpty()) {
        val sm = heap.remove()
    }
    removeFishes(sharkMove.first, sharkMove.second, sharkMove.third, tc)
}

fun removeFishes(first: Int, second: Int, third: Int, tc: Int) {
    val nextFishes = mutableListOf<Fish>()
    val firstX = shark.x+DX[sharkMovePriority[first]]
    val firstY = shark.y+DY[sharkMovePriority[first]]
    val secondX = firstX+DX[sharkMovePriority[second]]
    val secondY = firstY+DY[sharkMovePriority[second]]
    val thirdX = secondX+DX[sharkMovePriority[third]]
    val thirdY = secondY+DY[sharkMovePriority[third]]

    for (fish in fishes) {
        if (isRemoved(fish, firstX, firstY, secondX, secondY, thirdX, thirdY)) {
            smell[fish.x][fish.y] = tc+2
            fishCount[fish.x][fish.y] -= 1
        } else {
            nextFishes.add(fish)
        }
    }

    shark.x = thirdX
    shark.y = thirdY
    fishes = nextFishes
}

fun isRemoved(fish: Fish, firstX: Int, firstY: Int, secondX: Int, secondY: Int, thirdX: Int, thirdY: Int): Boolean {
    return (fish.x == firstX && fish.y == firstY) || (fish.x == secondX && fish.y == secondY) || (fish.x == thirdX && fish.y == thirdY)
}

fun dfs(index: Int, moves: IntArray, heap: PriorityQueue<SharkMove>, x: Int, y: Int, catched: Int) {
    if (index == 3) {
        heap.add(SharkMove(moves[0], moves[1], moves[2], catched))
        return
    }
    for (i in 0 until 4) {
        val sd = sharkMovePriority[i]
        val nx = x + DX[sd]
        val ny = y + DY[sd]

        if (isInner(nx, ny)) {
            moves[index] = i
            val fc = fishCount[nx][ny]
            fishCount[nx][ny] = 0
            dfs(index + 1, moves, heap, nx, ny, catched + fc)
            fishCount[nx][ny] = fc
        }
    }
}

fun addCopyFish() {
    for (fish in copyFishes) {
        fishes.add(fish)
        fishCount[fish.x][fish.y] += 1
    }
}

class SharkMove(val first: Int, val second: Int, val third: Int, val catched: Int) {
    fun compare(s: SharkMove): Int {
        if (this.catched != s.catched) {
            return s.catched.compareTo(this.catched)
        }
        if (this.first != s.first) {
            return this.first.compareTo(s.first)
        }
        if (this.second != s.second) {
            return this.second.compareTo(s.second)
        }
        return this.third.compareTo(s.third)
    }
}

class Fish(var x: Int, var y: Int, var d: Int) {}

class Position(var x: Int, var y: Int) {}
