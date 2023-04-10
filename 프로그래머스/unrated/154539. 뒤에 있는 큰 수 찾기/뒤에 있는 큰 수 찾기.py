from heapq import heappop, heappush
def solution(numbers):
    """12:35"""
    answer = [-1] * len(numbers)
    q = []
    for i in range(len(numbers)):
        while q:
            if q[0][0] < numbers[i]:
                answer[q[0][1]] = numbers[i]
                heappop(q)
            else:
                break
        heappush(q,[numbers[i], i])
    return answer