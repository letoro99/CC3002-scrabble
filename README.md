# Scrabble

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

Interactive graphic programming language heavily inspired by 
[Scratch](https://scratch.mit.edu).
This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/), 
and aims purely to be used with the purpose of teaching in the context of the course 
_CC3002 Metodologías de Diseño y programación_ of the 
[_Computer Sciences Department (DCC)_](https://www.dcc.uchile.cl) of the 
_University of Chile_.

---

# IDEA ORIGINAL DEL EQUIPO DOCENTE DEL CURSO CC3002 DEL SEMESTRE OTOÑO 2021 #


# Context.
This project's goal is to create a simplified version of Scratch maked by Scratch Foundation. This is going to have a program environment to create simple programs without having knowledge of programming languages.

# A little explanation.
## Types
This version has different classes for Scrabble's types:
- Int : a integer value.
- Float : a double value.
- Binary : a string who have a binary number.
- Bool : a boolean value.
- String : a string.

And you can save a variable like x = 2 with a class named Var that save a string and a type object. Also you can create different syntax for a Var object with VarSyntax, this class save a Var object and a AST and when the method execute is called, replace the value of the given Var object with the AST result.

## Operators
All types will transform to others if it's possible. The next table determinates if a type can transform to another type. (For example, a Float type can't change his type to a Int but a Int can transform to Float type) 

![table](https://user-images.githubusercontent.com/71891188/125826699-ebdb10c4-bda9-49ce-8a18-4b21926c2b23.png)

Also this program will have aritmethics and logicals operators bewteen the types if it's possible. The next table determinates (For example, a )

![table2](https://user-images.githubusercontent.com/71891188/125826777-3f3bcd3a-1a9c-4f11-bff6-059e294b2891.png)

We can use thoses types with different classes that represents the aritmethics and logicals operators define in the Scrabble's types to create an AST. Depending of what operator is it, it will have a limited number of childs who can be another operator or a Scrabble's type. These operators will evaluate his childs and return a new Scrabble's type or a null value if his AST contains a invalid operation bewteen his childs.

## Control flow.

This has a class to comapre the value of Int and Float types define to the project. Actually exists two class, CompareMinor and CompareEquals.

CompareMinor has a method that returns true if the first value is minor to the second one, this method will return false in others cases.

CompareEquals has a same method than CompareMinor but this method return true if the firs value is equal to the second value.

Also, This project has classes that represents If and While.

The If class has a Compare object, and two list of AST that represent the code inside if and else. This class will execute one of the list depending de boolean value return by the Compare object.

The While class has a Compare object and a list of AST that represent the code inside de while block. This class will execute the list of ASTs while the boolean value of Compare is true.

## Memory
There are 6 class to manage the resource of memory creating and saving in a HashTable the different types objects using the Flyweigth pattern, this class are:
- IntMemory.
- FloatMemory. 
- BinaryMemory.
- StringMemory.
- BooleanMemory.
- VarMemory.

## Controller
Scrabble has a controller who can create different syntaxs with all the classes previously define (This will contains methods to create the different Types and Operations).

This controller has a list of AST. That list represent the code of a algorithm, where you can add different AST into it with the method addOperation(). If you want to run the program, you can call the method execute in the controller to run the program. To optimize memory, this controller has all the Memory objects and when a AST return a type obejct, the controller will see if this type is in one of the Memory object to return the same to optimize memory. Also the controller has a method that delete all the elements inside the list of AST called clean().

# Run (Test) Time.
For now, this project's version only has different tests for all the classes. To run this test, you can run the folder "java" inside of the folder "test" in the project. 
