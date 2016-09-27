package com.ricardo.mello.controller;

import com.ricardo.mello.bean.Hotel;
import com.ricardo.mello.repository.HotelRepository;
import com.ricardo.mello.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Controlador RESTful para a API de Hotéis
 *
 * @author Ricardo
 */
@RestController
@RequestMapping("/api")
public class HotelController {

    private final Logger log = LoggerFactory.getLogger(HotelController.class);

    @Inject
    private HotelRepository hotelRepository;

    /**
     * POST /hotel : Cria um novo hotel.
     *
     * @param hotel Hotel a ser criado
     * @return Um ResponseEntity com status 201 (Criado) e o novo hotel no body da requisição, ou com status 400 (Bad Request) se o hotel já tiver um ID
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/hotel",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> criarHotel(@RequestBody Hotel hotel) throws URISyntaxException {
        log.debug("Salvar Hotel : {}", hotel);
        if (hotel.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.criarMensagemFalha("hotel", "idexists", "O novo hotel já possui um ID e não pode ser criado")).body(null);
        }
        Hotel result = hotelRepository.save(hotel);
        return ResponseEntity.created(new URI("/api/hotel/" + result.getId()))
                .headers(HeaderUtil.criarMensagemEntidadeCriada("hotel", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /hotel : Atualiza um hotel existente.
     *
     * @param hotel Hotel a ser atualizado
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/hotel",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> atualizarHotel(@RequestBody Hotel hotel) throws URISyntaxException {
        log.debug("Atualizar Hotel : {}", hotel);
        if (hotel.getId() == null) {
            return criarHotel(hotel);
        }
        Hotel result = hotelRepository.save(hotel);
        return ResponseEntity.ok()
                .headers(HeaderUtil.criarMensagemEntidadeAtualizada("hotel", hotel.getId().toString()))
                .body(result);
    }


    /**
     * GET /hotel : Listar todos os hotéis.
     *
     * @return Um ResponseEntity com status 200 (OK) e a lista de hotéis no body
     */
    @RequestMapping(value = "/hotel",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> listarHoteis() {
        log.debug("Listar todos os Hotéis");
        List<Hotel> hoteis = hotelRepository.findAll();
        return hoteis;
    }

    /**
     * GET  /hotel/:id : Obtém um hotel a partir do ID.
     *
     * @param id o ID do hotel a ser obtido
     * @return Um ResponseEntity com status 200 (OK) e com o hotel no Body, ou 404 (Não Encontrado)
     */
    @RequestMapping(value = "/hotel/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> obterHotel(@PathVariable Long id) {
        log.debug("Obter Hotel : {}", id);
        Hotel hotel = hotelRepository.findOne(id);

        return Optional.ofNullable(hotel)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /hotel/:id : Remove um hotel a partir do ID
     *
     * @param id o ID do hotel a ser removido
     * @return Um ResponseEntity com status 200 (OK)
     */
    @RequestMapping(value = "/hotel/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> removerHotel(@PathVariable Long id) {
        log.debug("Remover Hotel : {}", id);
        hotelRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.criarMensagemEntidadeRemovida("hotel", id.toString())).build();
    }


}
