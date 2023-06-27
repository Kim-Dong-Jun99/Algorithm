import sys
from collections import defaultdict

TC = int(sys.stdin.readline())

for ____ in range(TC):
    h, w = map(int, sys.stdin.readline().split())
    
    buildings = [['*' for _ in range(w)] for _ in range(h)]
    visited = [[0 for _ in range(w)] for _ in range(h)]
    
    for i in range(h):
        row = sys.stdin.readline().rstrip()
        
        for j in range(w):
            buildings[i][j] = row[j]
                
    keys = sys.stdin.readline().rstrip()
    """
    갈 수 있는 곳이면 can_go = 1
    갈 수 없는 곳이면 can_go = -1
    아직 열쇠를 못 얻어서 갈 수 있을지 없을지 모르면 can_go = 0
    """
    can_go = defaultdict(lambda : 0)
    can_go['.'] = 1
    can_go['*'] = -1
    can_go['$'] = 2
    
    if keys != '0':
        for i in keys:
            can_go[chr(ord(i)-32)] = 1
            
    def move(i, j):
        temp = []
        
        if i - 1 >= 0:
            temp.append([i-1, j])
            
        if i + 1 < h:
            temp.append([i+1, j])
            
        if j - 1 >= 0:
            temp.append([i, j-1])
            
        if j + 1 < w:
            temp.append([i, j+1])
            
        return temp
            
    result = 0
    cur = []
    after_key = defaultdict(lambda : [])
    for i in range(h):
        if can_go[buildings[i][0]] >= 1:
            if can_go[buildings[i][0]] == 2:
                result += 1
            cur.append([i, 0])
            visited[i][0] = 1
        elif can_go[buildings[i][0]] == 0:
            a_value = ord(buildings[i][0])
            visited[i][0] = 1
            if 97 <= a_value:
                can_go[chr(a_value-32)] = 1
                cur.append([i, 0])
            else:
                after_key[buildings[i][0]].append([i,0])
        if can_go[buildings[i][w-1]] >= 1:
            if can_go[buildings[i][w-1]] == 2:
                result += 1
            cur.append([i, w-1])
            visited[i][w-1] = 1
        elif can_go[buildings[i][w-1]] == 0:
            a_value = ord(buildings[i][w-1])
            visited[i][w-1] = 1
            if 97 <= a_value:
                can_go[chr(a_value-32)] = 1
                cur.append([i, w-1])
            else:
                after_key[buildings[i][w-1]].append([i,w-1])
    for j in range(1,w-1):
        if can_go[buildings[0][j]] >= 1:
            if can_go[buildings[0][j]] == 2:
                result += 1
            cur.append([0, j])
            visited[0][j] = 1
        elif can_go[buildings[0][j]] == 0:
            a_value = ord(buildings[0][j])
            visited[0][j] = 1
            if 97 <= a_value:
                can_go[chr(a_value-32)] = 1
                cur.append([0, j])
            else:
                after_key[buildings[0][j]].append([0,j])
        if can_go[buildings[h-1][j]] >= 1:
            if can_go[buildings[h-1][j]] == 2:
                result += 1
            cur.append([h-1, j])
            visited[h-1][j] = 1
        elif can_go[buildings[h-1][j]] == 0:
            a_value = ord(buildings[h-1][j])
            visited[h-1][j] = 1
            if 97 <= a_value:
                can_go[chr(a_value-32)] = 1
                cur.append([h-1, j])
            else:
                after_key[buildings[h-1][j]].append([h-1,j])
    
    while cur:
        temp = []
        for i,j in cur:
            for k,l in move(i,j):
                if can_go[buildings[k][l]] == 0 and visited[k][l] == 0:
                    a_value = ord(buildings[k][l])
                    visited[k][l] = 1
                    if 97 <= a_value:
                        if can_go[chr(a_value-32)] == 0:
                            for k_, l_ in after_key[chr(a_value-32)]:
                                temp.append([k_, l_])
                            can_go[chr(a_value-32)] = 1
                        temp.append([k,l])
                    else:
                        after_key[buildings[k][l]].append([k, l])
                
                elif can_go[buildings[k][l]] == 1 and visited[k][l] == 0:
                    visited[k][l] = 1
                    temp.append([k, l])
                elif can_go[buildings[k][l]] == 2 and visited[k][l] == 0:
                    visited[k][l] = 1
                    temp.append([k, l])
                    result += 1
                    
        cur = temp
        
        
    sys.stdout.write("%d\n"%result)
                