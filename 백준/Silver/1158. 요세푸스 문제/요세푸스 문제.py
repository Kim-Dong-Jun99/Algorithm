n = list(map(int,input().split()))
target = [i for i in range(1,n[0]+1)]

sq = []
d = n[1]
index = d-1
while target:
    if index > len(target)-1:
        index = index%(len(target))
    
       
    sq.append(target[index])
    target.pop(index)   
    index += d-1
print('<',end = '')
for i in range(n[0]):
    if i != n[0]-1:
        print('%d, '%sq[i],end = '')
    else:
        print('%d'%sq[i],end = '')
print('>')