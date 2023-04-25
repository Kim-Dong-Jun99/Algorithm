from math import gcd
def solution(arrayA, arrayB):
    answer = 0
    a,b = find_gcd(arrayA), find_gcd(arrayB)
    for i in arrayB:
        if i % a == 0:
            break
    else:
        answer = max([answer, a])
    for i in arrayA:
        if i % b == 0:
            break
    else:
        answer = max([answer, b])
    
    return answer

def find_gcd(a):
    result = a[0]
    for i in range(1, len(a)):
        result = gcd(result, a[i])
    return result