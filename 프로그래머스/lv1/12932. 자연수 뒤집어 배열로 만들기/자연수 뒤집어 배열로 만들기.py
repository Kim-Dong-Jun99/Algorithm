def solution(n):
    answer = []
    s = str(n)
    for i in range(len(s)-1, -1, -1):
        answer.append(int(s[i]))
    return answer