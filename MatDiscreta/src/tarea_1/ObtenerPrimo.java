package tarea_1;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class ObtenerPrimo {

	public static final String TEXT_RESET = "\u001B[0m";
	public static final String TEXT_BLACK = "\u001B[30m";
	public static final String TEXT_RED = "\u001B[31m";
	
	public static void main(String[] args) {
		DecimalFormat formatea = new DecimalFormat("###,###.##");
		Integer ID_UCAM = 222441;
		Integer intentos = 1000000000;
		//BUSCANDO CON EL PRIMO MAS CERCANO
		Integer primoCercano = primoMasCercano(ID_UCAM, intentos);
		System.out.println("PRIMO MAS CERCANO: " + formatea.format(primoCercano));
		for(int i=1; i<intentos; i++) {
			Long prueba = Long.parseLong(ID_UCAM +""+ i);
			if(esValido(prueba, primoCercano)) {
				System.out.println("NUMERO VÁLIDO: " + formatea.format(prueba));
				System.out.println(" ");
				System.out.println(formatea.format(prueba) + " + " + formatea.format(primoCercano) + "^2 = " + formatea.format(fermat(prueba, primoCercano)) + "^2");
				System.out.println(" ");
				System.out.println(formatea.format(prueba) + " = " + formatea.format(fermat(prueba, primoCercano).intValue()-primoCercano) + " * " + formatea.format(fermat(prueba, primoCercano).intValue()+primoCercano));
				
				i = intentos;
			}
		}
		System.out.println(" ");
		System.out.println(" ");
		//BUSCANDO SOLO CON LOS PRIMOS MAYORES QUE EL IDUCAM
		Integer primoCercanoPorArriba = primoMasCercanoArriba(ID_UCAM, intentos);
		System.out.println("PRIMO MAS CERCANO POR ARRIBA: " + formatea.format(primoCercanoPorArriba));
		for(int i=1; i<intentos; i++) {
			Long prueba = Long.parseLong(ID_UCAM +""+ i);
			if(esValido(prueba, primoCercanoPorArriba)) {
				System.out.println("NUMERO VÁLIDO: " + formatea.format(prueba));
				System.out.println(" ");
				System.out.println(formatea.format(prueba) + " + " + formatea.format(primoCercanoPorArriba) + "^2 = " + formatea.format(fermat(prueba, primoCercanoPorArriba)) + "^2");
				i = intentos;
			}
		}		
	}
	
	public static boolean esValido(long n, Integer primo) {
		if(n%2==0) {
			return false;
		}
		for(int i=3; i<=19; i++) {
			if(n%i == 0) {
				return false;
			}
		}

		BigInteger primoCuadrado = new BigInteger(primo+"").pow(2);
		BigInteger resultado = new BigInteger(n+"");
		BigInteger buscado = raiz(resultado.add(primoCuadrado));
		
		BigInteger a = buscado.pow(2);
		BigInteger b = primoCuadrado;
		BigInteger c = a.subtract(b);

		return c.equals(resultado);
	}
	
	public static BigInteger raiz(BigInteger x) {
	    BigInteger a= BigInteger.ZERO.setBit(x.bitLength()/2);
	    BigInteger b= a;
	    while(true) {
	        BigInteger c = a.add(x.divide(a)).shiftRight(1);
	        if (c.equals(a) || c.equals(b))
	            return c;
	        b= a;
	        a= c;
	    }
	}
	
	public static BigInteger fermat(Long prueba, Integer primoCercano) {
		BigInteger primoCuadrado = new BigInteger(primoCercano+"").pow(2);
		BigInteger resultado = new BigInteger(prueba+"");
		return raiz(resultado.add(primoCuadrado));
	}
	
	public static Integer primoMasCercano(int n, int intentos) {
		Integer res = null;
		for(int i=1; i<intentos; i++) {
			if(esPrimo(n+i)) {
				res = n+i;
				i = intentos;
			}
			if(res==null && esPrimo(n-i) && i<n) {
				res = n-i;
				i = intentos;
			}
		}
		return res;
	}
	
	public static Integer primoMasCercanoArriba(int n, int intentos) {
		Integer res = null;
		for(int i=1; i<intentos; i++) {
			if(esPrimo(n+i)) {
				res = n+i;
				i = intentos;
			}
		}
		return res;
	}
	
	public static boolean esPrimo(int n) {
        if (n <= 1) {
        	return false;        	
        }
        for (int i = 2; i < n; i++) {
        	if (n % i == 0) {
        		return false;        	
        	}
        }
        return true;
    }
	
	public static boolean testMillerRabin(int n) {
        if (n <= 1) {
        	return false;        	
        }
        for (int i = 2; i < n; i++) {
        	if (n % i == 0) {        		
        		return false;        	
        	}
        }
        return true;
    }
}
