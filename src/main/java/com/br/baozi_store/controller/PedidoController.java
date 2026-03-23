package com.br.baozi_store.controller;

import com.br.baozi_store.dto.PedidoDTO;import com.br.baozi_store.dto.PedidoResponseDTO;import com.br.baozi_store.model.Cliente;import com.br.baozi_store.model.Pedido;
import com.br.baozi_store.model.Produto;import com.br.baozi_store.repository.ClienteRepository;import com.br.baozi_store.repository.PedidoRepository;
import com.br.baozi_store.repository.ProdutoRepository;import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository pedidoRepository,
                            ClienteRepository clienteRepository,
                            ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Pedido criar(@RequestBody PedidoDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElseThrow();
        Produto produto = produtoRepository.findById(dto.getProdutoId()).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(dto.getQuantidade());

        return pedidoRepository.save(pedido);
    }

    @GetMapping
    public List<PedidoResponseDTO> listar() {
        return pedidoRepository.findAll()
                .stream()
                .map(p -> new PedidoResponseDTO(
                        p.getId(),
                        p.getCliente().getId(),
                        p.getProduto().getId(),
                        p.getQuantidade()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO consultar(@PathVariable Long id) {
        Pedido p = pedidoRepository.findById(id).orElseThrow();

        return new PedidoResponseDTO(
                p.getId(),
                p.getCliente().getId(),
                p.getProduto().getId(),
                p.getQuantidade()
        );
    }

    @DeleteMapping("/{id}")
    public void apagar(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable Long id, @RequestBody PedidoDTO dto) {

        Pedido existente = pedidoRepository.findById(id).orElseThrow();

        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElseThrow();
        Produto produto = produtoRepository.findById(dto.getProdutoId()).orElseThrow();

        existente.setCliente(cliente);
        existente.setProduto(produto);
        existente.setQuantidade(dto.getQuantidade());

        return pedidoRepository.save(existente);
    }
}
