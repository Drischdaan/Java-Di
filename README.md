# Java-Di
IoC (Inversion of Control) library. Inspired by my other di library (typescript):
https://github.com/Honey-Smart-Home/honey-di


## Example (or clone this repo and execute the main class in the test package)

1. Create a IContainer instance like this:
```java
IContainer container = new Container();
```

2. Create an interface or normal class and create a class that implements the interface (if you use one):

```java
interface ILogger {
    void print(String msg);
}

class Logger implements ILogger {

    public void print(String msg) {
        System.out.println(msg);
    }

}
```

3. Register the class in the container like this:
```java
container.bind(ILogger.class).to(Logger.class).lifetime(new TransientLifetime());
```

4. Resolve it like this:
```java
ILogger logger = container.resolve(ILogger.class); // Should return a new instance of the Logger class
```

5. (Optional) Use the Inject Annotation to specify the type that gets injected or the class attribute that should get injected:
```java
class Test {

    @Inject
    private ILogger logger;


    public void printHelloWorld() {
        this.logger.print("Hello World");
    }


}
```

6. (Optional)  Bind the Test class to itself:
```java
container.bind(Test.class).to(Test.class).lifetime(new TransientLifetime());
```

7. (Optional) Remove the Inject annotation and use the InjectConstructor Annotation to specify the constructor that should be used to instantiate the class:
```java
class Test {

    private ILogger logger;

    @InjectConstructor
    public Test(ILogger logger) {
        this.logger = logger;
    }

    public void printHelloWorld() {
        this.logger.print("Hello World");
    }

}
```