# import time
#
#
# li = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0] * 60000
# st = '1234567890' * 60000
# start = time.time()
# st = st[:1000] + '1' + st[1000:]
# temp1 = time.time() - start
# print(time.time() - start)
# start = time.time()
# li = li[:1000] + [1] + li[1000:]
# print(time.time() - start)
# start = time.time()
# li.insert(1000, 1)
# print(time.time() - start)
# start = time.time()
# li.append(1)
# temp2 = time.time() - start
# print(time.time() - start)
# if temp1 > temp2:
#     print(temp1 // temp2)
# compare = [i for i in range(100000)]
# start = time.time()
# result = 0
# for i in range(len(compare)):
#     if i%2 == 0:
#         result += 1
# time1 = time.time()-start
# start = time.time()
# result = 0
# i = 0
# while i < len(compare):
#     if i%2 == 0:
#         result += 1
#     i += 1
# time2 = time.time()-start
# print('time compare for loop and while loop with certain condition : %f'%(time2 // time1))
# start = time.time()
# a = [0 for i in range(1000000)]
#
# c = a[999990:]
# temp1 = time.time()-start
#
# start = time.time()
# a = [0 for i in range(20)]
#
# c = a[10:]
# temp2 = time.time()-start
# print(temp1//temp2)

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