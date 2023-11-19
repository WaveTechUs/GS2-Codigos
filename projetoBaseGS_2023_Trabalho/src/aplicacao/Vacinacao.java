package aplicacao;

import java.util.Stack;

import arvores.AVLCidade;
import arvores.AVLCidade.ARVORE;
import cidades.Cidade;

public class Vacinacao {

	public static void main(String[] args) {
		
		/*
		 * Os vetores criados s�o para que realizar testes solicitados 
		 */
		String cidades[]   = {"Analandia","Araraquara","Dourado","Ibitinga","Matao","S�o Carlos","Tabatinga"};
		double vacinacao[]   = {72.5, 88.4, 71.9, 76, 78.8, 96.7,66};
		int nCasos[]  = {1, 0, 2, 0, 0, 1, 1};
		
		AVLCidade avlCidade = new AVLCidade();
		
		/*
		 * 1) Insere na AVL organizada por nome da cidade
		 * 2) Usando um metodo da classe AVL gerar vetor de cidades com vacinacao menor do 80% e pelo menos 1 caso
		 * 	  Depois, ordenar vetor usando quicksort.
		 * 3) Gerar ABB percorrendo AVL, usando um metodo da classe AVL. ABB � organizada pela cobertura vacinal.
		 * 
		 */
		System.out.println("\n\n===========ARVORE AVL=========");
			for(int i = 0; i<cidades.length;i++) {
				avlCidade.rootAVL = avlCidade.inserirAVL(avlCidade.rootAVL, cidades[i], nCasos[i], vacinacao[i]);
			}
			avlCidade.mostra(avlCidade.rootAVL);

			System.out.println("\n\n===========VETOR=========");
			Cidade[] a = avlCidade.gerarLista(avlCidade.rootAVL);
			quicksort(a, 0, a.length-1);
			for(int j = 0; j<a.length; j++) {
				System.out.println("Cidade: "+ a[j].getNome() + " Porcentagem Vacina:" + a[j].getVacina() + "%") ;
			}
			
			
			System.out.println("\n\n===========ARVORE ABB=========");
			avlCidade.gerarABB(avlCidade.rootAVL);
			avlCidade.mostra(avlCidade.rootABB);
			
		}
	
	public static void quicksort(Cidade x[],int li,int ls)
	{
		int j;
		if (li<ls){
			j = particiona(x, li, ls);
			quicksort(x, li, j-1);
			quicksort(x, j+1,ls);
		}
	}
	public static int particiona (Cidade x[], int li, int ls)
	{
		int abaixo,acima;
		Cidade pivo,temp;
		pivo=x[ls];
		acima=ls;
		abaixo=li;
		while(abaixo<acima)
		{
			while(x[abaixo].getVacina() < pivo.getVacina() && abaixo<ls) {
				abaixo++;
			}
			while (x[acima].getVacina() >= pivo.getVacina() && acima > abaixo) {
				acima--;
			}
			if (abaixo<acima){
				temp=x[abaixo];
				x[abaixo]=x[acima];
				x[acima]=temp;
			}
		}
		x[ls]=x[acima];
		x[acima]=pivo;
		return acima;
	}

}
