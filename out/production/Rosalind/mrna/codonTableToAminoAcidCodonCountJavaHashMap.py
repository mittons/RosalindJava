fin = file("RnaCodonTableFixed.txt", 'r')
fout = file("AminoAcidCodonCountJavaHashMap.txt", 'w')

aaCodonDict = {}

for fullLine in fin:
    line = fullLine.strip("\n")
    mapping = line.split(" ")
    codon = mapping[0]
    aa = mapping[1]
    if aa not in aaCodonDict:
        aaCodonDict[aa] = []
    aaCodonDict[aa].append(codon)

for aa in aaCodonDict.keys():
    outputString = "aaCodonCountMap.put(\'" + aa + "\', " + str(len(aaCodonDict[aa])) + ");" + "\n"
    fout.write(outputString)

fout.flush()
fout.close()
