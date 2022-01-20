n = int(input())
times = [300, 60, 10]
result = [0] * 3
for i in range(3):
    result[i] += n // times[i]
    n = n % times[i]
if n == 0:
    for i in range(3):
        print(result[i], end=' ')
else:
    print(-1)
