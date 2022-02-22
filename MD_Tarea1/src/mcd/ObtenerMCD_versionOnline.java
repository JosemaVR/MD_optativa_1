package mcd;

import java.util.ArrayList;

public class ObtenerMCD_versionOnline {

	private static Integer contador = 0;
	
	public static void main(String[] args) {
        Integer a = 6;
        Integer b = 3;
        System.out.println("Vamos a calcular el máximo común divisor de " + a + " y " + b);
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
        	System.out.println("Ahora se calculará la igualdad de Bezout");
        	System.out.println("");
        	if(resultados.size()>2) {
        		contador = resultados.size()-2;
        		igualdadBezout(resultados);
        	} else {
        		System.out.println(b + " = " + originalA + " * 1 - " + originalB + " * 1");
        	}
        }
    }
    
    public static void igualdadBezout(ArrayList<ArrayList<Integer>> resultados) {
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

    	contador--;
    	if(contador>=0) {
    		igualdadBezout(resultados);
    	} else {    		
    		System.out.println(res.get(4) + " = " + res.get(0) + " * " + res.get(1) + " - " + res.get(2) + " * " + res.get(3));
    		System.out.println("");
    	}
    }
}