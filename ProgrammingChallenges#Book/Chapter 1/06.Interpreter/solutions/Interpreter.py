from sys import stdin


class Computer:
    def __init__(self):
        self.address = 0
        self.registers = [0 for x in range(10)]
        self.RAM  = ["000" for x in range(1000)]

        
    def runProgram(self):
        def case0():
            self.instruction0(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))

        def case2():
            self.instruction2(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))

        def case3():
            self.instruction3(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))

        def case4():
            self.instruction4(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))

        def case5():
            self.instruction5(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))

        def case6():
            self.instruction6(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))

        def case7():
            self.instruction7(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))

        def case8():
            self.instruction8(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))

        def case9():
            self.instruction9(int(self.RAM[self.address][1]), int(self.RAM[self.address][2]))
            
        switch = {
            '0': case0,
            '2': case2,
            '3': case3,
            '4': case4,
            '5': case5,
            '6': case6,
            '7': case7,
            '8': case8,
            '9': case9
        }

        countInstructions = 1
        while (self.RAM[self.address][0] != '1'):
            countInstructions += 1

            if (self.RAM[self.address][0] in switch.keys()):
                switch[self.RAM[self.address][0]]()
            self.address += 1
        
        return countInstructions



    def instruction0(self, d, s):
        if (self.registers[s] != 0):
            self.address = self.registers[d]-1
    
    def instruction2(self, d, n):
        self.registers[d] = n
        self.registers[d] %= 1000
    
    def instruction3(self, d, n):
        self.registers[d] += n
        self.registers[d] %= 1000
    
    def instruction4(self, d, n):
        self.registers[d] *= n
        self.registers[d] %= 1000
    
    def instruction5(self, d, s):
        self.registers[d] = self.registers[s]
        self.registers[d] %= 1000
    
    def instruction6(self, d, s):
        self.registers[d] += self.registers[s]
        self.registers[d] %= 1000
    
    def instruction7(self, d, s):
        self.registers[d] *= self.registers[s]
        self.registers[d] %= 1000
    
    def instruction8(self, d, a):
        self.registers[d] = int(self.RAM[self.registers[a]])
        self.registers[d] %= 1000
    
    def instruction9(self, s, a):
        self.RAM[self.registers[a]] = str(self.registers[s])



# Main program
def main():    
    cases = int(stdin.readline())
    stdin.readline()
    
    for i in range(0, cases):
        pc = Computer()
        
        index = 0;
        for inputLine in stdin:
            if (inputLine == "" or inputLine == "\n"):
                break

            pc.RAM[index] = inputLine
            index += 1
        
        print(pc.runProgram())
        
        if (i != cases-1):
            print()
        

main()
