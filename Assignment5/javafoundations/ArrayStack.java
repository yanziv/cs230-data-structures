package javafoundations; 
import javafoundations.exceptions.*; 

/******************************************************************** 
 *  ArrayStack.java       
 *  @author Java Foundations 
 *  @version 2019.10.09 (TM)
 *  Represents an array implementation of a stack. The bottom of 
 *  the stack is kept at array index 0. 
 ********************************************************************/ 
public class ArrayStack<T> implements Stack<T> 
{ 
    private final int DEFAULT_CAPACITY = 10; 
    private int count; 
    private T[] array; 

    /** 
     * Constructor. Creates an empty stack using the default capacity. 
     */ 
    public ArrayStack() 
    { 
        count = 0; 
        array = (T[]) (new Object[DEFAULT_CAPACITY]); 
    } 

    /** 
     *  @param element The input element added to the top of this stack, expanding 
     *  the capacity of the stack array if necessary. 
     */ 
    public void push (T element) 
    { 
        if (count == array.length) 
            expandCapacity(); 

        array[count] = element; 
        count++; 
    } 

    /** 
     *  @return a string representation of this stack. 
     */ 
    public String toString() 
    { 
        String result = "<top of stack>\n"; 

        for (int index=count-1; index >= 0; index--) 
            result += array[index] + "\n"; 

        return result + "<bottom of stack>"; 
    } 

    /** 
     *  Helper method.  
     *  Creates a new array to store the contents of this stack with 
     *  twice the capacity of the old one. 
     */ 
    private void expandCapacity() 
    { 
        T[] larger = (T[])(new Object[array.length*2]); 

        for (int index=0; index < array.length; index++) 
            larger[index] = array[index]; 

        array = larger; 
    } 

    /** 
     *  Removes the element at the top of this stack and returns a 
     *  reference to it. 
     *  Throws an EmptyCollectionException if the stack contains no elements. 
     *  @return the removed element
     */ 
    public T pop () throws EmptyCollectionException  
    { 
        if(count == 0) 
            throw new EmptyCollectionException("Pop operation failed. Stack is empty."); 

        T temp = array[count - 1]; 
        count--; 
        return temp; 
    } 

    /** 
     *  @return the top without retrieving 
     */ 
    public T peek () throws EmptyCollectionException  
    { 
        if(count == 0) 
            throw new EmptyCollectionException("Peek operation failed. Stack is empty."); 

        return array[count - 1]; 
    } 

    /** 
     *  @return true iff stack is empty 
     */ 
    public boolean isEmpty()  
    { 
        return count == 0; 
    } 

    /** 
     *  @return the number of elements in the stack 
     */ 
    public int size()  
    { 
        return count; 
    } 
} 

