import sys

n = sys.stdin.readline().rstrip()
m = sys.stdin.readline().rstrip()

LCS = [[0 for _ in range(len(m)+1)] for _ in range(len(n)+1)]

for i in range(1, len(n)+1):
    for j in range(1, len(m)+1):
        if n[i-1] == m[j-1]:
            LCS[i][j] = LCS[i - 1][j - 1] + 1
        else:
            LCS[i][j] = max(LCS[i - 1][j], LCS[i][j - 1])
print(LCS[len(n)][len(m)])
result = []
i = len(n)
j = len(m)
while i > 0 and j > 0 and LCS[i][j] != 0:
    if LCS[i-1][j] == LCS[i][j]:
        i -= 1
    elif LCS[i][j-1] == LCS[i][j]:
        j -= 1
    else:
        result.append(n[i-1])
        i -= 1
        j -= 1
        
while result:
    sys.stdout.write(str(result.pop()))
sys.stdout.flush()