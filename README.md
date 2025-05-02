# Infinite Precision Calculator
## SDF Project 1

This is a simple calculator that supports for integer and Float
- Addition
- Subtraction
- Divisio 
- Multiplication

## Installation
Clone the repository and run : <br>
`ant compile` to compile the project<br>
then you can either use GUI version by running :
`ant runGui` <br>
` ant run -Dargs="<int/float> <operation> <operand1> <operand2>" `
or use the terminal version by going to build directory and running:<br>
` java MyInfArith <int/float> <operation> <operand1> <operand2>`<br>
or if you want to build using python you can complie using ant and then run<br>
`python run.py <int/float> <operation> <operand1> <operand2>`

Or you can use the docker by:
` docker pull hghit007/sdf-project-1:fourth `
and then :
` docker run --rm hghit007/sdf-project-1:fourth docker run --rm hghit007/sdf-project-1:first` 
