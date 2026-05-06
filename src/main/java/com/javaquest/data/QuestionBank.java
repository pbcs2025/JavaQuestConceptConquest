package com.javaquest.data;

import com.javaquest.model.*;
import java.util.*;

public final class QuestionBank {

    // JAVA CONCEPT: COLLECTIONS
    // ArrayList stores all 18 rooms in order
    public static final List<Room> ALL_ROOMS = new ArrayList<>();

    // JAVA CONCEPT: COLLECTIONS
    // HashMap maps question ID -> Question (O(1) lookup)
    public static final Map<String, Question> Q_MAP = new HashMap<>();

    private QuestionBank() {
    }

    static {
        Room room1 = new Room(1, "Variables & Data Types", "Easy", "Learn primitives, references, and declarations.", List.of(
                q("R1-Q1", "Which primitive type stores whole numbers up to about 2 billion?", new String[]{"double", "int", "char", "boolean"}, "int", "int is a 32-bit signed integer primitive.", "MCQ"),
                q("R1-Q2", "What is the default value of an instance int field?", new String[]{"null", "0", "-1", "undefined"}, "0", "Primitive int fields default to 0.", "MCQ"),
                q("R1-Q3", "Which declaration is valid for a constant in Java?", new String[]{"const int x=1;", "final int x=1;", "static int x=1;", "immutable int x=1;"}, "final int x=1;", "Use final to make a variable non-reassignable.", "MCQ"),
                q("R1-Q4", "CODE: Write a one-line declaration for a double variable named price with value 19.99.", null, "double price = 19.99;", "A double literal with decimal point is assigned to price.", "CODE"),
                q("R1-Q5", "CODE: Write a one-line declaration of a String variable named user with value Alex.", null, "String user = \"Alex\";", "String literals must be wrapped in double quotes.", "CODE")
        ));

        Room room2 = new Room(2, "Operators & Expressions", "Easy", "Understand arithmetic, relational, and logical operators.", List.of(
                q("R2-Q1", "What is the result of 7 % 4?", new String[]{"1", "2", "3", "0"}, "3", "The remainder after dividing 7 by 4 is 3.", "MCQ"),
                q("R2-Q2", "Which operator checks both value and type-safe equality for primitives?", new String[]{"=", "==", "!=", "equals()"}, "==", "For primitives, == compares values directly.", "MCQ"),
                q("R2-Q3", "What is the value of x after int x = 5; x += 3;?", new String[]{"8", "53", "2", "5"}, "8", "Compound assignment adds and stores back.", "MCQ"),
                q("R2-Q4", "CODE: Write an expression that is true only when age is between 18 and 60 inclusive.", null, "age >= 18 && age <= 60", "Use logical AND to combine both range checks.", "CODE"),
                q("R2-Q5", "CODE: Write one line to increment variable count by 1.", null, "count++;", "Post-increment increases count by one.", "CODE")
        ));

        Room room3 = new Room(3, "Control Flow (if/switch)", "Easy", "Branch execution using if-else and switch.", List.of(
                q("R3-Q1", "Which keyword handles the default branch in a switch statement?", new String[]{"else", "default", "case else", "fallback"}, "default", "default runs when no case matches.", "MCQ"),
                q("R3-Q2", "What happens if break is missing in a traditional switch case?", new String[]{"Compilation fails", "Case repeats", "Fall-through to next case", "Program exits"}, "Fall-through to next case", "Without break, control continues to the next case.", "MCQ"),
                q("R3-Q3", "Which condition style is valid in Java if?", new String[]{"if x > 5", "if (x > 5)", "if [x > 5]", "if {x > 5}"}, "if (x > 5)", "Java requires parentheses around the boolean expression.", "MCQ"),
                q("R3-Q4", "CODE: Write an if statement that prints \"Pass\" when score >= 40.", null, "if (score >= 40) System.out.println(\"Pass\");", "Single-line if can omit braces for one statement.", "CODE"),
                q("R3-Q5", "CODE: Write a switch case for value 1 that prints \"One\" and breaks.", null, "case 1 -> System.out.println(\"One\");", "Java 17 switch arrow labels avoid explicit break.", "CODE")
        ));

        Room room4 = new Room(4, "Loops", "Medium", "Repeat logic using for, while, and do-while loops.", List.of(
                q("R4-Q1", "Which loop always executes at least once?", new String[]{"for", "while", "do-while", "enhanced for"}, "do-while", "do-while checks the condition after the first iteration.", "MCQ"),
                q("R4-Q2", "What does continue do inside a loop?", new String[]{"Stops loop fully", "Skips current iteration", "Restarts program", "Throws exception"}, "Skips current iteration", "continue jumps to the next iteration.", "MCQ"),
                q("R4-Q3", "How many times does for(int i=0;i<3;i++) run?", new String[]{"2", "3", "4", "Infinite"}, "3", "i takes values 0,1,2.", "MCQ"),
                q("R4-Q4", "CODE: Write a for loop header that iterates i from 1 to 5 inclusive.", null, "for (int i = 1; i <= 5; i++)", "Initialization, condition, and update define loop bounds.", "CODE"),
                q("R4-Q5", "CODE: Write a while loop condition that continues while items are left.", null, "while (itemsLeft > 0)", "A while loop runs while its condition stays true.", "CODE")
        ));

        Room room5 = new Room(5, "Arrays", "Medium", "Store ordered values of the same type.", List.of(
                q("R5-Q1", "What is the first valid index of a Java array?", new String[]{"-1", "0", "1", "Depends on length"}, "0", "Arrays are zero-indexed in Java.", "MCQ"),
                q("R5-Q2", "How do you get array length?", new String[]{"arr.length()", "arr.size()", "arr.length", "length(arr)"}, "arr.length", "Array length is a field, not a method.", "MCQ"),
                q("R5-Q3", "Which exception occurs when accessing an invalid array index?", new String[]{"NullPointerException", "IndexOutOfBoundsException", "ArrayIndexOutOfBoundsException", "ClassCastException"}, "ArrayIndexOutOfBoundsException", "Java throws this runtime exception for invalid array indices.", "MCQ"),
                q("R5-Q4", "CODE: Declare an int array of size 4 named marks.", null, "int[] marks = new int[4];", "Array declaration includes type and allocated size.", "CODE"),
                q("R5-Q5", "CODE: Assign value 99 to the third element of scores array.", null, "scores[2] = 99;", "Third element uses index 2 because indexing starts at 0.", "CODE")
        ));

        Room room6 = new Room(6, "Methods & Recursion", "Medium", "Create reusable behavior with parameters and return values.", List.of(
                q("R6-Q1", "Which keyword returns a value from a method?", new String[]{"yield", "return", "send", "break"}, "return", "return exits the method and optionally provides a value.", "MCQ"),
                q("R6-Q2", "What is required to stop recursion?", new String[]{"Loop variable", "Base case", "Static keyword", "Try-catch"}, "Base case", "A base case prevents infinite recursive calls.", "MCQ"),
                q("R6-Q3", "Can method overloading use same name with different parameters?", new String[]{"No", "Only in interfaces", "Yes", "Only for static methods"}, "Yes", "Overloading is based on parameter list differences.", "MCQ"),
                q("R6-Q4", "CODE: Write a method signature for adding two ints and returning int.", null, "public int add(int a, int b)", "Method signature defines visibility, return type, name, and parameters.", "CODE"),
                q("R6-Q5", "CODE: Write a base case line for factorial where n <= 1 returns 1.", null, "if (n <= 1) return 1;", "This base case stops recursion for smallest valid inputs.", "CODE")
        ));

        Room room7 = new Room(7, "Classes & Objects", "Medium", "Model real-world entities using classes and instances.", List.of(
                q("R7-Q1", "What does new keyword do?", new String[]{"Deletes object", "Creates object instance", "Imports class", "Calls static block only"}, "Creates object instance", "new allocates memory and invokes a constructor.", "MCQ"),
                q("R7-Q2", "Which member belongs to all instances equally?", new String[]{"instance field", "local variable", "static field", "parameter"}, "static field", "static members belong to the class, not individual objects.", "MCQ"),
                q("R7-Q3", "this keyword inside an object refers to?", new String[]{"Parent class", "Current object", "Main method", "Package"}, "Current object", "this points to the current instance.", "MCQ"),
                q("R7-Q4", "CODE: Create a Person object named p using default constructor.", null, "Person p = new Person();", "The statement creates a new Person instance.", "CODE"),
                q("R7-Q5", "CODE: Write one line to access object's field name from object p.", null, "p.name", "Dot operator accesses members on an object reference.", "CODE")
        ));

        Room room8 = new Room(8, "Constructors", "Medium", "Initialize object state using constructors.", List.of(
                q("R8-Q1", "What is a constructor's return type?", new String[]{"void", "int", "No return type", "Class<?>"},
                        "No return type", "Constructors do not declare any return type.", "MCQ"),
                q("R8-Q2", "When is a no-arg constructor provided automatically?", new String[]{"Always", "Never", "Only if no constructor is defined", "Only for final classes"},
                        "Only if no constructor is defined", "Defining any constructor suppresses automatic default constructor.", "MCQ"),
                q("R8-Q3", "Which keyword calls another constructor in the same class?", new String[]{"super", "this", "new", "base"}, "this", "this(...) chains constructors within the same class.", "MCQ"),
                q("R8-Q4", "CODE: Write constructor header for class Car with parameter String model.", null, "public Car(String model)", "Constructor name must match class name.", "CODE"),
                q("R8-Q5", "CODE: Inside a constructor, assign parameter age to field age.", null, "this.age = age;", "this.age targets the field while age refers to parameter.", "CODE")
        ));

        Room room9 = new Room(9, "Encapsulation", "Hard", "Protect state using access modifiers and controlled methods.", List.of(
                q("R9-Q1", "Best access level for fields in encapsulation?", new String[]{"public", "protected", "private", "default"}, "private", "Private fields hide internal state from external classes.", "MCQ"),
                q("R9-Q2", "Getter methods primarily provide?", new String[]{"Inheritance", "Controlled read access", "Thread locking", "Constructor chaining"}, "Controlled read access", "Getters expose values without exposing fields directly.", "MCQ"),
                q("R9-Q3", "Why validate data inside setters?", new String[]{"For faster compile time", "To enforce object invariants", "To reduce memory", "To avoid imports"}, "To enforce object invariants", "Validation keeps objects in valid states.", "MCQ"),
                q("R9-Q4", "CODE: Write getter method signature for private String email.", null, "public String getEmail()", "Getter returns the field type and has no parameters.", "CODE"),
                q("R9-Q5", "CODE: In setter, reject blank input for name.", null, "if (name == null || name.isBlank()) throw new IllegalArgumentException(\"name cannot be blank\");", "Input validation prevents invalid state from being stored.", "CODE")
        ));

        Room room10 = new Room(10, "Inheritance", "Hard", "Reuse behavior by extending base classes.", List.of(
                q("R10-Q1", "Which keyword creates inheritance between classes?", new String[]{"implements", "inherits", "extends", "super"}, "extends", "A subclass extends a superclass.", "MCQ"),
                q("R10-Q2", "What does super() call?", new String[]{"Current constructor", "Parent constructor", "Static method", "Interface default method"}, "Parent constructor", "super() invokes a superclass constructor.", "MCQ"),
                q("R10-Q3", "Can Java class extend multiple classes?", new String[]{"Yes", "No", "Only abstract classes", "Only final classes"}, "No", "Java supports single class inheritance.", "MCQ"),
                q("R10-Q4", "CODE: Declare class Dog inheriting Animal.", null, "class Dog extends Animal", "extends declares class inheritance.", "CODE"),
                q("R10-Q5", "CODE: Inside Dog constructor, call parent constructor with name.", null, "super(name);", "super(...) delegates initialization to superclass.", "CODE")
        ));

        Room room11 = new Room(11, "Polymorphism", "Hard", "Use one interface for many implementations.", List.of(
                q("R11-Q1", "Runtime polymorphism in Java is achieved by?", new String[]{"Method overloading", "Method overriding", "Generics only", "Packages"}, "Method overriding", "Overridden methods are selected at runtime via dynamic dispatch.", "MCQ"),
                q("R11-Q2", "Animal a = new Dog(); a.bark(); causes?", new String[]{"Always compile and run", "Compile error if bark not in Animal type", "Runtime cast automatically", "No method lookup"}, "Compile error if bark not in Animal type", "Reference type controls accessible methods at compile time.", "MCQ"),
                q("R11-Q3", "Which principle lets same method call behave differently by object type?", new String[]{"Encapsulation", "Abstraction", "Polymorphism", "Composition"}, "Polymorphism", "Different subclass implementations execute through common API.", "MCQ"),
                q("R11-Q4", "CODE: Write polymorphic assignment where Shape refers to Circle object.", null, "Shape s = new Circle();", "Superclass/interface reference can point to subclass object.", "CODE"),
                q("R11-Q5", "CODE: Override method draw in subclass using annotation line.", null, "@Override", "Override annotation helps verify method signature correctness.", "CODE")
        ));

        Room room12 = new Room(12, "Abstract Classes & Interfaces", "Hard", "Define contracts and partial implementations.", List.of(
                q("R12-Q1", "Can abstract class be instantiated directly?", new String[]{"Yes", "No", "Only once", "Only with static factory"}, "No", "Abstract classes require concrete subclasses.", "MCQ"),
                q("R12-Q2", "Which keyword implements an interface?", new String[]{"extends", "implements", "inherits", "realizes"}, "implements", "Classes use implements to satisfy interface contracts.", "MCQ"),
                q("R12-Q3", "Interface methods are public and abstract by default?", new String[]{"True", "False", "Only in Java 8", "Only for functional interfaces"}, "True", "Regular interface methods are implicitly public abstract unless default/static/private.", "MCQ"),
                q("R12-Q4", "CODE: Declare interface named Payable with method pay returning void.", null, "interface Payable { void pay(); }", "Interface declares behavior contracts.", "CODE"),
                q("R12-Q5", "CODE: Declare abstract method calculate in abstract class returning double.", null, "public abstract double calculate();", "Abstract methods define required subclass behavior.", "CODE")
        ));

        Room room13 = new Room(13, "Exception Handling", "Expert", "Handle runtime errors safely with try-catch-finally.", List.of(
                q("R13-Q1", "Which block always executes (except JVM crash/system halt)?", new String[]{"try", "catch", "finally", "throw"}, "finally", "finally is designed for cleanup logic.", "MCQ"),
                q("R13-Q2", "Checked exceptions are validated by?", new String[]{"JIT", "Compiler", "Garbage collector", "Runtime only"}, "Compiler", "Compiler enforces catch or throws for checked exceptions.", "MCQ"),
                q("R13-Q3", "Which keyword explicitly raises an exception object?", new String[]{"throws", "throw", "catch", "final"}, "throw", "throw creates an exception flow at runtime.", "MCQ"),
                q("R13-Q4", "CODE: Throw IllegalArgumentException when amount is negative.", null, "if (amount < 0) throw new IllegalArgumentException(\"amount must be non-negative\");", "Validate and fail fast for invalid input.", "CODE"),
                q("R13-Q5", "CODE: Catch IOException with variable e.", null, "catch (IOException e)", "Catch clause type and variable capture thrown exception.", "CODE")
        ));

        Room room14 = new Room(14, "Collections Framework", "Expert", "Work with List, Set, Map, and utility operations.", List.of(
                q("R14-Q1", "Which collection does not allow duplicate elements?", new String[]{"List", "Queue", "Set", "ArrayList"}, "Set", "Set enforces uniqueness.", "MCQ"),
                q("R14-Q2", "Which implementation preserves insertion order for key-value pairs?", new String[]{"HashMap", "TreeMap", "LinkedHashMap", "Hashtable"}, "LinkedHashMap", "LinkedHashMap maintains insertion order.", "MCQ"),
                q("R14-Q3", "Best average time for HashMap get?", new String[]{"O(n)", "O(log n)", "O(1)", "O(n log n)"}, "O(1)", "HashMap lookup is constant time on average.", "MCQ"),
                q("R14-Q4", "CODE: Create mutable list of strings with values A and B.", null, "List<String> list = new ArrayList<>(List.of(\"A\", \"B\"));", "Wrap List.of into ArrayList to allow modification.", "CODE"),
                q("R14-Q5", "CODE: Put key id with value 10 into map scores.", null, "scores.put(\"id\", 10);", "put inserts or updates a map entry.", "CODE")
        ));

        Room room15 = new Room(15, "Generics", "Expert", "Write type-safe reusable classes and methods.", List.of(
                q("R15-Q1", "Why use generics?", new String[]{"Reduce runtime", "Type safety and less casting", "Avoid classes", "Enable reflection only"}, "Type safety and less casting", "Generics catch type errors at compile time.", "MCQ"),
                q("R15-Q2", "Which declaration is type-safe for integers?", new String[]{"List list = new ArrayList();", "List<int> nums = new ArrayList<>();", "List<Integer> nums = new ArrayList<>();", "ArrayList<Number> nums = new List<>();"}, "List<Integer> nums = new ArrayList<>();", "Generics require reference types like Integer, not primitive int.", "MCQ"),
                q("R15-Q3", "Wildcard ? extends Number means?", new String[]{"Only Number exact", "Any subtype of Number", "Any supertype of Number", "No types allowed"}, "Any subtype of Number", "? extends sets an upper bound.", "MCQ"),
                q("R15-Q4", "CODE: Write generic method header returning same type T from parameter value.", null, "public static <T> T identity(T value)", "Type parameter T is declared before return type.", "CODE"),
                q("R15-Q5", "CODE: Declare list of strings named names using diamond operator.", null, "List<String> names = new ArrayList<>();", "Diamond operator infers generic type from left side.", "CODE")
        ));

        Room room16 = new Room(16, "Lambda & Streams", "Expert", "Use functional style operations on collections.", List.of(
                q("R16-Q1", "Which interface is commonly used for lambda with one input and one output?", new String[]{"Runnable", "Predicate", "Function", "Consumer"}, "Function", "Function<T,R> transforms input to output.", "MCQ"),
                q("R16-Q2", "What does stream().filter(...) return?", new String[]{"boolean", "new filtered Stream", "List always", "void"}, "new filtered Stream", "filter is intermediate and returns a stream.", "MCQ"),
                q("R16-Q3", "Which terminal operation collects stream elements into a list?", new String[]{"map", "sorted", "collect", "peek"}, "collect", "collect gathers elements into a container.", "MCQ"),
                q("R16-Q4", "CODE: Lambda for checking if n is even using Predicate<Integer>.", null, "n -> n % 2 == 0", "Lambda body returns true for even numbers.", "CODE"),
                q("R16-Q5", "CODE: Stream line to count strings longer than 3 in list words.", null, "long count = words.stream().filter(w -> w.length() > 3).count();", "Stream filter and count compute matching element total.", "CODE")
        ));

        Room room17 = new Room(17, "Multithreading", "Expert", "Run tasks concurrently and coordinate shared state.", List.of(
                q("R17-Q1", "Which method starts a new thread execution?", new String[]{"run()", "start()", "execute()", "init()"}, "start()", "start creates a new call stack and invokes run asynchronously.", "MCQ"),
                q("R17-Q2", "synchronized keyword helps with?", new String[]{"Serialization", "Thread safety for shared data", "Faster IO", "Garbage collection"}, "Thread safety for shared data", "synchronized prevents race conditions in critical sections.", "MCQ"),
                q("R17-Q3", "Which class provides a higher-level thread pool API?", new String[]{"ThreadGroup", "Executors", "System", "Math"}, "Executors", "Executors factory methods create managed thread pools.", "MCQ"),
                q("R17-Q4", "CODE: Create and start thread t with lambda printing Hi.", null, "Thread t = new Thread(() -> System.out.println(\"Hi\")); t.start();", "Thread runs lambda in parallel when start is called.", "CODE"),
                q("R17-Q5", "CODE: Method signature to sleep current thread for ms with checked exception.", null, "Thread.sleep(ms);", "Thread.sleep pauses current thread and may throw InterruptedException.", "CODE")
        ));

        Room room18 = new Room(18, "File I/O", "Expert", "Read and write files using modern Java APIs.", List.of(
                q("R18-Q1", "Which package contains Files and Paths utilities?", new String[]{"java.io", "java.nio.file", "java.net", "java.util.concurrent"}, "java.nio.file", "NIO file APIs are in java.nio.file.", "MCQ"),
                q("R18-Q2", "Which method reads all lines from a text file into List<String>?", new String[]{"Files.read()", "Files.readAllLines()", "Files.scan()", "Path.readLines()"}, "Files.readAllLines()", "Files.readAllLines loads file content as list of lines.", "MCQ"),
                q("R18-Q3", "Try-with-resources is used to?", new String[]{"Optimize CPU", "Auto-close resources", "Create threads", "Parse JSON"}, "Auto-close resources", "Resources implementing AutoCloseable close automatically.", "MCQ"),
                q("R18-Q4", "CODE: Build Path for file data.txt in current folder.", null, "Path path = Path.of(\"data.txt\");", "Path.of creates a path in a platform-independent way.", "CODE"),
                q("R18-Q5", "CODE: Write string \"Hello\" to file path using Files API.", null, "Files.writeString(path, \"Hello\");", "writeString writes text content to the target path.", "CODE")
        ));

        Collections.addAll(
                ALL_ROOMS,
                room1,
                room2,
                room3,
                room4,
                room5,
                room6,
                room7,
                room8,
                room9,
                room10,
                room11,
                room12,
                room13,
                room14,
                room15,
                room16,
                room17,
                room18
        );

        for (Room r : ALL_ROOMS) {
            for (Question q : r.getQuestions()) {
                Q_MAP.put(q.getId(), q);
            }
        }
    }

    private static Question q(String id, String text, String[] options, String correctAnswer, String explanation, String type) {
        return new Question(id, text, options, correctAnswer, explanation, type);
    }
}
