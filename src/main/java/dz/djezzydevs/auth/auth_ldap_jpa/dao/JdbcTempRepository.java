package dz.djezzydevs.auth.auth_ldap_jpa.dao;

import dz.djezzydevs.auth.auth_ldap_jpa.dto.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class JdbcTempRepository {




    @Autowired
    JdbcTemplate jdbcTemplate;



    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    public List<AppResponse> findAllByUser(String win_session) {

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("username", win_session);
        return namedParameterJdbcTemplate.query("\n" +
                        "select a.id ,a.appname ,a.role,a.active ,a.publicapp,a.url,a.title ,a.text,a.img \n" +
                        "from  appsroles a  , userapp u   where a.appname =u.appname \n" +
                        "and lower (u.username)=lower(:username) ",
                namedParameters, new BeanPropertyRowMapper<AppResponse>(AppResponse.class));


    }



}
