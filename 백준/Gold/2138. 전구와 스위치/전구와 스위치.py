N = int(input())

before = list(map(int, input()))
after = list(map(int, input()))
temp_before = before[:]
cnt = 0
for i in range(1, N):

    if before[i - 1] != after[i - 1]:
        cnt += 1
        before[i] = int(not before[i])
        before[i - 1] = int(not before[i - 1])
        if i != N - 1:
            before[i + 1] = int(not before[i + 1])
else:
    if ''.join(map(str, before)) == ''.join(map(str, after)):
        print(cnt)
        exit()

cnt = 1

temp_before[0] = not temp_before[0]
temp_before[1] = not temp_before[1]

for i in range(1, N):

    if temp_before[i - 1] != after[i - 1]:
        cnt += 1
        temp_before[i] = int(not temp_before[i])
        temp_before[i - 1] = int(not temp_before[i - 1])
        if i != N - 1:
            temp_before[i + 1] = int(not temp_before[i + 1])
else:
    if ''.join(map(str, temp_before)) == ''.join(map(str, after)):
        print(cnt)
        exit()

print(-1)
