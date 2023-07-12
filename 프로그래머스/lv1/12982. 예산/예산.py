def solution(d, budget):
    answer = 0
    d.sort()
    index = 0
    while index < len(d):
        if budget - d[index] >= 0:
            budget -= d[index]
            index += 1
            answer += 1
        else:
            break
    return answer