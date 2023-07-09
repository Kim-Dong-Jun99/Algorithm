def solution(n):
    answer = ''
    a = []
    for i in str(n):
        a.append(int(i))
    a.sort(reverse = True)
    for i in a:
        answer += str(i)
    return int(answer)