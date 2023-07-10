import math
def solution(n, m):
    answer = []
    def lcm(n,m):
        return (n*m) // math.gcd(n,m)
    a = math.gcd(n,m)
    b = lcm(n,m)
    answer.append(a)
    answer.append(b)

    return answer