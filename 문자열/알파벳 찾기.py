import sys
s = sys.stdin.readline()
for i in range(26):
    if (chr(i+97) in s):
        print(s.index(chr(i+97)),end = ' ')
    else:
        print('-1',end = ' ')