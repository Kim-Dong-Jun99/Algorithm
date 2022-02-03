n = input()
temp = ''
result = []
for i in range(len(n) - 1, -1, -1):
    temp = n[i] + temp
    result.append(temp)

result.sort()
for i in result:
    print(i)