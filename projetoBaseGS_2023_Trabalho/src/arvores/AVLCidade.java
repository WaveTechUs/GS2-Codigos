package arvores;

import java.lang.reflect.Array;
import java.util.Arrays;

import cidades.Cidade;

public class AVLCidade {
		
		public class ARVORE {
			Cidade cidade;
			ARVORE esq, dir;
			int hEsq, hDir;
		}
		
		public ARVORE rootAVL = null;
		public ARVORE rootABB = null;

		
		public ARVORE inserirAVL(ARVORE p, String cNome, int cNumCasos, double cCoberturaVacina) {
			if(p == null) {
				p = new ARVORE();
				p.cidade = new Cidade(cNome, cNumCasos, cCoberturaVacina);
				p.esq= null;
				p.dir = null;
				p.hDir = 0;
				p.hEsq = 0;
			}else if (p.cidade.getNome().compareToIgnoreCase(cNome) > 0){
				p.esq =inserirAVL(p.esq, cNome, cNumCasos, cCoberturaVacina);
				if(p.esq.hDir> p.esq.hEsq)
					p.hEsq = p.esq.hDir +1;
				else
					p.hEsq = p.esq.hEsq +1;
			}
			else {
				p.dir = inserirAVL(p.dir, cNome, cNumCasos, cCoberturaVacina);
				if(p.dir.hDir > p.dir.hEsq)
					p.hDir = p.dir.hDir +1;
				else
					p.hDir = p.dir.hEsq +1;
			}
			p = balanceamento(p);
			
			return p;
		}
		public ARVORE inserirABB(ARVORE p, String cNome, int cNumCasos, double cCoberturaVacina) {
			// insere elemento em uma ABB
			if (p == null) {
				p = new ARVORE();
				p.cidade = new Cidade(cNome, cNumCasos, cCoberturaVacina);
				p.esq = null;
				p.dir = null;
			} else if (cCoberturaVacina < p.cidade.getVacina())
				p.esq = inserirABB(p.esq, cNome, cNumCasos, cCoberturaVacina);
			else
				p.dir = inserirABB(p.dir, cNome, cNumCasos, cCoberturaVacina);
			return p;
		}
		
		public ARVORE balanceamento(ARVORE p) {
			int FB = p.hDir - p.hEsq;
			if( FB >1) {
				int fbFilhoDir = p.dir.hDir - p.dir.hEsq;
				if(fbFilhoDir >=0) {
					p = rotacaoEsquerda(p);
				}
				else {
					p.dir = rotacaoDireita(p.dir);
					p = rotacaoEsquerda(p);
				}
			}
			else {
				if (FB < -1) {
					int fbFilhoEsq = p.esq.hDir - p.esq.hEsq;
					if(fbFilhoEsq <=0) {
						p = rotacaoDireita(p);
					}
					else {
						p.esq = rotacaoEsquerda(p.esq);
						p= rotacaoDireita(p);
					}
				}
			}
			return p;
		}
		
		public ARVORE rotacaoDireita(ARVORE p) {
			ARVORE q, temp;
			q = p.esq;
			temp = q.dir;
			q.dir = p;
			p.esq = temp;
			return q;
		}
		
		public ARVORE rotacaoEsquerda(ARVORE p) {
			ARVORE q, temp;
			q = p.dir;
			temp = q.esq;
			q.esq = p;
			p.dir = temp;
			return q;
		}
		
		public void mostra(ARVORE p) {
			if (p != null) {
				mostra(p.esq);
				System.out.println("Cidade: " + p.cidade.getNome() + "\t taxa Vacina: "+ p.cidade.getVacina() +"%    \t FB = " + (p.hDir - p.hEsq));
				mostra(p.dir);
			}
		}
		
		public int contaNos(ARVORE p, int cont) {
			if (p != null) {
				cont++;
				if (p.esq != null)
					cont = contaNos(p.esq, cont);
				if (p.dir != null)
					cont = contaNos(p.dir, cont);
			}
			return cont;
		}
		
		public Cidade[] gerarLista(ARVORE pAVL) {
			ARVORE[] sArray = gerarListaArvore(pAVL);
			Cidade[] cidades = new Cidade[sArray.length];
			for(int i =0; i< sArray.length; i++) {
				cidades[i] = sArray[i].cidade;
			}
			return cidades;
		}
		
		public void gerarABB( ARVORE pAVL) {
			ARVORE[] sArray = gerarListaArvore(pAVL);
			for(int i =0; i< sArray.length; i++) {
				rootABB = inserirABB(rootABB, sArray[i].cidade.getNome(), sArray[i].cidade.getCasos(), sArray[i].cidade.getVacina());
			}
		}
		
		public ARVORE[] gerarListaArvore(ARVORE p){
			int tamanho =contaNos(p, 0);
			ARVORE[] lista = new ARVORE[tamanho];
			int[] index = {0};
			gerarListaHelperArvore(p, lista, index);
			return Arrays.copyOf(lista, index[0]);
		}
		
		private void gerarListaHelperArvore(ARVORE p, ARVORE[] lista, int[] index) {
			if(p == null) {
				return;
			}
			gerarListaHelperArvore(p.esq, lista, index);
			if(p.cidade.getVacina() <= 80 && p.cidade.getCasos() > 0) 
				lista[index[0]++] = p;
			gerarListaHelperArvore(p.dir, lista, index);
		}
		
}
