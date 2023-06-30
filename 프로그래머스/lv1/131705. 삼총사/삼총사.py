from itertools import combinations
def solution(number):
    answer = 0
    temp = combinations(number, 3)
    for i in temp:
        if sum(i) == 0:
            answer += 1
    return answer