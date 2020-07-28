/**
 * Deposito
 */
public class Estoque {

    private int itens = 0;
    private final int capacidade = 10;
    private boolean disponivel = false;

    public synchronized int retirar() {
        try {
            while (!disponivel) {
                wait();
            }
            disponivel = false;
            if (itens > 0) {
                itens--;
                System.out.println("Caixa retirada: Restam " + itens + " caixas");
                notifyAll();
                return 1;
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public synchronized int colocar() {

        try {
            while (disponivel) {
                wait();
            }
            disponivel = true;
            if (itens < capacidade) {
                itens++;
                System.out.println("Caixa armazenada: passaram a ser " + itens + " caixas");
                notifyAll();
                return 1;
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Fabricante p = new Fabricante(estoque);
        Cliente c = new Cliente(estoque);

        // inicia o produtor
        Thread threadProdutor = new Thread(p);
        threadProdutor.start();

        // inicia o consumidor
        Thread threadConsumidor = new Thread(c);
        threadConsumidor.start();

        try {
            threadConsumidor.join();
            threadProdutor.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("Execução do programa concluída!"); 
    }
}