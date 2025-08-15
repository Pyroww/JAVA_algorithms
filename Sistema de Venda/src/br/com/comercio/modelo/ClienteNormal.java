package br.com.comercio.modelo;

public class ClienteNormal extends Cliente {
    public ClienteNormal(int id, String nome) {
        super(id, nome);
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Normal";
    }
}