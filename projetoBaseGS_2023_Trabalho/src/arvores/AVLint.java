package arvores;

import cidades.Cidade;

public class AVLint {

	private class ARVORE {
		int dado;
		ARVORE esq, dir;
		int hEsq, hDir;
	}

	public ARVORE root = null;
	public int contaComparacao =0;

	public ARVORE removeValor(ARVORE p, int info) {
		if (p != null) {
			if (info == p.dado) {
				if (p.esq == null && p.dir == null) return null;
				if (p.esq == null) 	return p.dir;
				else if (p.dir == null) return p.esq;
				else {
					ARVORE aux, ref;
					ref = p.dir;
					aux = p.dir;
					while (aux.esq != null)
						aux = aux.esq;
					aux.esq = p.esq;
					return ref;
				}
			} else { // procura dado a ser removido na ABB
				if (info < p.dado) {
					p.esq = removeValor(p.esq, info);
					if (p.esq == null) p.hEsq = 0;
					else {
						if (p.esq.hDir > p.esq.hEsq) // Altura do n� ser� a maior
							p.hEsq = p.esq.hDir + 1; // altura dos seus filhos
						else
							p.hEsq = p.esq.hEsq + 1;
					}
					p = atualizaAlturaBalanceamento(p);
				} else {
					p.dir = removeValor(p.dir, info);
					if (p.dir == null) 	p.hDir = 0;
					else {
						if (p.dir.hDir > p.dir.hEsq) // Altura do n� ser� a maior
							p.hDir = p.esq.hDir + 1; // altura dos seus filhos
						else
							p.hDir = p.esq.hEsq + 1;
					}
					p = atualizaAlturaBalanceamento(p);
				}
			}
		}
		return p;
	}

	public int altura(ARVORE p) {
		if (p.hDir > p.hEsq)
			return p.hDir;
		else
			return p.hEsq;
	}

	public ARVORE inserirAVL(ARVORE p, int info) {
		if (p == null) { // n� inserido sempre ser� n� folha
			p = new ARVORE();
			p.dado = info;
			p.esq = null;
			p.dir = null;
			p.hDir = 0;
			p.hEsq = 0;
		} else if (p.dado > info) {
			contaComparacao++;
			p.esq = inserirAVL(p.esq, info);
			if (p.esq.hDir > p.esq.hEsq) // Altura do n� ser� a maior
				p.hEsq = p.esq.hDir + 1; // altura dos seus filhos
			else
				p.hEsq = p.esq.hEsq + 1;
		} else {
			contaComparacao++;
			p.dir = inserirAVL(p.dir, info);
			if (p.dir.hDir > p.dir.hEsq)
				p.hDir = p.dir.hDir + 1;
			else
				p.hDir = p.dir.hEsq + 1;
		}

		p = balanceamento(p);
		return p;
	}

	public ARVORE rotacaoDireita(ARVORE p) {
		// faz rota��o para direita em rela��o ao n� apontado por p
		ARVORE q, temp;
		q = p.esq;
		temp = q.dir;
		q.dir = p;
		p.esq = temp;
		return q;
	}

	public ARVORE rotacaoEsquerda(ARVORE p) {
		// faz rota��o para esquerda em rela��o ao n� apontado por p
		ARVORE q, temp;
		q = p.dir;
		temp = q.esq;
		q.esq = p;
		p.dir = temp;
		return q;
	}

	public ARVORE balanceamento(ARVORE p) {
		// analisa FB e realiza rota��es necess�rias para balancear �rvore
		int FB = p.hDir - p.hEsq;
		if (FB > 1) {
			int fbFilhoDir = p.dir.hDir - p.dir.hEsq;
			if (fbFilhoDir >= 0)
				p = rotacaoEsquerda(p);
			else {
				p.dir = rotacaoDireita(p.dir);
				p = rotacaoEsquerda(p);
			}
		} else {
			if (FB < -1) {
				int fbFilhoEsq = p.esq.hDir - p.esq.hEsq;
				if (fbFilhoEsq <= 0)
					p = rotacaoDireita(p);
				else {
					p.esq = rotacaoEsquerda(p.esq);
					p = rotacaoDireita(p);
				}
			}
		}
		return p;
	}

	public void atualizaAlturas(ARVORE p) {
		/*
		 * atualiza informa��o da altura de cada n� depois da remo��o percorre a �rvore
		 * usando percurso p�s-ordem para ajustar primeiro os n�s folhas (profundidade
		 * maior) e depois os n�veis acima
		 */
		if (p != null) {
			atualizaAlturas(p.esq);
			if (p.esq == null)
				p.hEsq = 0;
			else if (p.esq.hEsq > p.esq.hDir)
				p.hEsq = p.esq.hEsq + 1;
			else
				p.hEsq = p.esq.hDir + 1;
			atualizaAlturas(p.dir);
			if (p.dir == null)
				p.hDir = 0;
			else if (p.dir.hEsq > p.dir.hDir)
				p.hDir = p.dir.hEsq + 1;
			else
				p.hDir = p.dir.hDir + 1;
		}
	}

	public void mostraFB(ARVORE p) {
		if (p != null) {
			mostraFB(p.esq);
			mostraFB(p.dir);
			System.out.println("dado: " + p.dado + "    \t FB = " + (p.hDir - p.hEsq));
		}
	}
	public void mostra(ARVORE p) {
		if (p != null) {
			mostra(p.esq);
			System.out.println("dado: " + p.dado + "    \t FB = " + (p.hDir - p.hEsq));
			mostra(p.dir);
		}
	}
	public ARVORE atualizaAlturaBalanceamento(ARVORE p) {
		/*
		 * atualiza informa��o da altura de cada n� depois da remo��o percorre a �rvore
		 * usando percurso p�s-ordem para ajustar primeiro os n�s folhas (profundidade
		 * maior) e depois os n�veis acima
		 */
		if (p != null) {
			p.esq = atualizaAlturaBalanceamento(p.esq);
			if (p.esq == null)
				p.hEsq = 0;
			else if (p.esq.hEsq > p.esq.hDir)
				p.hEsq = p.esq.hEsq + 1;
			else
				p.hEsq = p.esq.hDir + 1;
			p.dir = atualizaAlturaBalanceamento(p.dir);
			if (p.dir == null)
				p.hDir = 0;
			else if (p.dir.hEsq > p.dir.hDir)
				p.hDir = p.dir.hEsq + 1;
			else
				p.hDir = p.dir.hDir + 1;
			p = balanceamento(p);
		}
		return p;
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
	
	public ARVORE inserirABB(ARVORE p, int info) {
		// insere elemento em uma ABB
		if (p == null) {
			p = new ARVORE();
			p.dado = info;
			p.esq = null;
			p.dir = null;
		} else if (info < p.dado) {
			contaComparacao++;
			p.esq = inserirABB(p.esq, info);
		}
		else {
			contaComparacao++;
			p.dir = inserirABB(p.dir, info);
		}
		return p;
	}
}
