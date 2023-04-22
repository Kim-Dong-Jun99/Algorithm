from heapq import heappop, heappush
def solution(n, k, enemy):
    answer = 0
    killed = []
    if k >= len(enemy):
        return len(enemy)
    if n >= sum(enemy):
        return len(enemy)
    
    for i in enemy:
        if n - i >= 0:
            answer += 1
            heappush(killed, -i)
            n -= i
        else:
            if k - 1 >= 0:
                if killed:
                    if i > -killed[0]:
                        k -= 1
                        answer += 1
                    else:
                        n -= killed[0]
                        n -= i
                        answer += 1
                        k -= 1
                        heappop(killed)
                        heappush(killed, -i)
                else:
                    k -= 1
                    answer += 1
            else:
                break        
    return answer