# Infinite Precision Calculator
## SDF Project 1

This is a simple calculator that supports for integer and Float
- Addition
- Subtraction
- Divisio 
- Multiplication

## Installation 
### Using ant
Clone the repository and run : <br>
`ant compile` to compile the project<br>
then you can either use GUI version by running :<br>
`ant runGui` <br>
or run from the terminal: <br>
` ant run -Dargs="<int/float> <operation> <operand1> <operand2>" `<br>
or use the terminal version by going to build directory and running:<br>
` java MyInfArith <int/float> <operation> <operand1> <operand2>`<br>
and use `ant clean` to clean the build
### Using python
or if you want to build using python you can complie using ant and then run<br>
`python run.py <int/float> <operation> <operand1> <operand2>`<br>

### Using Docker
<br>
Or you can use the docker by:<br>
` docker pull hghit007/sdf-project-1:fourth `<br>
and then :<br>
` docker run --rm hghit007/sdf-project-1:fourth docker run --rm hghit007/sdf-project-1:first` 
