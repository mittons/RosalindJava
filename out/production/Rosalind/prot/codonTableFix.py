fin = file("RnaCodonTable.txt", 'r')
fout = file("RnaCodonTableFixed.txt", 'w')

for fullLine in fin:
    line = fullLine.strip('\n')
    l0 = line[0:11]
    l1 = line[11:22]
    l2 = line[22:33]
    l3 = line[33:]
    fout.write(l0 + "\n")
    fout.write(l1 + "\n")
    fout.write(l2 + "\n")
    fout.write(l3 + "\n")

fout.flush()
fout.close()
    

