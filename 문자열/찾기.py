import sys

n = sys.stdin.readline().strip()
pattern = sys.stdin.readline().strip()
kmp = [0 for i in range(len(pattern) + 1)]
kmp[0] = -1
kmp[1] = 0
for i in range(1, len(kmp)-1):
    if pattern[kmp[i]] == pattern[i]:
        kmp[i+1] = kmp[i]+1
    else:
        if pattern[i] == pattern[0]:
            kmp[i+1] = 1
        else:
            kmp[i+1] = 0

result = []
i = 0
while i + len(pattern) - 1 < len(n):
    j = 0
    while j < len(kmp) - 1:
        if n[i + j] == pattern[j]:
            j += 1
        else:
            i += j - kmp[j]
            break
    if j == len(kmp) - 1:
        result.append(str(i + 1))
        i += j - kmp[j]

print(len(result))
print(' '.join(result))