package aed.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FintListTest {

    private static FintList listaCom(int... valores) {
        FintList lista = new FintList();
        for (int v : valores) lista.add(v);
        return lista;
    }

    private static List<Integer> conteudo(FintList lista) {
        List<Integer> resultado = new ArrayList<>();
        for (int v : lista) resultado.add(v);
        return resultado;
    }

    @Test
    void novaLista_EstaVazia() {
        FintList lista = new FintList();
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.size());
        assertFalse(lista.iterator().hasNext());
    }

    @Test
    void add_InsereNoFimPorOrdem() {
        FintList lista = listaCom(1, 2, 3);
        assertEquals(List.of(1, 2, 3), conteudo(lista));
        assertEquals(3, lista.size());
        assertEquals(1, lista.getFirst());
        assertEquals(3, lista.get());
    }

    @Test
    void add_AlemDaCapacidadeInicial_CresceEMantemElementos() {
        FintList lista = new FintList();
        List<Integer> esperado = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            lista.add(i);
            esperado.add(i);
        }
        assertEquals(esperado, conteudo(lista));
        assertEquals(100, lista.size());
        assertEquals(57, lista.get(57));
    }

    @Test
    void get_IndiceInvalido_LancaExcecao() {
        FintList lista = listaCom(1, 2);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(2));
    }

    @Test
    void getFirstEGet_ListaVazia_LancamExcecao() {
        FintList lista = new FintList();
        assertThrows(IndexOutOfBoundsException.class, lista::getFirst);
        assertThrows(IndexOutOfBoundsException.class, lista::get);
    }

    @Test
    void set_AlteraValorNoIndice() {
        FintList lista = listaCom(1, 2, 3);
        lista.set(1, 20);
        assertEquals(List.of(1, 20, 3), conteudo(lista));
    }

    @Test
    void addAt_InicioMeioFim() {
        FintList lista = listaCom(2, 4);
        lista.addAt(0, 1);
        lista.addAt(2, 3);
        lista.addAt(4, 5);
        assertEquals(List.of(1, 2, 3, 4, 5), conteudo(lista));
        assertEquals(5, lista.size());
        assertEquals(1, lista.getFirst());
        assertEquals(5, lista.get());
    }

    @Test
    void addAt_IndiceInvalido_LancaExcecao() {
        FintList lista = listaCom(1, 2);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.addAt(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.addAt(3, 0));
    }

    @Test
    void remove_RemoveDoFimEDevolveValor() {
        FintList lista = listaCom(1, 2, 3);
        assertEquals(3, lista.remove());
        assertEquals(List.of(1, 2), conteudo(lista));
        assertEquals(2, lista.get());
    }

    @Test
    void remove_UnicoElemento_FicaVazia() {
        FintList lista = listaCom(7);
        assertEquals(7, lista.remove());
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.size());
    }

    @Test
    void remove_ListaVazia_LancaExcecao() {
        assertThrows(IndexOutOfBoundsException.class, () -> new FintList().remove());
    }

    @Test
    void removeAt_InicioMeioFim() {
        FintList lista = listaCom(1, 2, 3, 4, 5);
        assertEquals(1, lista.removeAt(0));
        assertEquals(3, lista.removeAt(1));
        assertEquals(5, lista.removeAt(2));
        assertEquals(List.of(2, 4), conteudo(lista));
        assertEquals(2, lista.size());
        assertEquals(2, lista.getFirst());
        assertEquals(4, lista.get());
    }

    @Test
    void removeItem_RemovePrimeiraOcorrencia() {
        FintList lista = listaCom(1, 2, 3, 2);
        assertTrue(lista.remove((int) 2));
        assertEquals(List.of(1, 3, 2), conteudo(lista));
        assertFalse(lista.remove((int) 9));
    }

    @Test
    void add_DepoisDeRemocoes_ReutilizaPosicoesLivres() {
        FintList lista = listaCom(1, 2, 3, 4);
        lista.removeAt(1);
        lista.removeAt(1);
        lista.add(5);
        lista.add(6);
        lista.addAt(1, 7);
        assertEquals(List.of(1, 7, 4, 5, 6), conteudo(lista));
        assertEquals(5, lista.size());
    }

    @Test
    void indexOfEContains() {
        FintList lista = listaCom(5, 6, 7, 6);
        assertEquals(1, lista.indexOf(6));
        assertEquals(-1, lista.indexOf(9));
        assertTrue(lista.contains(7));
        assertFalse(lista.contains(9));
    }

    @Test
    void reverse_InverteOrdemEExtremos() {
        FintList lista = listaCom(1, 2, 3, 4);
        lista.reverse();
        assertEquals(List.of(4, 3, 2, 1), conteudo(lista));
        assertEquals(4, lista.getFirst());
        assertEquals(1, lista.get());
        assertEquals(3, lista.get(1));
    }

    @Test
    void reverse_SeguidoDeAdd_AdicionaNoNovoFim() {
        FintList lista = listaCom(1, 2, 3);
        lista.reverse();
        lista.add(0);
        assertEquals(List.of(3, 2, 1, 0), conteudo(lista));
    }

    @Test
    void reverse_ListaVazia_LancaExcecao() {
        assertThrows(IndexOutOfBoundsException.class, () -> new FintList().reverse());
    }

    @Test
    void map_AplicaOperadorATodos() {
        FintList lista = listaCom(1, 2, 3);
        lista.map(x -> x * x);
        assertEquals(List.of(1, 4, 9), conteudo(lista));
    }

    @Test
    void map_OperadorNulo_LancaExcecao() {
        FintList lista = listaCom(1);
        assertThrows(NullPointerException.class, () -> lista.map(null));
    }

    @Test
    void reduce_SomaEProduto() {
        FintList lista = listaCom(1, 2, 3, 4);
        assertEquals(10, lista.reduce(Integer::sum, 0));
        assertEquals(24, lista.reduce((a, b) -> a * b, 1));
    }

    @Test
    void reduce_ListaVazia_DevolveValorPorOmissao() {
        assertEquals(42, new FintList().reduce(Integer::sum, 42));
    }

    @Test
    void deepCopy_EIndependenteDoOriginal() {
        FintList original = listaCom(1, 2, 3);
        FintList copia = original.deepCopy();
        copia.set(0, 100);
        copia.add(4);
        assertEquals(List.of(1, 2, 3), conteudo(original));
        assertEquals(List.of(100, 2, 3, 4), conteudo(copia));
    }

    @Test
    void deepCopy_ListaVazia_DevolveListaVazia() {
        assertTrue(new FintList().deepCopy().isEmpty());
    }

    @Test
    void iterator_SemMaisElementos_LancaExcecao() {
        Iterator<Integer> it = listaCom(1).iterator();
        assertEquals(1, it.next());
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void forEach_PercorreTodosPorOrdem() {
        List<Integer> vistos = new ArrayList<>();
        listaCom(3, 1, 2).forEach(vistos::add);
        assertEquals(List.of(3, 1, 2), vistos);
    }

    /**
     * Executa uma sequência aleatória (com semente fixa) de operações em paralelo
     * numa FintList e numa ArrayList, verificando que o conteúdo coincide.
     * Exercita a cache de posição (lastUsedNode/lastArrayPosition) e a reutilização
     * de posições livres.
     */
    @Test
    void operacoesAleatorias_ComportamentoIgualAArrayList() {
        Random random = new Random(12345);
        FintList lista = new FintList();
        List<Integer> modelo = new ArrayList<>();

        for (int passo = 0; passo < 5000; passo++) {
            int op = random.nextInt(8);
            int valor = random.nextInt(1000);
            String contexto = "passo " + passo + ", operação " + op;

            if (op <= 1 || modelo.isEmpty()) {
                lista.add(valor);
                modelo.add(valor);
            } else if (op == 2) {
                int i = random.nextInt(modelo.size() + 1);
                lista.addAt(i, valor);
                modelo.add(i, valor);
            } else if (op == 3) {
                int i = random.nextInt(modelo.size());
                assertEquals(modelo.remove(i), lista.removeAt(i), contexto);
            } else if (op == 4) {
                assertEquals(modelo.remove(modelo.size() - 1), lista.remove(), contexto);
            } else if (op == 5) {
                int i = random.nextInt(modelo.size());
                assertEquals(modelo.get(i), lista.get(i), contexto);
            } else if (op == 6) {
                int i = random.nextInt(modelo.size());
                lista.set(i, valor);
                modelo.set(i, valor);
            } else {
                lista.reverse();
                java.util.Collections.reverse(modelo);
            }

            assertEquals(modelo.size(), lista.size(), contexto);
            if (passo % 50 == 0) assertEquals(modelo, conteudo(lista), contexto);
        }
        assertEquals(modelo, conteudo(lista));
    }
}
