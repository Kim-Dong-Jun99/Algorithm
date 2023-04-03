import sys
"""
12:57
13:03
"""

roomNumber = sys.stdin.readline().rstrip()

roomDict = {"0":0,"1":0, "2":0, "3":0, "4":0, "5":0, "6":0, "7":0, "8":0}

for n in roomNumber:
    if n == "6" or n == "9":
        roomDict["6"] += 1
    else:
        roomDict[n] += 1
result = []
for n in roomDict:
    if n == "6":
        result.append(roomDict[n] // 2 + roomDict[n]%2)
    else:
        result.append(roomDict[n])
print(max(result))
