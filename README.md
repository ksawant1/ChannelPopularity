# CSX42: Assignment 2
**Name:Krupa Sawant


-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:
## Assumptions:
     1. Each line of input contains one operation per line.
     2. The input line doesn't contain spaces.
## Program Flow:
     1. Driver code accepts line of input from File Processor and sends to Helper class.
     2. Helper Class processes every line of input and calls the corresponding action to be performed to channel context class.
     3. The channel context class implements the context interface which performs adding and removing videos from video context class, calculating metrics and approving  
     of rejecting ad request, getting and setting current state based on popularity.
     4. Result Class writes to output file and console.
     5. Data structure used is array list for output.

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 06/24/2020


