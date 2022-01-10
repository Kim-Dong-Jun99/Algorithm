n = int(input())
cycle = 0
newN = str(n)
if len(newN) == 1:
    newN = '0'+newN
while True:
    left = newN[0]
    right = newN[1]
    temp = int(left)+int(right)
    temp = str(temp)
    newN = newN[1]+temp[-1]
    cycle += 1
    if int(newN) == n:
        print(cycle)
        break