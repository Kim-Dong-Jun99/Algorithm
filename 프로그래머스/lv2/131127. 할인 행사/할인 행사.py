from collections import defaultdict
def solution(want, number, discount):
    answer = 0
    combinations = defaultdict(lambda : 0)
    
    for i in range(len(discount)):
        combinations[discount[i]] += 1
        can_add = True
        for j in range(len(want)):
            if combinations[want[j]] < number[j]:
                can_add = False
        if can_add:
            answer += 1
        if i >= 9:
            combinations[discount[i-9]] -= 1
            
    return answer