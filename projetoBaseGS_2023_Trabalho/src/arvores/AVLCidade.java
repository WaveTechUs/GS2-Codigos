package arvores;

import java.lang.reflect.Array;
import java.util.Stack;

import cidades.Cidade;

public class AVLCidade {
		
		private class ARVORE {
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
			if(p == null) {
				p = new ARVORE();
				p.cidade = new Cidade(cNome, cNumCasos, cCoberturaVacina);
				p.esq= null;
				p.dir = null;
				p.hDir = 0;
				p.hEsq = 0;
			}else if (p.cidade.getVacina() > cCoberturaVacina){
				p.esq =inserirABB(p.esq, cNome, cNumCasos, cCoberturaVacina);
				if(p.esq.hDir> p.esq.hEsq)
					p.hEsq = p.esq.hDir +1;
				else
					p.hEsq = p.esq.hEsq +1;
			}
			else {
				p.dir = inserirABB(p.dir, cNome, cNumCasos, cCoberturaVacina);
				if(p.dir.hDir > p.dir.hEsq)
					p.hDir = p.dir.hDir +1;
				else
					p.hDir = p.dir.hEsq +1;
			}
			p = balanceamento(p);
			
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
				System.out.println("dado: " + p.cidade.getNome() + "    \t FB = " + (p.hDir - p.hEsq));
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
		
		public Stack<Cidade> gerarLista(ARVORE p){
			Stack<Cidade> lista = new Stack<Cidade>();
			gerarListaHelper(p, lista);
			return lista;
		}
		
		private void gerarListaHelper(ARVORE p, Stack<Cidade> lista) {
			if(p == null) {
				return;
			}
			gerarListaHelper(p.esq, lista);
			if(p.cidade.getVacina() <= 80 && p.cidade.getCasos() > 0) 
				lista.push(p.cidade);
			gerarListaHelper(p.dir, lista);
		}
		
		public void gerarABB( ARVORE pAVL) {
			Stack <ARVORE> sArray = gerarListaArvore(pAVL);
			for(int i =0; i< sArray.size(); i++) {
				rootABB = inserirABB(rootABB, sArray.get(i).cidade.getNome(), sArray.get(i).cidade.getCasos(), sArray.get(i).cidade.getVacina());
			}
		}
		
		public Stack<ARVORE> gerarListaArvore(ARVORE p){
			Stack<ARVORE> lista = new Stack<ARVORE>();
			gerarListaHelperArvore(p, lista);
			return lista;
		}
		
		private void gerarListaHelperArvore(ARVORE p, Stack<ARVORE> lista) {
			if(p == null) {
				return;
			}
			gerarListaHelperArvore(p.esq, lista);
			if(p.cidade.getVacina() <= 80 && p.cidade.getCasos() > 0) 
				lista.push(p);
			gerarListaHelperArvore(p.dir, lista);
		}
		
}
