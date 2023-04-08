import sys, math
input = sys.stdin.readline

def solution(mod,bit):
    if bit == (1 << n) - 1:
        if mod == 0:
            return 1
        return 0
    if dp[mod][bit] != -1:
        return dp[mod][bit]
    temp = 0
    for j in range(n):
        if not bit & (1 << j):
            new_mod = ((mod * mod_10[arr_length[j]]) % k + arr[j]) % k
            temp += solution(new_mod, bit | 1 << j)
    dp[mod][bit] = temp
    return dp[mod][bit]

n = int(input())
arr = [int(input()) for _ in range(n)]
k = int(input())
arr_length = [len(str(i)) for i in arr]
arr = [i % k for i in arr]

mod_10 = [1]
for i in range(50):
    mod_10.append((mod_10[-1]*10) % k)

dp = [[-1]*(1 << n) for _ in range(k)]
answer = solution(0,0)

if answer == 0:
    print('0/1')
else:
    fact = math.factorial(n)
    if answer == fact or k == 1:
        print('1/1')
    else:
        mod = math.gcd(answer, fact)
        print('{}/{}'.format(answer//mod, fact//mod))