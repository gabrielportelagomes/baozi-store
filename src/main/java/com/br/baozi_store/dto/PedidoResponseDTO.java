package com.br.baozi_store.dto;

public class PedidoResponseDTO {

    private Long id;
    private Long clienteId;
    private Long produtoId;
    private Integer quantidade;

    public PedidoResponseDTO(Long id, Long clienteId, Long produtoId, Integer quantidade) {
        this.id = id;
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}

