def solution(commands):
    dict = {}
    value_dict = {}
    answer = []
    for i in range(1, 51):
        for j in range(1, 51):
            dict[(i, j)] = (i, j)
            value_dict[(i,j)] = "EMPTY"
    for c in commands:
        command_list = c.split()
        if command_list[0] == "UPDATE":
            if len(command_list) == 4:
                r = int(command_list[1])
                c = int(command_list[2])
                value_dict[find((r,c), dict)] = command_list[3]
            else:
                for k in dict:
                    drc = find(k, dict)
                    if value_dict[drc] == command_list[1]:
                        value_dict[drc] = command_list[2]
        elif command_list[0] == "MERGE":
            r1 = int(command_list[1])
            c1 = int(command_list[2])
            r2 = int(command_list[3])
            c2 = int(command_list[4])
            drc1 = find((r1,c1), dict)
            drc2 = find((r2,c2), dict)
            if value_dict[drc1] == "EMPTY" and value_dict[drc2] != "EMPTY":
                value_dict[drc1] = value_dict[drc2]
            union((r1,c1), (r2,c2), dict)
            
        elif command_list[0] == "UNMERGE":
            r = int(command_list[1])
            c = int(command_list[2])
            drc = find((r,c), dict)
            final_v = value_dict[drc]
            to_empty = []
            for k in dict:
                if find(k, dict) == drc:
                    to_empty.append(k)
            for k in to_empty:
                dict[k] = k
                value_dict[k] = "EMPTY"
            value_dict[(r,c)] = final_v
        else:
            r = int(command_list[1])
            c = int(command_list[2])
            drc = find((r,c), dict)
            answer.append(value_dict[drc])
    
    return answer

def find(x,dict):
    if x == dict[x]:
        return x
    dict[x] = find(dict[x], dict)
    return dict[x]

def union(x,y,dict):
    dx = find(x,dict)
    dy = find(y,dict)
    
    if dx != dy:
        dict[dy] = dx