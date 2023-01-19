import sys

n = int(input())

max_5 = n // 5

for i in range(max_5,-1,-1):
    max_2 = n - 5*i
    if max_2 % 2 == 0:
        print(i + max_2 // 2)
        sys.exit()
print(-1)