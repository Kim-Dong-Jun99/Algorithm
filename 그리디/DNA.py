import sys
N, M = map(int, sys.stdin.readline().split())

dnas = [sys.stdin.readline() for _ in range(N)]
dna = "ACGT"
s = {}

for i in range(M):
    s[i] = {"A":0, "C":0, "G":0, "T":0}

for i in dnas:
    for j in range(M):
        s[j][i[j]] += 1
result = 0
for i in s.keys():
    cur_max = 0
    to_print = ""
    
    for j in dna:
        if s[i][j] > cur_max:
            cur_max = s[i][j]
            to_print = j
    result += sum(s[i].values()) - cur_max
    print(to_print, end="")
print()
print(result)
