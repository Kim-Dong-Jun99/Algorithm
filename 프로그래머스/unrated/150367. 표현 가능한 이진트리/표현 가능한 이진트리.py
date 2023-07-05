import math
def solution(numbers):
    answer = []
    for i in numbers:
        binary = str(bin(i))[2:]
        tree_height = int(math.log2(len(binary)))
        standard = '0'*(2**(tree_height+1) - 1 - len(binary)) + binary
        index = 0
        roots = [[get_middle(0, len(standard)-1), 0, len(standard)-1]]
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