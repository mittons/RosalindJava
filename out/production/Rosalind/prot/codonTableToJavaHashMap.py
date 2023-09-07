fin = file("RnaCodonTableFixed.txt", 'r')
fout = file("RnaCodonTableJavaHashMap.txt", 'w')

for fullLine in fin:
    line = fullLine.strip("\n")
    mapping = line.split(" ")
    outputString = "rnaCodonTable.put(\"" + mapping[0] + "\", \"" + mapping[1] + "\");" + "\n" 
    fout.write(outputString)
    #print outputString

fout.flush()
fout.close()
    
