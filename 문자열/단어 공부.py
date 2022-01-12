import sys
n = sys.stdin.readline().strip()

alpha = [0 for i in range(26)]
for i in n.lower():
    alpha[ord(i)-97] += 1
forMax = []
maxAppear = None
for i in range(26):
    if maxAppear == None:
        maxAppear = alpha[i]
        forMax.append(chr(65+i))
    else:
        if alpha[i] > maxAppear:
            maxAppear = alpha[i]
            forMax = [chr(65+i)]
        elif alpha[i] == maxAppear:
            forMax.append(chr(65+i))
if len(forMax) > 1:
    print('?')
else:
    print(forMax[0])