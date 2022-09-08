package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * packageName :  domain
 * fileName : Role
 * author :  home
 * date : 22. 9. 8.
 * description :
 */

@Table(name = "role")
@Entity
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    private String roleId;


    @Column(name = "role_name")
    private String roleName;
}
