def solution(k, d):
    answer = 0
    x_index = 0
    d2 = d**2
    for i in range(d//k + 1):
        y_d = int((d2 - (i*k)**2)**0.5)
        answer += y_d // k + 1
    return answer
