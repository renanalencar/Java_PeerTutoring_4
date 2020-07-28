
public class Cliente implements Runnable {

    private Estoque estoque;

    public Cliente(Estoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        for (int i = 0; i < 10; i++) {
            this.estoque.retirar();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
