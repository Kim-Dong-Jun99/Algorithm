import sys
n = int(sys.stdin.readline())
polygon = [list(map(int,sys.stdin.readline().split())) for i in range(n)]
def getSize(polygon,n):
    temp = 0
    for i in range(n):
        if i == n-1:
            temp += polygon[i][0]*polygon[0][1]
        else:
            temp += polygon[i][0]*polygon[i+1][1]
    for i in range(n):
        if i == n-1:
            temp -= polygon[i][1]*polygon[0][0]
        else:
            temp -= polygon[i][1]*polygon[i+1][0]
    return temp
result = abs(getSize(polygon,n))/2
print('%0.1f'%result)