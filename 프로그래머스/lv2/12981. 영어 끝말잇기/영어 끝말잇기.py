def solution(n, words):
    answer = []
    visited = set()
    lastword = ""
    index = 1
    turn = 1
    for i in words:
        if lastword != "":
            if lastword[-1] != i[0] or i in visited:
                return [index, turn]
        lastword = i
        visited.add(i)
        index += 1
        if index > n:
            index = 1
            turn += 1
                
    return [0,0]