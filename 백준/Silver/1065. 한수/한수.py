n = int(input())
result = 0
for i in range(1,n+1):
    if i >= 100:
        temp = []
        for j in str(i):
            temp.append(j)
        dif = int(temp[1]) - int(temp[0])
        check = True
        for j in range(len(temp)-1):
            if int(temp[j+1])-int(temp[j]) != dif:
                check = False
                break
        if check:
            result += 1
    else:
        result += 1
print(result)