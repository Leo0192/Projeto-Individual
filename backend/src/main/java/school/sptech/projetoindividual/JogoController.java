package school.sptech.projetoindividual;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
@CrossOrigin(origins = "*")
public class JogoController {

    private final JdbcTemplate template;

    public JogoController(JdbcTemplate template) {
        this.template = template;
    }

    // GET JOGOS
    @GetMapping
    public ResponseEntity<List<Jogo>> listar() {

        String sql = """
            SELECT 
                j.id,
                j.nome,
                j.preco,
                j.data_lancamento AS dataLancamento,
                j.categoria_id AS categoriaId,
                j.plataforma,
                j.multiplayer,
                c.nome AS categoriaNome
            FROM jogo j
            JOIN categoria c ON j.categoria_id = c.id
        """;

        List<Jogo> jogos =
                template.query(sql, new BeanPropertyRowMapper<>(Jogo.class));

        if (jogos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(jogos);
    }

    // GET CATEGORIAS
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {

        String sql = "SELECT * FROM categoria";

        List<Categoria> categorias =
                template.query(sql,
                        new BeanPropertyRowMapper<>(Categoria.class));

        if (categorias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(categorias);
    }

    // POST
    @PostMapping
    public ResponseEntity<Jogo> cadastrar(@RequestBody Jogo jogo) {

        if (jogo.getNome() == null || jogo.getNome().isBlank() ||
            jogo.getPreco() == null || jogo.getPreco() < 0 ||
            jogo.getDataLancamento() == null || jogo.getDataLancamento().isBlank() ||
            jogo.getCategoriaId() == null || jogo.getCategoriaId() <= 0 ||
            jogo.getPlataforma() == null || jogo.getPlataforma().isBlank()) {
            return ResponseEntity.status(400).build();
        }

        Integer catExiste = template.queryForObject(
                "SELECT COUNT(*) FROM categoria WHERE id = ?",
                Integer.class,
                jogo.getCategoriaId()
        );

        if (catExiste == null || catExiste == 0) {
            return ResponseEntity.status(400).build();
        }

        Integer existe = template.queryForObject(
                "SELECT COUNT(*) FROM jogo WHERE nome = ? AND plataforma = ?",
                Integer.class,
                jogo.getNome(),
                jogo.getPlataforma()
        );

        if (existe != null && existe > 0) {
            return ResponseEntity.status(409).build();
        }

        String insert = """
            INSERT INTO jogo
            (nome, preco, data_lancamento, categoria_id, plataforma, multiplayer)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        template.update(insert,
                jogo.getNome(),
                jogo.getPreco(),
                jogo.getDataLancamento(),
                jogo.getCategoriaId(),
                jogo.getPlataforma(),
                jogo.isMultiplayer()
        );

        Integer id = template.queryForObject(
                "SELECT LAST_INSERT_ID()",
                Integer.class
        );

        jogo.setId(id);

        return ResponseEntity.status(201).body(jogo);
    }
}