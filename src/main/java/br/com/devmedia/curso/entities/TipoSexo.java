package br.com.devmedia.curso.entities;

/**
 * TipoSexo
 */
public enum TipoSexo {

    FEMININO('F'),
    MASCULINO('M');

    private char desc;

    private TipoSexo(char desc) {
        this.desc = desc;
    }

    public char getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}