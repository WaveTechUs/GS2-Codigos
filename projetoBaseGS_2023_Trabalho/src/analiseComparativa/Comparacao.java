package analiseComparativa;

import java.awt.Robot;
import java.util.Iterator;
import java.util.Random;

import arvores.AVLint;
import cidades.Cidade;

public class Comparacao {
	public static int quickConta=0;
	public static void main(String[] args) {
		/*
		 * Tarefa 4
		 * classe de aplica��o onde s�o gerados muitos valores aleat�rios 
		 * que s�o tanto armazenados em um vetor como na ABBint.
		 */
		AVLint abbInt = new AVLint();
		
		/* Ao invés de recriar uma classe abb, apenas adicionei a método de inserição da
		 * ABB na classe AVLINT */

		
		testeArrayOrdenadoCrescente(1000);
		testeArrayOrdenadoDecrescente(1000);
		testeArrayAleatorio(10000);
		int array[] = {1, 2, 3, 4, 5, 6, 7, 8};
		testeFixo(array);
	}
	public static void quicksort(int x[],int li,int ls)
	{
		int j;
		if (li<ls){
			j = particiona(x, li, ls);
			quicksort(x, li, j-1);
			quicksort(x, j+1,ls);
		}
	}
	public static int particiona (int x[], int li, int ls)
	{
		int abaixo,acima;
		int pivo,temp;
		pivo=x[ls];
		acima=ls;
		abaixo=li;
		while(abaixo<acima)
		{
			while(x[abaixo]<=pivo && abaixo<ls) {
				quickConta++;
				abaixo++;
			}
			while (x[acima]>pivo && acima > abaixo) {
				quickConta++;
				acima--;
			}
			if (abaixo<acima) {
				temp=x[abaixo];
				x[abaixo]=x[acima];
				x[acima]=temp;
			}
		}
		
		x[ls]=x[acima];
		x[acima]=pivo;
		return acima;
	}
	
	public static void testeArrayOrdenadoCrescente(int tam) {
		int n[] = new int[tam];
		AVLint abbInt = new AVLint();
		abbInt.contaComparacao=0;
		quickConta = 0;
		for(int i =0; i<n.length; i++) {
			n[i]= i;
			abbInt.root = abbInt.inserirABB(abbInt.root, n[i]);
		}
		quicksort(n, 0, n.length-1);
		System.out.println("==========TESTE ORDENADO==========");
		testeShow(n, tam, abbInt.contaComparacao);
	}
	
	public static void testeArrayOrdenadoDecrescente(int tam){
		int n[] = new int[tam];
		int aux[] = new int[tam];
		AVLint abbInt = new AVLint();
		quickConta = 0;
		abbInt.contaComparacao=0;
		int j=tam;
		for(int i =0; i < tam; i++) {
			n[i] = j;
			aux[i] = j;
			abbInt.root = abbInt.inserirABB(abbInt.root, n[i]);
			j--;
		}
		quicksort(n, 0, n.length-1);
		System.out.println("==========TESTE DESCRECENTE========");
		testeShow(aux, tam, abbInt.contaComparacao);
	}
	
	public static void testeArrayAleatorio(int tam) {
		int n[] = new int[tam];
		int aux[] = new int[tam];
		AVLint abbInt = new AVLint();
		Random rand = new Random(); 
		abbInt.contaComparacao=0;
		quickConta = 0;
		for(int i =0; i < tam; i++) {
			n[i] = rand.nextInt()+1;
			aux[i] = n[i];
			abbInt.root = abbInt.inserirABB(abbInt.root, n[i]);
		}
		quicksort(n, 0, n.length-1);
		System.out.println("==========TESTE ALEATORIO========");
		testeShow(aux, tam, abbInt.contaComparacao);
	}
	
	public static void testeFixo(int n[]) {
		AVLint abbInt = new AVLint();
		abbInt.contaComparacao=0;
		quickConta = 0;
		for(int i =0; i<n.length; i++) {
			n[i]= i;
			abbInt.root = abbInt.inserirABB(abbInt.root, n[i]);
		}
		quicksort(n, 0, n.length-1);
				System.out.println("==========TESTE FIXO==========");
		testeShow(n, n.length, abbInt.contaComparacao);
	}
	
	public static void testeShow(int n[], int tam, int abbComparacao) {
		System.out.println("DADOS INSERIDOS: " + tam);
		System.out.print("CINCO PRIMEIROS DIGITOS: ");
		for(int k = 0; k<5; k++)
			System.err.print( n[k] + " ");
		System.err.println();
		System.out.println("COMPARACAO ABB: " + abbComparacao);
		System.out.println("COMPARACAO QUICK: " + quickConta);
		System.out.println("==================================");
	}
}
