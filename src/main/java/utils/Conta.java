package utils;

public class Conta {
    private static int contador = 1;
    private int numeroConta;
    private Cliente cliente;

    public Conta(Cliente cliente) {
        this.numeroConta = contador++;
        this.cliente = cliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
