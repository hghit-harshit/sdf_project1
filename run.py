import os
import subprocess
import sys

def main():
    if len(sys.argv) != 5:
        print("Usage : python run.py <int/float> <add/sub/mult/div> <operand1> <operand2>")
    
    
    
    #path to the build directory    
    
    src_dir = "build"
    file_name = "MyInfArith.java"
    class_name = "MyInfArith"
    
    #firsst we do to src_dir
    
    os.chdir(src_dir)
    
    # compile_result = subprocess.run(["javac",file_name])
    
    # if compile_result != 0:
    #     print("Compilation failed")
    #     sys.exit(1)
        
    args = sys.argv[1:]
    run_cmd = ["java",class_name] + args
    run_result = subprocess.run(run_cmd)
    
    if run_result != 0:
        print("Program did not run")
        
        
if __name__ ==  "__main__":
    main()

    
