import sys
n = int(sys.stdin.readline())
liquids = list(map(int,sys.stdin.readline().split()))


alc = []
acid = []
for i in liquids:
    if i < 0:
        alc.append(i)
    else:
        acid.append(i)
if alc:
    alc.sort()
    if acid:
        acid.sort()
        mindiff = sys.maxsize
        minAlc = None
        minAcid = None
        alcIndex = 0
        acidIndex = len(acid)-1
        check = True
        while alcIndex < len(alc) and acidIndex >= 0:
            temp = abs(alc[alcIndex]+acid[acidIndex])
            if temp < mindiff:
                mindiff = temp
                minAlc = alcIndex
                minAcid = acidIndex
            if alc[alcIndex] + acid[acidIndex] > 0:
                acidIndex -= 1
            elif alc[alcIndex] + acid[acidIndex] < 0:
                alcIndex += 1
            else:
                print('%d %d'%(alc[alcIndex],acid[acidIndex]))
                check = False
                break
        if check:
            if len(alc) >= 2 and len(acid) >=2 and mindiff > min(abs(alc[-2]+alc[-1]),abs(acid[0]+acid[1])):
                if abs(alc[-2]+alc[-1]) < abs(acid[0]+acid[1]):
                    print('%d %d'%(alc[-2],alc[-1]))
                else:
                    print('%d %d'%(acid[0],acid[1]))
            else:
                print('%d %d'%(alc[minAlc],acid[minAcid]))
    else:
        print('%d %d'%(alc[-2],alc[-1]))
else:
    acid.sort()
    print('%d %d'%(acid[0],acid[1]))




