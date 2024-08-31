import sys

s = list(sys.stdin.readline().rstrip())

n = s.count('0')
m = s.count('1')


check = 0
for i in s:
    if check == m//2:
        break
    if i == '1':
        s.remove(i)
        check += 1

check = 0
s = s[::-1]
for i in s:
    if check == n//2:
        break
    if i == '0':
        s.remove(i)
        check += 1

for i in s[::-1]:
    print(i, end='')