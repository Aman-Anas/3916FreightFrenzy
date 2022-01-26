# author Nathan Battle
# written 10/29/2021
#
# Run program, give it the yaml, and copy and paste from output file into MeepMeep
# If you run it in IDLE, you can copy and paste directly from the Shell
# Not tested with Tangent or Constant headings

while 1:
    fileName = input("Enter file name or [quit] to quit:\n")
    if (fileName.lower() == "quit" or fileName.lower() == "[quit]"):
        break
    if (fileName[-5] != ".yaml"):
        fileName += ".yaml"
    fileRaw = open(fileName, 'r')
    fileStr = fileRaw.read()
    fileRaw.close()
    points = fileStr.split("- position:\n")
    points[0] = points[0][17:-14]#.split("\n")
    for i in range(len(points)):
        points[i] = points[i].split("\n")
        if '' in points[i]:
            points[i].remove('')
        for j in range(len(points[i])):
            points[i][j] = points[i][j][points[i][j].index(":") + 2:]
    start = points.pop(0)
    output = "                .followTrajectorySequence(drive ->\n                        drive.trajectorySequenceBuilder(new Pose2d("
    output += ", ".join(start[0:3]) + "))\n"# + start[3] + ")\n"
    for point in points:
        output += "                                .splineTo" + point[4].strip('"').title() + "Heading(new "
        if point[4] in ('"SPLINE"', '"LINEAR"'):
            output += "Pose2d(" + ", ".join(point[0:3]) + "), " + point[3] + ")\n"
        else:
            output += "Vector2d(" + ", ".join(point[0:2]) + "))\n"
    output += "\n                                .build()\n                );"
    print(output)
    outputFile = open(fileName[:-5] + ".txt", 'w')
    outputFile.write(output)
    outputFile.close()
