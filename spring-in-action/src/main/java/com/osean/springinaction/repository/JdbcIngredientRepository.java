package com.osean.springinaction.repository;

import com.osean.springinaction.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JdbcIngredientRepository 클래스는 IngredientRepository 인터페이스를 구현하고 있다.
 */

@Repository
@RequiredArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbc;

    /**
     * query() 메소드는 쿼리로 생성된 결과 세트(ResultSet)의 행(Row) 개수만큼 호출되며
     * 결과 세트의 모든 행을 mapRowToIngredient() 메소드를 통해 각각 객체로 생성하고 이를 List 에 저장한 뒤 반환한다.
     *
     * @return Iterable<Ingredient>
     */
    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    /**
     * queryForObject() 메소드는 query() 메소드와 동일하게 실행되지만,
     * 객체의 List 를 반환하는 대신 하나의 객체만 반환한다.
     * 메소드의 첫 번째, 두 번째 인자는 query() 와 같으며,
     * 세 번째 인자는 쿼리문의 Where 절에 들어갈 id 값을 전달한다.
     * 이를 통해 쿼리를 전달할 때 ? 대신 id 가 치환되서 전달된다.
     *
     * @param id Ingredient.id
     * @return Ingredient
     */
    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject(
                "select id, name, type from Ingredient where id = ?",
                new RowMapper<>() {
                    @Override
                    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return Ingredient
                                .builder()
                                .id(rs.getString("id"))
                                .name(rs.getString("name"))
                                .type(Ingredient.Type.valueOf(rs.getString("type")))
                                .build();
                    }
                },
                id
        );
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

    /**
     * 쿼리로 생성된 결과 세트(ResultSet) 의 행 Row 개수만큼 호출되어 도메인 인스턴스를 생성한다.
     *
     * @param rs     ResultSet
     * @param rowNum Row
     * @return Ingredient
     * @throws SQLException SQLException
     */
    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return Ingredient
                .builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .type(Ingredient.Type.valueOf(rs.getString("type")))
                .build();
    }
}
