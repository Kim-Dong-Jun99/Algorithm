import sys
n = int(sys.stdin.readline())
vid = [sys.stdin.readline().rstrip() for i in range(n)]
def quadtree(vid):
    if len(vid) == 1:
        if vid[0] == '0':
            return '0'
        else:
            return '1'
    else:
        d = len(vid)
        mid = d // 2
        area1 = [vid[i][mid:] for i in range(mid)]
        area2 = [vid[i][:mid] for i in range(mid)]
        area3 = [vid[i][:mid] for i in range(mid,d)]
        area4 = [vid[i][mid:] for i in range(mid,d)]
        temp = [area2,area1,area3,area4]
        c = []
        for i in temp:
            c.append(quadtree(i))
        if c == ['1','1','1','1']:
            return '1'
        elif c == ['0','0','0','0']:
            return '0'
        else:
            result = '('
            for i in c:
                result += i
            result += ')'
            return result
print(quadtree(vid))