package aed.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    /** Constrói a lista de forma a que a ordem final seja a dos argumentos (add insere no início). */
    @SafeVarargs
    private static <T> LinkedList<T> listaCom(T... valores) {
        LinkedList<T> lista = new LinkedList<>();
        for (int i = valores.length - 1; i >= 0; i--) lista.add(valores[i]);
        return lista;
    }

    private static <T> List<T> conteudo(LinkedList<T> lista) {
        List<T> resultado = new ArrayList<>();
        Iterator<T> it = lista.iterator();
        while (it.hasNext()) resultado.add(it.next());
        return resultado;
    }

    @Test
    void novaLista_EstaVazia() {
        LinkedList<Integer> lista = new LinkedList<>();
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.size());
        assertFalse(lista.iterator().hasNext());
    }

    @Test
    void add_InsereNoInicio() {
        LinkedList<Integer> lista = new LinkedList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        assertEquals(List.of(3, 2, 1), conteudo(lista));
        assertEquals(3, lista.size());
    }

    @Test
    void remove_RemoveDoInicioEDevolveValor() {
        LinkedList<Integer> lista = listaCom(1, 2, 3);
        assertEquals(1, lista.remove());
        assertEquals(List.of(2, 3), conteudo(lista));
        assertEquals(2, lista.size());
    }

    @Test
    void remove_ListaVazia_DevolveNull() {
        assertNull(new LinkedList<Integer>().remove());
    }

    @Test
    void getESet() {
        LinkedList<String> lista = listaCom("a", "b", "c");
        assertEquals("b", lista.get(1));
        lista.set(2, "z");
        assertEquals(List.of("a", "b", "z"), conteudo(lista));
    }

    @Test
    void addAt_InsereNaPosicaoCerta() {
        LinkedList<Integer> lista = listaCom(1, 3);
        lista.addAt(0, 0);
        lista.addAt(2, 2);
        lista.addAt(4, 4);
        assertEquals(List.of(0, 1, 2, 3, 4), conteudo(lista));
    }

    @Test
    void addAt_AtualizaTamanho() {
        LinkedList<Integer> lista = listaCom(1, 3);
        lista.addAt(1, 2);
        assertEquals(3, lista.size());
    }

    @Test
    void removeAt_RemoveDaPosicaoCerta() {
        LinkedList<Integer> lista = listaCom(0, 1, 2, 3, 4);
        assertEquals(2, lista.removeAt(2));
        assertEquals(4, lista.removeAt(3));
        assertEquals(0, lista.removeAt(0));
        assertEquals(List.of(1, 3), conteudo(lista));
    }

    @Test
    void removeAt_AtualizaTamanho() {
        LinkedList<Integer> lista = listaCom(1, 2, 3);
        lista.removeAt(1);
        assertEquals(2, lista.size());
    }

    @Test
    void reverse_InverteOrdem() {
        LinkedList<Integer> lista = listaCom(1, 2, 3, 4);
        assertSame(lista, lista.reverse());
        assertEquals(List.of(4, 3, 2, 1), conteudo(lista));
    }

    @Test
    void reverse_ListaComUmOuZeroElementos_NaoAltera() {
        assertEquals(List.of(), conteudo(new LinkedList<Integer>().reverse()));
        assertEquals(List.of(7), conteudo(listaCom(7).reverse()));
    }

    @Test
    void shallowCopy_CopiaEstruturaMasPartilhaElementos() {
        StringBuilder partilhado = new StringBuilder("x");
        LinkedList<StringBuilder> original = listaCom(partilhado, new StringBuilder("y"));
        LinkedList<StringBuilder> copia = original.shallowCopy();

        assertEquals(original.size(), copia.size());
        assertSame(partilhado, copia.get(0));

        copia.remove();
        assertEquals(2, original.size());
        assertSame(partilhado, original.get(0));
    }

    @Test
    void iterator_RemoveNaoSuportado() {
        Iterator<Integer> it = listaCom(1).iterator();
        assertThrows(UnsupportedOperationException.class, it::remove);
    }
}
