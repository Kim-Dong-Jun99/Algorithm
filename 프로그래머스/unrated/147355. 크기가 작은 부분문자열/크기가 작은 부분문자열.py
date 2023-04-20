def solution(t, p):
    answer = 0
    for i in range(len(t)):
        if i + len(p) - 1 < len(t):
            
            possible = True
            for j in range(len(p)):
                if int(t[i+j]) < int(p[j]):
                    break
                elif int(t[i+j]) == int(p[j]):
                    continue
                else:
                    possible = False
                    break
            if possible:
                answer += 1
        else:
            break
    return answer