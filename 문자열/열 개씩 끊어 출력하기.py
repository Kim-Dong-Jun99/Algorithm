import sys
query = sys.stdin.readline().rstrip()
index = 0
while index + 10 < len(query):
    print(query[index:index+10])
    index += 10
print(query[index:])