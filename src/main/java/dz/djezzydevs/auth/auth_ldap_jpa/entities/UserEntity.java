package dz.djezzydevs.auth.auth_ldap_jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="employee")
@Data
public class UserEntity implements Serializable {

	
	private static final long serialVersionUID = -5763827745308343856L;
	
	@Id
	private long id;
	@Column(length=20)
	private String firstname;

	@Column(length=20)
	private String lastname;
	@Column(length=60)
	private String location;

	@Column(length=7)
	private String region;
	@Column
	private Date hiredate;

	@Column(length=60)
	private String sector;

	@Column(length=60)
	private String department;

	@Column(length=60)
	private String organisation;

	private String position;
	private String job;


	private String phone;

	private String email;


	private Date leavingdate ;

	private String pabx ;
	private String gender ;
	private int manager ;
	private String wilaya ;

	private String rib ;
	private String status ;
	private String grade ;
	private int costcenter ;
	private String soldeconge ;

	@ManyToMany
	private List<RoleEntity> roles=new ArrayList<>();




	

	




}
