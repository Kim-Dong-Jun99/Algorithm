def solution(k, m, score):
    answer = 0
    score.sort(reverse = True)
    temp_min = k
    temp = 0
    for i in range(len(score)):
        if score[i] < temp_min:
            temp_min = score[i]
        temp += 1
        if temp % m == 0:
            answer += m * temp_min
            temp = 0
            temp_min = k
        
    return answer