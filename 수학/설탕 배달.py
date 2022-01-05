n = int(input())
sugar = [-1 for i in range(n+1)]
if n >= 3:
    sugar[3] = 1
if n >= 5:
    sugar[5] = 1
i = 6
while i <= n:
    if sugar[i-3] != -1 and sugar[i-5] != -1:
        sugar[i] = min(sugar[i-3]+1,sugar[i-5]+1)
    else:
        if sugar[i-3] != -1:
            sugar[i] = sugar[i-3]+1
        elif sugar[i-5] != -1:
            sugar[i] = sugar[i-5] +1
    i += 1
print(sugar[n])