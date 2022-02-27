import sys
HEIGHT, CNT = 0, 1
N = int(sys.stdin.readline())
arr = [int(sys.stdin.readline()) for _ in range(N)]
stack = []
answer = 0
for h in arr:
    while stack and stack[-1][HEIGHT] < h:
        answer += stack.pop()[CNT]
    if not stack:
        stack.append((h, 1))
        continue

    if stack[-1][HEIGHT] == h:
        cnt = stack.pop()[CNT]
        answer += cnt
        if stack:
            answer += 1

        stack.append((h, cnt + 1))

    else:
        stack.append((h, 1))
        answer += 1


print(answer)
