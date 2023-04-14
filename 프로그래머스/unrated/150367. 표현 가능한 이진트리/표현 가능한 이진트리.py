import math
def solution(numbers):
    """
    16:09
    트리 탐색 순서 - LNR
    7 - 111
    42 - 0101010  
    트리의 높이 h 일때, 전체 노드의 수 = 2**(h+1)-1, (h는 0부터 시작)
    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
    """
    answer = []
    for i in numbers:
        # 이진수는 2번 인덱스부터 시작
        binary = str(bin(i))[2:]
        tree_height = int(math.log2(len(binary)))
        standard = '0'*(2**(tree_height+1) - 1 - len(binary)) + binary
        index = 0
        roots = [[get_middle(0, len(standard)-1), 0, len(standard)-1]]
        # if standard[roots[0][0]] == '0' and len(standard) != 1:
        #     answer.append(0)
        #     continue
        possible = True
        while index < tree_height:
            temp = []
            
            for r,left,right in roots:
                left_child = get_middle(left, r-1)
                right_child = get_middle(r+1,right)
                if standard[r] == '1' or standard[left_child] == '0' and standard[right_child] == '0':
                    temp.append([left_child, left, r-1])
                    temp.append([right_child, r+1, right])
                else:
                    possible = False
                    break
            if possible == False:
                break
            index += 1
            roots = temp
        if possible:
            answer.append(1)
        else:
            answer.append(0)
                    
                
            
        
        
        
    return answer
def get_middle(i, j):
    return (i + j) // 2