package mcd;

import java.util.ArrayList;
import java.util.Scanner;

public class ObtenerMCD {

	private static Integer contador = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el primer número: ");
        Integer a = sc.nextInt();
        System.out.print("Introduzca el segundo número: ");
        Integer b = sc.nextInt();
        sc.close();
        System.out.println("");
        System.out.println("Calculando mcd(" + a + ", " + b + ")");
        System.out.println("");
        ArrayList<ArrayList<Integer>> resultados = new ArrayList<ArrayList<Integer>>();
        if(a>b){
        	calcularMCD(a, b, a, b, resultados);
        } else {
        	calcularMCD(b, a, b, a, resultados);
        }
	}
   
    public static void calcularMCD(Integer a, Integer b, Integer originalA, Integer originalB, ArrayList<ArrayList<Integer>> resultados){
    	Integer resto = a%b;
        Integer cociente = a/b;
        System.out.println(a + " = " + b + "*" + cociente + " + " + resto);
        if(resto!=0){
        	// Guardando información para la igualdad de bezout
        	ArrayList<Integer> res = new ArrayList<Integer>();
        	res.add(a);
        	res.add(1);
        	res.add(b);
        	res.add(cociente);
        	res.add(resto);
        	resultados.add(res);
        	// Continuamos con el cálculo del MCD
        	calcularMCD(b, resto, originalA, originalB, resultados);
        } else {
        	System.out.println("");
        	System.out.println("mcd(" + originalA + ", " + originalB + ") = " + b);
        	System.out.println("");
        	System.out.println("");
        	System.out.println("Calculando igualdad de Bezout");
        	System.out.println("");
        	contador = resultados.size()-2;
        	igualdadBezout(resultados, b);
        }
    }
    
    public static void igualdadBezout(ArrayList<ArrayList<Integer>> resultados, Integer mcd) {
    	ArrayList<Integer> res = resultados.get(resultados.size()-1);
    	ArrayList<Integer> linea = resultados.get(contador);
    	 
    	System.out.println(res.get(4) + " = " + res.get(0) + " * " + res.get(1) + " - " + res.get(2) + " * " + res.get(3));
    	System.out.println(linea.get(4) + " = " + linea.get(0) + " * " + linea.get(1) + " - " + linea.get(2) + " * " + linea.get(3));
    	System.out.println("");
    	
    	Integer a = linea.get(0);
    	Integer b = res.get(3);
    	Integer c = res.get(0);
    	Integer d = res.get(1) + linea.get(3)*res.get(3);
    	
    	res.set(0, a);
    	res.set(1, b);
    	res.set(2, c);
    	res.set(3, d);
    	
    	System.out.println(res.get(4) + " = " + res.get(0) + " * " + res.get(1) + " - " + res.get(2) + " * " + res.get(3));
    	System.out.println("");

    	contador--;
    	if(contador>=0) {
    		igualdadBezout(resultados, mcd);
    	}
    }
}
