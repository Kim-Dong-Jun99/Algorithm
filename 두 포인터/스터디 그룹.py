import sys
n, k, d = map(int, sys.stdin.readline().split())
ns = [[list(map(int, sys.stdin.readline().split())), list(map(int, sys.stdin.readline().split()))] for _ in range(n)]
ns.sort(key=lambda a: a[0][1])
result = 0
left = 0
right = 0
temp = {}
# print(ns)
while left <= right and left < n:
    # print(temp)
    any = len(temp)
    every = 0
    # if right == n:
    #     for i in temp:
    #         if temp[i] == right - left:
    #             every += 1
    #     tempresult = (any-every)*(right-left)
    # else:
    #     for i in temp:
    #         if temp[i] == right-left+1:
    #             every += 1
    #     tempresult = (any-every)*(right-left+1)
    for i in temp:
        if temp[i] == right - left:
                every += 1
    tempresult = (any-every)*(right-left)
    result = max(tempresult, result)
    if right < n and ns[left][0][1] + d >= ns[right][0][1]:
        for i in ns[right][1]:
            if temp.get(i, -1) == -1:
                temp[i] = 1
            else:
                temp[i] += 1
        right += 1
    else:
        for i in ns[left][1]:
            temp[i] -= 1
            if temp[i] == 0:
                del temp[i]
        left += 1
print(result)
