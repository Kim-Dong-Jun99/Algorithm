import sys

A = sys.stdin.readline().rstrip()
B = sys.stdin.readline().rstrip()

max_length = min(len(A), len(B))
to_zero = 97
answer = 0
for i in range(1, max_length+1):
    aHash = [0 for _ in range(26)]
    aHashs = set()
    for j in range(i):
        aHash[ord(A[j]) - to_zero] += 1
    aHashs.add(tuple(aHash))
    for j in range(i, len(A)):
        aHash[ord(A[j]) - to_zero]  += 1
        aHash[ord(A[j-i]) - to_zero] -= 1
        aHashs.add(tuple(aHash))
    bHash = [0 for _ in range(26)]
    for j in range(i):
        bHash[ord(B[j]) - to_zero] += 1
    moveOn = False
    if tuple(bHash) in aHashs:
        answer = i
        continue
        
    for j in range(i, len(B)):
        bHash[ord(B[j]) - to_zero]  += 1
        bHash[ord(B[j-i]) - to_zero] -= 1
        if tuple(bHash) in aHashs:
            answer = i
            break
print(answer)