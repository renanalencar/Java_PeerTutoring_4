
public class Fabricante implements Runnable {

    private Estoque estoque;

    public Fabricante(Estoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++) {
            this.estoque.colocar();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
