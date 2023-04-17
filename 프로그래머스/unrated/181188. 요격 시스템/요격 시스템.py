def solution(targets):
    answer = 0
    targets.sort(key= lambda x : x[1])
    shoot = []
    for i in targets:
        if shoot == []:
            shoot = i
            answer += 1
        else:
            if shoot[1] <= i[0]:
                shoot = i
                answer += 1
    
    return answer