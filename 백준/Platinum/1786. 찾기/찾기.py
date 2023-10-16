n = input()
pattern = input()
kmp = [0 for i in range(len(pattern))]
j = 0
for i in range(1,len(pattern)):
    while j >0 and pattern[i] != pattern[j]:
        j = kmp[j-1]
    if pattern[i] == pattern[j]:
        j += 1
        kmp[i] = j
        

result = []
j = 0
for i in range(len(n)):
    while j > 0 and n[i] != pattern[j]:
        j = kmp[j-1]
    if n[i] == pattern[j]:
        if j == len(pattern)-1:
            result.append(i-len(pattern)+2)
            j = kmp[j]
        else:
            j += 1
    

print(len(result))
for i in result:
    print(i,end = ' ')