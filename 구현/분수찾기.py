import sys
# 23:41
# 23:57
n = int(sys.stdin.readline())
cur = 0
index = 1
while cur + index < n:
    cur += index
    index += 1
value = n - cur
if index % 2 == 0:
    print('%d/%d'%(value, index + 1 - value))
else:
    print('%d/%d'%(index + 1 - value, value))



