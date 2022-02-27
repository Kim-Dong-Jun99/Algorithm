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

import sys
from math import gcd
n = int(sys.stdin.readline())
ns = [int(sys.stdin.readline()) for _ in range(n)]
k = int(sys.stdin.readline())
slen = [0 for _ in range(n)]
tl = 0
for i in range(n):
    temp = len(str(ns[i]))
    tl += temp
    slen[i] = temp

# dp 구조는 확정
# dp = [[[0]*k for _ in range(2**n)] for _ in range(n)]
# for i in range(n):
#     d = ns[i]*(10**(tl-slen[i]))
#     dp[0][1 << i][d % k] += 1
#
# for i in range(n-1):
#     for j in range(2**n):
#         for l in range(k):
#             if dp[i][j][l] != 0:
#                 temps = 0
#                 for m in range(n):
#                     if j & (1 << m) == (1 << m):
#                         temps += slen[m]
#                 for m in range(n):
#                     if j & (1 << m) != (1 << m):
#                         d = ns[m]*(10**(tl - temps - slen[m]))
#                         dp[i+1][j | (1 << m)][(l+d) % k] += dp[i][j][l]
# # print(dp[-1][-1])
# total = sum(dp[-1][-1])
# r = dp[-1][-1][0]
# g = gcd(r, total)
# left = r // g
# right = total // g
# print('%d/%d'%(left,right))

dp = [[[] for _ in range(2**n)] for _ in range(n)]
for i in range(n):
    d = ns[i]*(10**(tl-slen[i]))
    # dp[0][1 << i][d % k].append(1)
    dp[0][1 << i].append(d)
for i in range(n-1):
    for j in range(2**n):
        # for l in range(k):
        #     if dp[i][j][l] != 0:
        #         temps = 0
        #         for m in range(n):
        #             if j & (1 << m) == (1 << m):
        #                 temps += slen[m]
        #         for m in range(n):
        #             if j & (1 << m) != (1 << m):
        #                 d = ns[m]*(10**(tl - temps - slen[m]))
        #                 dp[i+1][j | (1 << m)][(l+d) % k] += dp[i][j][l]
        if dp[i][j]:
            for l in dp[i][j]:
                temps = 0
                for m in range(n):
                    if j & (1 << m) == (1 << m):
                        temps += slen[m]
                for m in range(n):
                    if j & (1 << m) != (1 << m):
                        d = ns[m]*(10**(tl - temps - slen[m]))
                        dp[i+1][j | (1 << m)].append((l+d) % k)
# print(dp[-1][-1])
# total = sum(dp[-1][-1])
# r = dp[-1][-1][0]
# g = gcd(r, total)
# left = r // g
# right = total // g
# print('%d/%d'%(left,right))
total = len(dp[-1][-1])
r = dp[-1][-1].count(0)
g = gcd(r, total)
left = r // g
right = total // g
print('%d/%d'%(left, right))