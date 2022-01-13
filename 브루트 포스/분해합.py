n = int(input())
i = 1
check = False
while i < n:
    temp = str(i)
    tempSum = i
    for j in temp:
        tempSum += int(j)
    if tempSum == n:
        check = True
        print(i)
        break
    i += 1
if check == False:
    print(0)