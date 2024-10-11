import java.util.*

class Solution {
    val NEXT = "next"
    val PREV = "prev"
    var answer = ""
    var videoLen = 0
    var pos = 0
    var opStart = 0
    var opEnd = 0
    var commands = arrayOf<String>()
    
    fun solution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {
        initialize(video_len, pos, op_start, op_end, commands)
        solve()
        return answer
    }
    
    fun initialize(videoLen: String, pos: String, opStart: String, opEnd: String, commands: Array<String>) {
        this.videoLen = strToInt(videoLen)
        this.pos = strToInt(pos)
        this.opStart = strToInt(opStart)
        this.opEnd = strToInt(opEnd)
        this.commands = commands
    }
    
    fun strToInt(time: String): Int {
        val temp = time.split(":")
        return temp[0].toInt()*60 + temp[1].toInt()
    }
    
    fun intToStr(time: Int): String {
        var temp = ""
        val m = time/60
        val s = time%60
        if (m.toString().length == 1) {
            temp += "0"
        }
        temp += m
        temp += ":"
        if (s.toString().length == 1) {
            temp += "0"
        }
        temp += s
        return temp
    }
    
    fun inSkip(time: Int): Boolean {
        return time in opStart..opEnd
    }
    
    fun solve() {
        var cur = pos
        for (cmd in commands) {
            if (inSkip(cur)) {
                cur = opEnd
            }
            if (cmd.equals(NEXT)) {
                cur  += 10
            }
            if (cmd.equals(PREV)) {
                cur -= 10
            }
            if (inSkip(cur)) {
                cur = opEnd
            }
            if (cur < 0) {
                cur = 0
            } 
            if (cur > videoLen) {
                cur = videoLen
            }
        }
        
        if (inSkip(cur)) {
            cur = opEnd
        }
        answer = intToStr(cur)
    }
}