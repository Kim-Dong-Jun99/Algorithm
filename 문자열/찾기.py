import sys
n = sys.stdin.readline().strip()
pattern = sys.stdin.readline().strip()
kmp = [0 for i in range(len(pattern)+1)]
kmp[0] = -1
for i in range(1,len(pattern)+1):
    if i == 1:
        kmp[i] = 0
    else:
        index = (i)//2
        while index > 0:
            if pattern[:index] == pattern[i-index:i]:
                break
            else:
                index -= 1
        kmp[i] = index


result = []
i = 0
while i+len(pattern)-1 < len(n):
    j = 0
    while j < len(kmp):
        if j == len(kmp)-1:
            result.append(str(i+1))
            i += j-kmp[j]
            break
        else:
            if n[i+j] == pattern[j]:
                j += 1
            else:
                i += j-kmp[j]
                break
print(len(result))
print(' '.join(result))