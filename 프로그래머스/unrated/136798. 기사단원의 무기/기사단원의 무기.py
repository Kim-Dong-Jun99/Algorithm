from collections import defaultdict
def solution(number, limit, power):
    answer = 1
    for n in range(2, number+1):
        div = factorize(n)
        div_num = 1
        for i in div.values():
            div_num *= (i+1)
        # print(n, div_num)
        if div_num > limit:
            answer += power
        else:
            answer += div_num
        
    return answer

def factorize(n):
    factor = 2
    factors = defaultdict(int)
    while (factor**2 <= n):  
        while (n % factor == 0):  
            factors[factor] += 1  
            n = n // factor  
        factor += 1
    if n > 1 : 
        factors[n] += 1
    return factors