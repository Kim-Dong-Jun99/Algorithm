import sys
n, d, k, c = map(int, sys.stdin.readline().split())
sushi = [int(sys.stdin.readline()) for _ in range(n)]

temp = {}
for i in range(k):
    if temp.get(sushi[i], -1) == -1:
        temp[sushi[i]] = 1
    else:
        temp[sushi[i]] += 1
maxresult = 0
l = 0
r = k
while l < n:

    if c not in temp:
        tempresult = len(temp)+1
    else:
        tempresult = len(temp)
    if maxresult < tempresult :
        maxresult = tempresult
    if maxresult == k+1:
        break
    if temp.get(sushi[r], -1) == -1:
        temp[sushi[r]] = 1
    else:
        temp[sushi[r]] += 1
    # temp.add(sushi[r])
    r += 1
    if r == n:
        r = 0
    temp[sushi[l]] -= 1
    if temp[sushi[l]] == 0:
        del temp[sushi[l]]
    l += 1

print(maxresult)