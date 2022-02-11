import sys
n, k = map(int,sys.stdin.readline().split())
v = sys.stdin.readline().strip()

stack = []
count = 0

for i in range(n):
    if stack == []:
        stack.append(v[i])
    else:
        while stack and stack[-1] < v[i] and count < k:
            stack.pop()
            count += 1
        stack.append(v[i])
        if count == k:
            for j in stack:
                print(j,end = '')
            for j in range(i+1,n):
                print(v[j],end = '')
            break
        if i == n-1:
            while count < k:
                stack.pop()
                count += 1
            for j in stack:
                print(j,end ='')


