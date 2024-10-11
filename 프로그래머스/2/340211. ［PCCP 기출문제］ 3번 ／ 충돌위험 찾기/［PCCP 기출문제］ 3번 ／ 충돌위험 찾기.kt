import java.util.*

class Solution {
    val DX = intArrayOf(1, -1, 0, 0)
    val DY = intArrayOf(0, 0, -1, 1)
    
    val board = Array(101) { IntArray(101) {0}}
    var answer = 0
    var points = arrayOf<Position>()
    var routes = arrayOf<IntArray>()
    var robots = arrayOf<Robot>()
    var n = 0
    var x = 0
    var m = 0

    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
        initialize(points, routes)
        solve()
        return answer
    }

    fun initialize(points: Array<IntArray>, routes: Array<IntArray>) {
        this.n = points.size
        this.x = routes.size
        this.m = routes[0].size
        this.points = Array(n) {Position(points[it][0], points[it][1])}
        this.routes = routes.map { arr -> arr.map { it-1 }.toIntArray() }.toTypedArray()
        this.robots = Array(x) {index -> 
            board[points[routes[index][0]-1][0]][points[routes[index][0]-1][1]] += 1
            if (board[points[routes[index][0]-1][0]][points[routes[index][0]-1][1]] == 2) {
                answer += 1
            }
            Robot(index, points[routes[index][0]-1][0], points[routes[index][0]-1][1], 1, false)
        }
    }

    fun solve() {
        while (!isDone()) {
            for (robot in robots) {
                if (robot.next == m) {
                    continue
                }
                updateBoard(robot, -1)
            }
            for (robot in robots) {
                if (robot.next == m) {
                    continue
                }
                val d = getRobotDirection(robot)
                robot.x += DX[d]
                robot.y += DY[d]
                if (board[robot.x][robot.y] == 1) {
                    answer += 1
                }
                if (robot.x == points[routes[robot.id][robot.next]].x && robot.y == points[routes[robot.id][robot.next]].y) {
                    robot.next += 1
                }
                updateBoard(robot, 1)
            }
            removeRobots()
            
        }
    }
    
    fun printBoard() {
        println("board")
        for (i in 1..7) {
            for (j in 1..7) {
                print("${board[i][j]} ")
            }
            println()
        }
    }
    
    fun updateBoard(robot: Robot, v: Int) {
        board[robot.x][robot.y] += v
    }
    
    fun getRobotDirection(robot: Robot): Int {
        var rd = -1
        val px = points[routes[robot.id][robot.next]].x
        val py = points[routes[robot.id][robot.next]].y
        var distance = Int.MAX_VALUE
        for (d in 0 until 4) {
            val nx = robot.x + DX[d]
            val ny = robot.y + DY[d]
            if (distance > calcDis(nx, ny, px, py)) {
                distance = calcDis(nx, ny, px, py)
                rd = d
            }
        }
        return rd
    }
    
    fun calcDis(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2)
    }
    
    fun removeRobots() {
        for (robot in robots) {
            if (robot.next == m && !robot.isOut) {
                robot.isOut = true
                updateBoard(robot, -1)
            }
        }
    }
    
    fun isDone(): Boolean {
        var done = 0
        for (robot in robots) {
            if (robot.isOut) {
                done += 1
            }
        }
        return done == x
    }

    class Position(var x: Int, var y: Int) {}
    
    class Robot(val id: Int, var x: Int, var y: Int, var next: Int, var isOut: Boolean) {}
}