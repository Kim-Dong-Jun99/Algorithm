def minimum(a, b):
    A, B = a, b
    while b > 0:
        a, b = b, a % b
        
    GCD = a # 최대공약수
    return A * B // GCD

def solution(arr):
    arr.sort()
    result = arr[0]
    for i in range(0, len(arr)-1):
        result = minimum(result, arr[i+1])
    return result