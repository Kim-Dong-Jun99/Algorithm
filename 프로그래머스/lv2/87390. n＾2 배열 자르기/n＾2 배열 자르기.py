def solution(n, left, right):
    answer = []
    for num in range(left, right+1):
        i = num // n
        j = num % n
        answer.append(max(i,j) + 1)
    return answer