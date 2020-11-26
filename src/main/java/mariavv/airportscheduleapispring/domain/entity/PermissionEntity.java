package mariavv.airportscheduleapispring.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "permission")
    private String permission;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="role_permissions",
            joinColumns=@JoinColumn (name="permission_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<RoleEntity> roles;

    public PermissionEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
