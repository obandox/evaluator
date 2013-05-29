cd $1
g++ $2 1> out 2> error
cat input | ./a.out  
