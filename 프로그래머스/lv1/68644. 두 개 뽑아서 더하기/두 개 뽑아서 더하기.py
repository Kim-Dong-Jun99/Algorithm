from itertools import combinations
def solution(numbers):
    answer = set()
    for c in combinations(numbers, 2):
        answer.add(sum(c))
    answer = list(answer)
    answer.sort()
    return answer