fin = file("monoisotopicMassTable.txt", 'r')
fout = file("monoisotopicMassTableJavaHashMap.txt", 'w')

for fullLine in fin:
    line = fullLine.strip("\n")
    aa = line[0]
    monoisotopicMass = line[4:]
    outputString = "aaMonoisotopicMassMap.put(\'" + aa + "\', " + monoisotopicMass + ");" + "\n"
    fout.write(outputString)

fout.flush()
fout.close()
