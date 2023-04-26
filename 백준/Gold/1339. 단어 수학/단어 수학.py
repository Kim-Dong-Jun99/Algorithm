import sys
n = int(sys.stdin.readline())

alpha = [0 for i in range(26)]
for i in range(n):
    query = sys.stdin.readline().strip()
    for j in range(len(query)):
        alpha[ord(query[j])-65]+=10**(len(query)-1-j)

alpha.sort()
alpha.reverse()
v = 9
result = 0
for i in range(26):
    if alpha[i]:
        
        result += v*alpha[i]
        v -= 1
    else:
        break
print(result)