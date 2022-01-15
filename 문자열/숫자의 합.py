import sys
n = int(sys.stdin.readline())
s = sys.stdin.readline().rstrip()
result = 0
for i in s:
    result += int(i)
print(result)