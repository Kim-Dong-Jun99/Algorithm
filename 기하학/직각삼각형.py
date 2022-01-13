import sys
while True:
    query = list(map(int,sys.stdin.readline().split()))
    if max(query) == 0:
        break
    else:
        tempMax = max(query)
        tempSum = 0
        for i in query:
            if i != tempMax:
                tempSum += i**2
        if tempSum == tempMax**2:
            print('right')
        else:
            print('wrong')