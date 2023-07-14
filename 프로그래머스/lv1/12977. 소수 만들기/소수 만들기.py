from itertools import combinations
def solution(nums):
    answer = 0
    for c in combinations(nums, 3):
        temp_sum = sum(c)
        done = True
        for i in range(2, int(temp_sum ** 0.5) + 1):
            if temp_sum % i == 0:
                done = False
                break
        if done:
            answer += 1
    return answer