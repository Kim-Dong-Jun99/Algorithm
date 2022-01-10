n = [0 for i in range(10 ** 4 + 1)]
for i in range(1, 10 ** 4 + 1):
    temp = i
    while True:
        for j in str(temp):
            temp += int(j)
        if temp <= 10 ** 4:
            n[temp] = 1
        else:
            break
for i in range(1, 10 ** 4 + 1):
    if n[i] == 0:
        print(i)
