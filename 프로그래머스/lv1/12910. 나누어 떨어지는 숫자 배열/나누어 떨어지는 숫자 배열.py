def solution(arr, divisor):
    answer = []
    for i in arr:
        if i % divisor == 0:
            answer.append(i)
    answer.sort()
    if answer == []:
        return [-1]
    return answer