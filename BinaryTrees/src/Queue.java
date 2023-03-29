/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author coron
 */
import java.util.ArrayList;


public class Queue <T> implements QueueADT<T> {
    private T[] cola;
    private int primero;
    private int ultimo;
    private int MAXIMO = 20;
    
    public Queue() {
        cola = (T[]) new Object[MAXIMO];
        primero = -1;
        ultimo = -1;
    }
    
    public Queue(int max) {
        cola = (T[]) new Object[max];
        primero = -1;
        ultimo = -1;
    }

    
    @Override
    public void agrega(T dato) {
        if ((ultimo + 1)% cola.length == primero)   // cuando sale uno y entra otro a la fila. Pensarlo [ ,x,x,x]
            expande();
        
        ultimo = (ultimo + 1) % cola.length;
        cola[ultimo] = dato;
        
        if (primero == -1) // la cola está vacia 
            primero = 0;
    }
    
    private void expande(){
        int i, tam = cola.length;
        T[] nuevo = (T[]) new Object[tam * 2];
        i = primero;
        
        for (int j = 0; j < tam; j++) {
            nuevo[j] = cola[i % tam];
            i++;
        }
        
        cola = nuevo;
        primero = 0;
        ultimo = tam - 1;
    }

    @Override
    public T saca() {
        if (estaVacia())
            throw new RuntimeException();
        
        T eliminado = cola[primero];
        cola[primero] = null;
            
        if (primero == ultimo){ // hay un solo elemento
            primero = -1;   // indica cola vacia 
            ultimo = -1;
        } else 
            primero = (primero + 1) % cola.length;
        
        return eliminado;
    }

    @Override
    public T consultaPrimero() {
        if (!estaVacia())
            return cola[primero];
        
        throw new RuntimeException();
    }

    @Override
    public boolean estaVacia() {
        return primero == -1;
    }
    
    public String toString(){
        String respuesta = "";
        
        if (!this.estaVacia())
            respuesta = toString(primero, new StringBuilder());
        
        return respuesta; 
        
    }
    
    private String toString(int actual, StringBuilder sB){
        
        if (actual == ultimo + 1)
            return sB.toString();
        
        else {
            sB.append(cola[actual]).append("  ");
            return toString((actual + 1) % cola.length, sB);
        }
        
    }

    // a) cuentaElementos(): regresa el total de elementos almacenados en la cola.
    @Override
    public int cuentaElementos() {
        int res = 0;
        
        if (!this.estaVacia()) {            
            if (primero <= ultimo)
                res = ultimo - primero + 1;
            else 
                res = (cola.length - primero) + (ultimo + 1);
        }
            
        return res;
    }
    
    // b) consultaUltimo(): regresa el último elemento almacenado en la cola, sin quitarlo.
    @Override
    public T consultaUltimo() {
        if (!estaVacia())
            return cola[ultimo];
        
        throw new RuntimeException();
    }

    // c) multiQuita(int n): regresa un ArrayList almacenando los n elementos quitados de la cola.
    @Override
    public ArrayList<T> multiQuita(int n) {
        ArrayList<T> arr = new ArrayList();
        int total = this.cuentaElementos();
        
       if( n > total)
           throw new RuntimeException();
       else 
           for (int i=0; i < n ; i++)
               arr.add(this.saca());
       return arr;
    }
    
    
}