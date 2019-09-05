package br.com.devmedia.curso.entities;

import com.fasterxml.jackson.annotation.JsonValue;

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

    /**O @JsonValue permite que possamos passar o valor da constante, 
     * para salvar a informação de nossa enum
     * e ao apresentar o json, o mesmo apresenta o valor da constante e não pelo nome.
     * Assim podemos enviar o valor 'F' e o mesmo irá fazer o tratamento para a constante FEMININO */
    @JsonValue
    public char getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}